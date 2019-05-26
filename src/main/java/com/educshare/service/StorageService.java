package com.educshare.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.educshare.entities.Document;
import com.educshare.exception.MyFileNotFoundException;
import com.educshare.reposistory.DocumentRepository;


@Service
@Transactional
public class StorageService {

	@Autowired
	private DocumentRepository documentRepository;
	
	// Added part //	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	 private final Path rootLocation = Paths.get("/app");
	 
	 public void store(MultipartFile file) {
	 try {
	 Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
	 log.error(this.rootLocation.resolve(file.getOriginalFilename()).toString());
	 log.error(rootLocation.toString());
	 } catch (Exception e) {
	 throw new RuntimeException("FAIL!");
	 }
	 }
	 
	 public Resource loadFile(String filename) {
	 try {
	 Path file = rootLocation.resolve(filename);
	 Resource resource = new UrlResource(file.toUri());
	 if (resource.exists() || resource.isReadable()) {
	 return resource;
	 } else {
	 throw new RuntimeException("FAIL!");
	 }
	 } catch (MalformedURLException e) {
	 throw new RuntimeException("FAIL!");
	 }
	 }
	 
	 public void deleteAll() {
	 FileSystemUtils.deleteRecursively(rootLocation.toFile());
	 }
	 
	 public void init() {
	 try {
	 Files.createDirectory(rootLocation);
	 } catch (IOException e) {
	 throw new RuntimeException("Could not initialize storage!");
	 }
	 }
	 
	 // finished //	 

    public Document storeFile(String documentName, String documentMatter, String documentValidated,
	 String documentLevel, String documentYear, String documentTheme, String documentType, Long documentSemestre,
	 String documentCountry, String documentUniversity, String documentDepartment, String documentFileEnonce, String documentFileCorrige,
     byte[] enonceData,byte[] corrigeData, Date createdAt, Date updatedAt, Long userId  ) {
        
    	Document document = new Document();
    	document.setDocumentName(documentName);
    	document.setDocumentMatter(documentMatter);
    	document.setDocumentValidated("false");
    	document.setDocumentLevel(documentLevel);
    	document.setDocumentYear(documentYear);
    	document.setDocumentTheme(documentTheme);
    	document.setDocumentType(documentType);
    	document.setDocumentSemestre(documentSemestre);
    	document.setDocumentCountry(documentCountry);
    	document.setDocumentUniversity(documentUniversity);
    	document.setDocumentDepartment(documentDepartment);
    	document.setDocumentFileCorrige(documentFileCorrige);
    	document.setDocumentFileEnonce(documentFileEnonce);
//    	document.setEnonceData(enonceData);
//    	document.setCorrigeData(corrigeData);
    	document.setCreatedAt(createdAt);
    	document.setUpdatedAt(updatedAt);
    	document.setUserId(userId);
    	documentRepository.save(document);
    	return document;
    }

    public Document getFile(long fileId) {
        return documentRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
