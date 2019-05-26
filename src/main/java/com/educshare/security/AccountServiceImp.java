package com.educshare.security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.educshare.entities.AppRole;
import com.educshare.entities.AppUser;
import com.educshare.reposistory.AppRoleRepository;
import com.educshare.reposistory.AppUserRepository;

@Service
@Transactional
@CrossOrigin("*")
public class AccountServiceImp implements AccountService {
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;


	public AccountServiceImp(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
		super();
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
	}
	
	@Override
	public AppRole save(AppRole role) {
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByEmail(String email) {
		return appUserRepository.findByEmail(email);
	}

	@Override
	public void addRoleToUser(String email, String rolename) {
//		AppUser appUser = appUserRepository.findByEmail(email);
//		AppRole appRole = appRoleRepository.findByRoleName(rolename);
//		appUser.getRoles().add(appRole);
	}


	@Override
	public AppUser SaveStudentWithAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, Date birthDate, String telNumber, String cin, String city, byte[] avatar,
			String filleName, String userLevel, String userSection, String userClasse, String userUniversity, String userCountry, String userRole) {
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(password);
			appUser.setConfirmPassword(confirmPassword);
			appUser.setLastName(lastName);
			appUser.setFirstName(firstName);
			appUser.setBirthDate(birthDate);
			appUser.setTelNumber(telNumber);
			appUser.setCin(cin);
			appUser.setCity(city);
			appUser.setAvatar(avatar);
			appUser.setFilleName(filleName);
			appUser.setUserLevel(userLevel);
			appUser.setUserSection(userSection);
			appUser.setUserClass(userClasse);
			appUser.setUserCountry(userCountry);
			appUser.setUserUniversity(userUniversity);
			appUser.setUserRole("Etudiant");
			appUserRepository.save(appUser);
//			addRoleToUser(email, "Etudiant");
			return appUser;
	}

	@Override
	public AppUser SaveStudentWithoutAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, Date birthDate, String telNumber, String cin, String city, String userLevel,
			String userSection, String userClasse, String userUniversity, String userCountry, String userRole) {
		
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(password);
			appUser.setConfirmPassword(confirmPassword);
			appUser.setLastName(lastName);
			appUser.setFirstName(firstName);
			appUser.setBirthDate(birthDate);
			appUser.setTelNumber(telNumber);
			appUser.setCin(cin);
			appUser.setCity(city);
			appUser.setUserLevel(userLevel);
			appUser.setUserSection(userSection);
			appUser.setUserClass(userClasse);
			appUser.setUserCountry(userCountry);
			appUser.setUserUniversity(userUniversity);
			appUser.setUserRole("Etudiant");
			appUserRepository.save(appUser);
//			addRoleToUser(email, "Etudiant");
			return appUser;
	}

	@Override
	public AppUser SaveProfessorWithAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, Date birthDate, String telNumber, String cin, String city, byte[] avatar,
			String filleName, String userLevel, String userSection, String userClasse, String userUniversity, String userCountry, String userRole) {
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(password);
			appUser.setConfirmPassword(confirmPassword);
			appUser.setLastName(lastName);
			appUser.setFirstName(firstName);
			appUser.setBirthDate(birthDate);
			appUser.setTelNumber(telNumber);
			appUser.setCin(cin);
			appUser.setCity(city);
			appUser.setAvatar(avatar);
			appUser.setFilleName(filleName);
			appUser.setUserLevel(userLevel);
			appUser.setUserSection(userSection);
			appUser.setUserClass(userClasse);
			appUser.setUserCountry(userCountry);
			appUser.setUserUniversity(userUniversity);
			appUser.setUserRole("Enseignant");
			appUserRepository.save(appUser);
//			addRoleToUser(email, "Enseignant");
			return appUser;
	}

	@Override
	public AppUser SaveProfessorWithoutAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, Date birthDate, String telNumber, String cin, String city, String userLevel,
			String userSection, String userClasse, String userUniversity, String userCountry, String userRole) {
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
	
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(password);
			appUser.setConfirmPassword(confirmPassword);
			appUser.setLastName(lastName);
			appUser.setFirstName(firstName);
			appUser.setBirthDate(birthDate);
			appUser.setTelNumber(telNumber);
			appUser.setCin(cin);
			appUser.setCity(city);
			appUser.setUserLevel(userLevel);
			appUser.setUserSection(userSection);
			appUser.setUserClass(userClasse);
			appUser.setUserCountry(userCountry);
			appUser.setUserUniversity(userUniversity);
			appUser.setUserRole("Enseignant");
			appUserRepository.save(appUser);
//			addRoleToUser(email, "Enseignant");
			return appUser;
	}
	
	@Override
	public AppUser updateProfileWithAvatar(String email ,String password, String confirmedPassword, String lastName, String firstName,
			Date birthDate, String telNumber, String cin, String city, byte[] avatar, String filleName,
			String userLevel, String userSection, String userClasse,  String userUniversity, String userCountry, String userRole) {		
		AppUser appUser = appUserRepository.findByEmail(email);	
		
		appUser.setPassword(password);
		appUser.setConfirmPassword(confirmedPassword);
		appUser.setLastName(lastName);
		appUser.setFirstName(firstName);
		appUser.setBirthDate(birthDate);
		appUser.setTelNumber(telNumber);
		appUser.setCin(cin);
		appUser.setCity(city);
		appUser.setAvatar(avatar);
		appUser.setFilleName(filleName);
		appUser.setUserLevel(userLevel);
		appUser.setUserSection(userSection);
		appUser.setUserClass(userClasse);
		appUser.setUserCountry(userCountry);
		appUser.setUserUniversity(userUniversity);
		
		AppUser updateProfileWithAvatar = appUserRepository.save(appUser);
		return updateProfileWithAvatar;
	}

	@Override
	public AppUser updateProfileWithoutAvatar(String email, String password, String confirmedPassword, String lastName,
			String firstName, Date birthDate, String telNumber, String cin, String city, String userLevel,
			String userSection, String userClasse,  String userUniversity, String userCountry, String userRole) {
		AppUser appUser = appUserRepository.findByEmail(email);	
		
		appUser.setPassword(password);
		appUser.setConfirmPassword(confirmedPassword);
		appUser.setLastName(lastName);
		appUser.setFirstName(firstName);
		appUser.setBirthDate(birthDate);
		appUser.setTelNumber(telNumber);
		appUser.setCin(cin);
		appUser.setCity(city);
		appUser.setUserLevel(userLevel);
		appUser.setUserSection(userSection);
		appUser.setUserClass(userClasse);
		appUser.setUserCountry(userCountry);
		appUser.setUserUniversity(userUniversity);
		
		AppUser updateProfileWithAvatar = appUserRepository.save(appUser);
		return updateProfileWithAvatar;
	}
	
}