package com.educshare.service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.educshare.entities.AppUser;
import com.educshare.reposistory.AppUserRepository;
import com.educshare.web.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@RestController
public class UserController {
	
	
	private AccountService accountService;

	@Autowired
	AppUserRepository appUserRepository;
	
	@PostMapping(value ="SaveStudentWithoutAvatar")
	public AppUser saveStudentWithoutAvatar(@Valid @RequestBody UserForm userForm) throws IOException {
		
		return accountService.SaveStudentWithoutAvatar(userForm.getEmail(), userForm.getPassword(), userForm.getConfirmPassword(),
				userForm.getLastName(), userForm.getFirstName(),userForm.getBirthDate() ,userForm.getTelNumber(), userForm.getCin(),
				userForm.getCity(), userForm.getUserLevel(), userForm.getUserSection(),
				userForm.getUserClass());
	}
	
	@PostMapping(value = "SaveStudentWithAvatar")
	public ResponseEntity<Response> saveStudentWithAvatar(@RequestParam("file") MultipartFile file,
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {

		AppUser appUser = new ObjectMapper().readValue(user, AppUser.class);
				
		AppUser dbperson = accountService.SaveStudentWithAvatar(appUser.getEmail(), appUser.getPassword(), appUser.getConfirmPassword(),
				appUser.getLastName(), appUser.getFirstName(),appUser.getBirthDate() ,appUser.getTelNumber(), appUser.getCin(),
				appUser.getCity(),file.getBytes(),file.getOriginalFilename(), appUser.getUserLevel(), appUser.getUserSection(),
				appUser.getUserClass());
		
		if (dbperson != null) {
			return new ResponseEntity<Response>(new Response("user is save Successufull"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("user isn't save"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value ="SaveProfessorWithoutAvatar")
	public AppUser SaveProfessorWithoutAvatar(@Valid @RequestBody UserForm userForm) throws IOException {
		
		return accountService.SaveProfessorWithoutAvatar(userForm.getEmail(), userForm.getPassword(), userForm.getConfirmPassword(),
				userForm.getLastName(), userForm.getFirstName(),userForm.getBirthDate() ,userForm.getTelNumber(), userForm.getCin(),
				userForm.getCity(), userForm.getUserLevel(), userForm.getUserSection(),
				userForm.getUserClass());
	}
	
	@PostMapping(value = "SaveProfessorWithAvatar")
	public ResponseEntity<Response> SaveProfessorWithAvatar(@RequestParam("file") MultipartFile file,
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {

		AppUser appUser = new ObjectMapper().readValue(user, AppUser.class);
				
		AppUser dbperson = accountService.SaveProfessorWithAvatar(appUser.getEmail(), appUser.getPassword(), appUser.getConfirmPassword(),
				appUser.getLastName(), appUser.getFirstName(),appUser.getBirthDate() ,appUser.getTelNumber(), appUser.getCin(),
				appUser.getCity(),file.getBytes(),file.getOriginalFilename(), appUser.getUserLevel(), appUser.getUserSection(),
				appUser.getUserClass());
		
		if (dbperson != null) {
			return new ResponseEntity<Response>(new Response("user is save Successufull"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("user isn't save"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/profil/{email}")
	public AppUser getUserProfilByEmail(@PathVariable(value = "email") String email) {
		return appUserRepository.findByEmail(email);
	}
	
	@GetMapping("/getUserById/{id}")
	public Optional<AppUser> getUserProfilById(@PathVariable(value = "id") Long id) {
		return appUserRepository.findById(id);
	}

	
	@PutMapping("/profilWithAvatar/{email}/edit")
	public ResponseEntity<Response> updateAppUser(@RequestParam("file") MultipartFile file,
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {

		AppUser appUser = new ObjectMapper().readValue(user, AppUser.class);

		AppUser dbperson = accountService.updateProfileWithAvatar(appUser.getEmail(), appUser.getPassword(),
				appUser.getConfirmPassword(), appUser.getLastName(), appUser.getFirstName(), appUser.getBirthDate(),
				appUser.getTelNumber(), appUser.getCin(), appUser.getCity(), file.getBytes(),
				file.getOriginalFilename(), appUser.getUserLevel(), appUser.getUserSection(), appUser.getUserClass());

		if (dbperson != null) {
			return new ResponseEntity<Response>(new Response("user is save Successufull"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("user isn't save"), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/profilWithoutAvatar/{email}/edit")
	public AppUser updateAppUserWithoutAvatar(@Valid @RequestBody UserForm userForm) throws IOException {
		return accountService.updateProfileWithoutAvatar(userForm.getEmail(), userForm.getPassword(),
				userForm.getConfirmPassword(), userForm.getLastName(), userForm.getFirstName(), userForm.getBirthDate(),
				userForm.getTelNumber(), userForm.getCin(), userForm.getCity(), userForm.getUserLevel(),
				userForm.getUserSection(), userForm.getUserClass());
	}
}


class UserForm {
	private String email;
	private String password;
	private String confirmPassword;
	private String lastName;
	private String firstName;
    private Date birthDate;
	private String telNumber;
	private String cin;
	private String city;
	private String userLevel;
	private String userSection;
	private String userClass;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	
	
}