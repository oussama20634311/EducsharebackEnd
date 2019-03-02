package com.educshare.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "Document")
@EntityListeners(AuditingEntityListener.class)
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String documentName;
	private String documentMatter;
	private String documentValidated;
	private String documentLevel;
	private String documentYear;
	private String documentTheme;
	private String documentType;	

	private Long documentSemestre;
	private String documentCountry;
	private String documentUniversity;
	private String documentDepartment;
	
	private String documentFileEnonce;
	private String documentFileCorrige;
	
	@Lob
    private byte[] enonceData;	
	@Lob
    private byte[] corrigeData;

	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;

	// Document has many comments
	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();	
	
	// Document belongs to User
//	@ManyToOne
//	@JoinColumn(name="user_id_fk")
//	public AppUser user;
	
	@Column(name="userid")
    private Long userId;
	
	// Document has many Baskets
	@OneToMany(mappedBy="document" , cascade = CascadeType.ALL)
	private List<Basket> baskets = new ArrayList<Basket>();
	
	public Document() {
		super();
	}

	public Document(Long id, String documentName, String documentMatter, String documentValidated, String documentLevel,
			String documentYear, String documentTheme, String documentType, Long documentSemestre, String documentCountry,
			String documentUniversity, String documentDepartment, String documentFileEnonce, String documentFileCorrige,
			byte[] enonceData, byte[] corrigeData, Date createdAt, Date updatedAt, List<Comment> comments, Long userId,
			List<Basket> baskets) {
		super();
		this.id = id;
		this.documentName = documentName;
		this.documentMatter = documentMatter;
		this.documentValidated = documentValidated;
		this.documentLevel = documentLevel;
		this.documentYear = documentYear;
		this.documentTheme = documentTheme;
		this.documentType = documentType;
		this.documentSemestre = documentSemestre;
		this.documentCountry = documentCountry;
		this.documentUniversity = documentUniversity;
		this.documentDepartment = documentDepartment;
		this.documentFileEnonce = documentFileEnonce;
		this.documentFileCorrige = documentFileCorrige;
		this.enonceData = enonceData;
		this.corrigeData = corrigeData;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.comments = comments;
		this.userId = userId;
		this.baskets = baskets;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentMatter() {
		return documentMatter;
	}

	public void setDocumentMatter(String documentMatter) {
		this.documentMatter = documentMatter;
	}

	public String getDocumentValidated() {
		return documentValidated;
	}

	public void setDocumentValidated(String documentValidated) {
		this.documentValidated = documentValidated;
	}

	public String getDocumentLevel() {
		return documentLevel;
	}

	public void setDocumentLevel(String documentLevel) {
		this.documentLevel = documentLevel;
	}

	public String getDocumentYear() {
		return documentYear;
	}

	public void setDocumentYear(String documentYear) {
		this.documentYear = documentYear;
	}

	public String getDocumentTheme() {
		return documentTheme;
	}

	public void setDocumentTheme(String documentTheme) {
		this.documentTheme = documentTheme;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Long getDocumentSemestre() {
		return documentSemestre;
	}

	public void setDocumentSemestre(Long documentSemestre) {
		this.documentSemestre = documentSemestre;
	}

	public String getDocumentCountry() {
		return documentCountry;
	}

	public void setDocumentCountry(String documentCountry) {
		this.documentCountry = documentCountry;
	}

	public String getDocumentUniversity() {
		return documentUniversity;
	}

	public void setDocumentUniversity(String documentUniversity) {
		this.documentUniversity = documentUniversity;
	}

	public String getDocumentDepartment() {
		return documentDepartment;
	}

	public void setDocumentDepartment(String documentDepartment) {
		this.documentDepartment = documentDepartment;
	}

	public String getDocumentFileEnonce() {
		return documentFileEnonce;
	}

	public void setDocumentFileEnonce(String documentFileEnonce) {
		this.documentFileEnonce = documentFileEnonce;
	}

	public String getDocumentFileCorrige() {
		return documentFileCorrige;
	}

	public void setDocumentFileCorrige(String documentFileCorrige) {
		this.documentFileCorrige = documentFileCorrige;
	}

	public byte[] getEnonceData() {
		return enonceData;
	}

	public void setEnonceData(byte[] enonceData) {
		this.enonceData = enonceData;
	}

	public byte[] getCorrigeData() {
		return corrigeData;
	}

	public void setCorrigeData(byte[] corrigeData) {
		this.corrigeData = corrigeData;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(List<Basket> baskets) {
		this.baskets = baskets;
	}
	
	
	
	
	

	
}
