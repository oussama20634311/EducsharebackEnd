package com.educshare.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AppUser")
@EntityListeners(AuditingEntityListener.class)
//@Data @NoArgsConstructor
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", nullable=false)
	private Long id;
	@NotBlank
	@Column(name = "firstName")
	private String firstName;
	@NotBlank
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "birthDate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(unique = true)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String confirmPassword;
	
	@NotBlank
	@Column(name = "telNumber")
	private String telNumber;

	
	private String cin;
	private String city;
	
	//Save file’s data to MySQL into BLOB Format.
	@Lob
	@Column(name="avatar")
	private byte[] avatar;	
	private String filleName;
//	@NotBlank
	@Column(name = "userLevel")
	private String userLevel;
//	@NotBlank
	@Column(name = "userSection")
	private String userSection;
//	@NotBlank
	@Column(name = "userClass")
	private String userClass;

	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}

	// User has many comments
	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL )
    private List<Comment> comments = new ArrayList<Comment>();

	// User has many documents
//	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL )
//    private List<Document> documents = new ArrayList<Document>();
	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name="userid", referencedColumnName="userid")
    private List<Document> documents = new ArrayList<Document>();
	
	// User has many Baskets
	@OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
	private List<Basket> baskets = new ArrayList<Basket>();
//	
//	// User has many messages
//	@OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
//	private List<Message> messages = new ArrayList<Message>();
	
	// User has many FAQs
	@OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
	private List<Faq> faqs = new ArrayList<Faq>();
	
	// User has roles
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getFilleName() {
		return filleName;
	}

	public void setFilleName(String filleName) {
		this.filleName = filleName;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserSection() {
		return userSection;
	}

	public void setUserSection(String userSection) {
		this.userSection = userSection;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
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

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(List<Basket> baskets) {
		this.baskets = baskets;
	}

	public List<Faq> getFaqs() {
		return faqs;
	}

	public void setFaqs(List<Faq> faqs) {
		this.faqs = faqs;
	}

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
