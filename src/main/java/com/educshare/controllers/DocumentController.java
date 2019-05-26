package com.educshare.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.educshare.entities.Document;
import com.educshare.reposistory.AppUserRepository;
import com.educshare.reposistory.DocumentRepository;
import com.educshare.service.AmazonS3ClientService;
import com.educshare.service.StorageService;
import com.educshare.web.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
public class DocumentController {

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	StorageService storageService;

	@Autowired
	private AmazonS3ClientService amazonS3ClientService;

	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@GetMapping("/documents")
	public List<Document> getAllDocuments() {
		return (List<Document>) documentRepository.findAll();
	}

	@PostMapping("/documents")
	public ResponseEntity<Response> createDocument(@RequestParam("file") MultipartFile file,
			@RequestParam("document") String document) throws JsonParseException, JsonMappingException, IOException {

		Document doc = new ObjectMapper().readValue(document, Document.class);

		Document savedDoc = storageService.storeFile(doc.getDocumentName(), doc.getDocumentMatter(),
				doc.getDocumentValidated(), doc.getDocumentLevel(), doc.getDocumentYear(), doc.getDocumentTheme(),
				doc.getDocumentType(), doc.getDocumentSemestre(), doc.getDocumentCountry(), doc.getDocumentUniversity(),
				doc.getDocumentDepartment(), file.getOriginalFilename(), file.getOriginalFilename(), file.getBytes(),
				file.getBytes(), doc.getCreatedAt(), doc.getUpdatedAt(), doc.getUserId());

		// Save document file to S3 bucket
		this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

		if (savedDoc != null) {
			return new ResponseEntity<Response>(new Response("Document was added successfully !!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("Failure in inserting document !!"),
					HttpStatus.BAD_REQUEST);
		}

	}


	@GetMapping("/documents/{id}")
	public Document getDocumentById(@PathVariable(value = "id") Long DocumentId) {
		Optional<Document> document = documentRepository.findById(DocumentId);
		return documentRepository.findById(DocumentId).orElseThrow(() -> new ResourceNotFoundException());
	}

	// Update a Document
	@PutMapping("/documents/{id}")
	public Document updateDocument(@PathVariable(value = "id") Long DocumentId,
			@Valid @RequestBody Document DocumentDetails) {

		Document Document = documentRepository.findById(DocumentId).orElseThrow(() -> new ResourceNotFoundException());

		Document.setDocumentName(DocumentDetails.getDocumentName());
		
		Document.setDocumentTheme(DocumentDetails.getDocumentTheme());

		Document updatedDocument = documentRepository.save(Document);
		return updatedDocument;
	}

	// Delete a Document
	@DeleteMapping("/documents/{id}")
	public ResponseEntity<?> deleteDocument(@PathVariable(value = "id") Long DocumentId) {
		Document document = documentRepository.findById(DocumentId).orElseThrow(() -> new ResourceNotFoundException());

		documentRepository.delete(document);
		// Delete from S3 bucket
		this.amazonS3ClientService.deleteFileFromS3Bucket(document.getDocumentFileEnonce());

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/search-documents")
	public List<Document> findByDocumentNameAndDocumentMatter(
			@RequestParam(value = "documentName") String documentName,
			@RequestParam(value = "documentMatter") String documentMatter) {
		return documentRepository.findByDocumentNameOrDocumentMatter(documentName, documentMatter);
	}
	
	@GetMapping(value = "/search-cours")
	public List<Document> findByDocumentName(
			@RequestParam(value = "documentName") String documentName) {
		return documentRepository.findDocumentOfDocumentName(documentName);
	}
	
	@GetMapping(value = "/search-matters")
	public List<Document> findByDocumentMatter(
			@RequestParam(value = "documentMatter") String documentMatter) {
		return documentRepository.findDocumentOfDocumentMatter(documentMatter);
	}

}