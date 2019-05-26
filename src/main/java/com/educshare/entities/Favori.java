package com.educshare.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Favori {

	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long documentId;
	private Long userId;

	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	
	
	public Favori() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Favori(Long id, Long documentId, Long userId, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.documentId = documentId;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Favori orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
