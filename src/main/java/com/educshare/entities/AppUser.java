package com.educshare.entities;

import java.util.ArrayList;
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
@Data @NoArgsConstructor
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
	
	@Column(name = "userRole")
	private String userRole;
	
	@Column(name = "userCountry")
	private String userCountry;
	
	@Column(name = "userUniversity")
	private String userUniversity;

//	public Collection<AppRole> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<AppRole> roles) {
//		this.roles = roles;
//	}

	// User has many comments
	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL )
    private List<Comment> comments = new ArrayList<Comment>();

	// User has many documents
//	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL )
//    private List<Document> documents = new ArrayList<Document>();
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
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
//	@ManyToMany(fetch = FetchType.EAGER)
//	private Collection<AppRole> roles = new ArrayList<>();	
}