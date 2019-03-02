package com.educshare.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educshare.entities.AppUser;
import com.educshare.entities.Document;
import com.educshare.reposistory.AppUserRepository;
import com.educshare.reposistory.DocumentRepository;
import com.educshare.service.StorageService;
import com.educshare.web.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


	@RestController
	public class DocumentController {

		@Autowired
		DocumentRepository documentRepository;
		
		@Autowired
		AppUserRepository appUserRepository;
		
		@Autowired
		StorageService storageService;
		
		private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);


		@GetMapping(value="/documents")
		public List<Document> getAllDocuments() {
			return (List<Document>) documentRepository.findAll();
		}

			
		@PostMapping(value="/documents")
		public ResponseEntity<Response> createDocument(@RequestParam("file") MultipartFile file,
				@RequestParam("document") String document) throws JsonParseException, JsonMappingException, IOException {

			Document doc = new ObjectMapper().readValue(document, Document.class);

			Document savedDoc = storageService.storeFile(doc.getDocumentName(), doc.getDocumentMatter(), doc.getDocumentValidated(), 
					doc.getDocumentLevel(), doc.getDocumentYear(), doc.getDocumentTheme(), doc.getDocumentType(), doc.getDocumentSemestre(),
					doc.getDocumentCountry(), doc.getDocumentUniversity(), doc.getDocumentDepartment(), file.getOriginalFilename(),
					file.getOriginalFilename(), file.getBytes(), file.getBytes(), doc.getCreatedAt(), doc.getUpdatedAt() , doc.getUserId());
			
			
			if (savedDoc != null) {
				return new ResponseEntity<Response>(new Response("Document is Added"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("Document does not added"), HttpStatus.BAD_REQUEST);
			}
		}
		
		
	
		@GetMapping(value="/documents/{id}")
		public Document getDocumentById(@PathVariable(value = "id") Long DocumentId) {
			return documentRepository.findById(DocumentId)
					.orElseThrow(() -> new ResourceNotFoundException());
		}

		// Update a Document
		@PutMapping(value="/documents/{id}")
		public Document updateDocument(@PathVariable(value = "id") Long DocumentId,
		                                        @Valid @RequestBody Document DocumentDetails) {

		    Document Document = documentRepository.findById(DocumentId)
		            .orElseThrow(() -> new ResourceNotFoundException());

		    Document.setDocumentName(DocumentDetails.getDocumentName());;
		    Document.setDocumentTheme(DocumentDetails.getDocumentTheme());

		    Document updatedDocument = documentRepository.save(Document);
		    return updatedDocument;
		}
		
		
		// Delete a Document
		@DeleteMapping(value="/documents/{id}")
		public ResponseEntity<?> deleteDocument(@PathVariable(value = "id") Long DocumentId) {
			Document Document = documentRepository.findById(DocumentId)
					.orElseThrow(() -> new ResourceNotFoundException());

			documentRepository.delete(Document);

			return ResponseEntity.ok().build();
		}
		
		@GetMapping(value="/search-documents")
		public List<Document> findByDocumentNameAndDocumentMatter1(@RequestParam(value = "documentName") String documentName ,
				@RequestParam(value = "documentMatter") String documentMatter) {
			return documentRepository.findByDocumentNameOrDocumentMatter(documentName,documentMatter);
		}
		

}