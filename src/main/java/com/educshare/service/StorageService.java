package com.educshare.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educshare.entities.AppUser;
import com.educshare.entities.Document;
import com.educshare.exception.MyFileNotFoundException;
import com.educshare.reposistory.DocumentRepository;


@Service
public class StorageService {

	@Autowired
	private DocumentRepository documentRepository;

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
    	document.setEnonceData(enonceData);
    	document.setCorrigeData(corrigeData);
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
