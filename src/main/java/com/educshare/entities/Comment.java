package com.educshare.entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(length = 1000)
	private String content;

	@Column(name = "commentValidated")
	private boolean commentValidated;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;
	

	@ManyToOne
    @JoinColumn(name="document_id_fk")
    private Document document;
	
	
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	public AppUser user;


	public Comment(@NotBlank String content, boolean commentValidated, Date createdAt, Date updatedAt
			, AppUser user , Document document) {
		super();
		this.content = content;
		this.commentValidated = commentValidated;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.document = document;
		this.user = user;
	}


	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public boolean isCommentValidated() {
		return commentValidated;
	}


	public void setCommentValidated(boolean commentValidated) {
		this.commentValidated = commentValidated;
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


	public AppUser getUser() {
		return user;
	}


	public void setUser(AppUser user) {
		this.user = user;
	}	
	
	public Document getDocument() {
		return document;
	}


	public void setDocument(Document document) {
		this.document = document;
	}
	
}
