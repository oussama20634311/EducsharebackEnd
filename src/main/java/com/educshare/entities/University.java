package com.educshare.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "University")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class University {

	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(name = "universityName")
	private String universityName;
	@NotBlank
	@Column(name = "universityAcronyme")
	private String universityAcronyme;
	private String countryName;

	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	

	public University(Long id, @NotBlank String universityName, @NotBlank String universityAcronyme, String countryName,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.universityName = universityName;
		this.universityAcronyme = universityAcronyme;
		this.countryName = countryName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	public University() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getUniversityAcronyme() {
		return universityAcronyme;
	}
	public void setUniversityAcronyme(String universityAcronyme) {
		this.universityAcronyme = universityAcronyme;
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	
	

}
