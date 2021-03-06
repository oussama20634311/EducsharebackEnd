package com.educshare.security;

import java.util.Date;

import com.educshare.entities.AppRole;
import com.educshare.entities.AppUser;

public interface AccountService {
	
	public AppUser SaveStudentWithAvatar(String email , String password,String confirmPassword,String lastName,
			String firstName,Date birthDate,String telNumber,String cin,String city,byte[] avater,String filleName,
			String userLevel,
			String userSection,
			String userClasse, String userUniversity, String userCountry, String userRole);
	
	public AppUser SaveStudentWithoutAvatar(String email , String password,String confirmPassword,String lastName,
			String firstName,Date birthDate,String telNumber,String cin,String city,String userLevel,String userSection,
			String userClasse, String userUniversity, String userCountry, String userRole);
	
	public AppUser SaveProfessorWithAvatar(String email , String password,String confirmPassword,String lastName,
			String firstName,Date birthDate,String telNumber,String cin,String city,byte[] avater,String filleName,
			String userLevel,
			String userSection,
			String userClasse, String userUniversity, String userCountry, String userRole);
	
	public AppUser SaveProfessorWithoutAvatar(String email , String password,String confirmPassword,String lastName,
			String firstName,Date birthDate,String telNumber,String cin,String city,String userLevel,String userSection,
			String userClasse, String userUniversity, String userCountry, String userRole);
	
	public AppUser updateProfileWithAvatar(String email ,String password,String confirmedPassword,String lastName,
			String firstName,Date birthDate,String telNumber,String cin,String city,byte[] avatar,String filleName,
			String userLevel,
			String userSection,
			String userClasse, String userCountry, String userUniversity, String userRole);
	
	public AppUser updateProfileWithoutAvatar(String email ,String password,String confirmedPassword,String lastName,
			String firstName,Date birthDate,String telNumber,String cin,String city,
			String userLevel,
			String userSection,
			String userClasse, String userCountry ,String userUniversity, String userRole);
	
	public AppRole save(AppRole role);

	public AppUser loadUserByEmail(String email);

	public void addRoleToUser(String email,String rolename);

	}