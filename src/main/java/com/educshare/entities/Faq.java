package com.educshare.entities;

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
@Table(name="Faq")
@EntityListeners(AuditingEntityListener.class)
public class Faq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
    @Column(name="question", length=512)  
	private String question;
	
	@NotBlank
    @Column(name="faqValidated", length=512)  
	private String faqValidated;

	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	private AppUser user;

	public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faq(Long id, @NotBlank String question, @NotBlank String faqValidated, Date createdAt, Date updatedAt,
			AppUser user) {
		super();
		this.id = id;
		this.question = question;
		this.faqValidated = faqValidated;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getFaqValidated() {
		return faqValidated;
	}

	public void setFaqValidated(String faqValidated) {
		this.faqValidated = faqValidated;
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
	
	

}
