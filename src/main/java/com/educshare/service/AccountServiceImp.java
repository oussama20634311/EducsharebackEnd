package com.educshare.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educshare.entities.AppRole;
import com.educshare.entities.AppUser;
import com.educshare.reposistory.AppRoleRepository;
import com.educshare.reposistory.AppUserRepository;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public AccountServiceImp(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
		AppUser appUser = appUserRepository.findByEmail(email);
		AppRole appRole = appRoleRepository.findByRoleName(rolename);
		appUser.getRoles().add(appRole);
	}


	@Override
	public AppUser SaveStudentWithAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, Date birthDate, String telNumber, String cin, String city, byte[] avatar,
			String filleName, String userLevel, String userSection, String userClasse) {
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(bCryptPasswordEncoder.encode(password));
			appUser.setConfirmPassword(bCryptPasswordEncoder.encode(confirmPassword));
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
			appUserRepository.save(appUser);
			addRoleToUser(email, "Etudiant");
			return appUser;
	}

	@Override
	public AppUser SaveStudentWithoutAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, java.util.Date birthDate, String telNumber, String cin, String city, String userLevel,
			String userSection, String userClasse) {
		
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(bCryptPasswordEncoder.encode(password));
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
			appUserRepository.save(appUser);
			addRoleToUser(email, "Etudiant");
			return appUser;
	}

	@Override
	public AppUser SaveProfessorWithAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, java.util.Date birthDate, String telNumber, String cin, String city, byte[] avatar,
			String filleName, String userLevel, String userSection, String userClasse) {
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(bCryptPasswordEncoder.encode(password));
			appUser.setConfirmPassword(bCryptPasswordEncoder.encode(confirmPassword));
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
			appUserRepository.save(appUser);
			addRoleToUser(email, "Enseignant");
			return appUser;
	}

	@Override
	public AppUser SaveProfessorWithoutAvatar(String email, String password, String confirmPassword, String lastName,
			String firstName, java.util.Date birthDate, String telNumber, String cin, String city, String userLevel,
			String userSection, String userClasse) {
			AppUser user = appUserRepository.findByEmail(email);
			if (user != null)
				throw new RuntimeException("User already exists");
	
			AppUser appUser = new AppUser();
			appUser.setEmail(email);
			appUser.setPassword(bCryptPasswordEncoder.encode(password));
			appUser.setConfirmPassword(bCryptPasswordEncoder.encode(confirmPassword));
			appUser.setLastName(lastName);
			appUser.setFirstName(firstName);
			appUser.setBirthDate(birthDate);
			appUser.setTelNumber(telNumber);
			appUser.setCin(cin);
			appUser.setCity(city);
			appUser.setUserLevel(userLevel);
			appUser.setUserSection(userSection);
			appUser.setUserClass(userClasse);
			appUserRepository.save(appUser);
			addRoleToUser(email, "Enseignant");
			return appUser;
	}
	
	@Override
	public AppUser updateProfileWithAvatar(String email ,String password, String confirmedPassword, String lastName, String firstName,
			Date birthDate, String telNumber, String cin, String city, byte[] avatar, String filleName,
			String userLevel, String userSection, String userClasse) {		
		AppUser appUser = appUserRepository.findByEmail(email);	
		
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUser.setConfirmPassword(bCryptPasswordEncoder.encode(confirmedPassword));
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
		
		AppUser updateProfileWithAvatar = appUserRepository.save(appUser);
		return updateProfileWithAvatar;
	}

	@Override
	public AppUser updateProfileWithoutAvatar(String email, String password, String confirmedPassword, String lastName,
			String firstName, Date birthDate, String telNumber, String cin, String city, String userLevel,
			String userSection, String userClasse) {
		AppUser appUser = appUserRepository.findByEmail(email);	
		
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUser.setConfirmPassword(bCryptPasswordEncoder.encode(confirmedPassword));
		appUser.setLastName(lastName);
		appUser.setFirstName(firstName);
		appUser.setBirthDate(birthDate);
		appUser.setTelNumber(telNumber);
		appUser.setCin(cin);
		appUser.setCity(city);
		appUser.setUserLevel(userLevel);
		appUser.setUserSection(userSection);
		appUser.setUserClass(userClasse);
		
		AppUser updateProfileWithAvatar = appUserRepository.save(appUser);
		return updateProfileWithAvatar;
	}
	
}