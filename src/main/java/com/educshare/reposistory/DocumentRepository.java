package com.educshare.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educshare.entities.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
	
	List<Document> findByDocumentNameOrDocumentMatter(String documentName, String documentMatter);

//	List<Document> findByDocumentNameContaining(String documentName);
	
//	List<Document> findByDocumentMatterContaining(String documentMatter);
	
	@Query("SELECT d FROM Document d WHERE lower(d.documentName) LIKE lower(CONCAT('%',:documentName,'%'))")
	List<Document> findDocumentOfDocumentName(@Param("documentName") String documentName);

	@Query("SELECT d FROM Document d WHERE lower(d.documentMatter) LIKE lower(CONCAT('%',:documentMatter,'%'))")
	List<Document> findDocumentOfDocumentMatter(@Param("documentMatter") String documentMatter);

}
