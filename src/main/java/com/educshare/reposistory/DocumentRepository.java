package com.educshare.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educshare.entities.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
	
	List<Document> findByDocumentNameOrDocumentMatter(String documentName, String documentMatter);

}
