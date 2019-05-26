package com.educshare;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.educshare.security.AccountService;
import com.educshare.service.StorageService;

@SpringBootApplication
public class SecuriteyJwtSpringApplication {
	
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(SecuriteyJwtSpringApplication.class, args);	 
	}
	 public void run(String... arg) throws Exception {
	 storageService.init();
	 }

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {		
//			 accountService.save(new AppRole(null,"ADMIN"));
//			 accountService.save(new AppRole(null,"Etudiant"));
//			 accountService.save(new AppRole(null,"Enseignant"));
			// accountService.SaveStudentWithoutAvatar("admin@gmail.com","3","3", "oussama",
			// "ben abdalla", null, "11025257", "sfax", "ddd", null, null, "55");
			// accountService.SaveProfessorWithoutAvatar("user1@gmail.com","3","3",
			// "oussama", "ben abdalla", null, "11025257", "sfax", "ddd", null, null, "55");
			// accountService.SaveProfessorWithoutAvatar("user2@gmail.com","3","3",
			// "oussama", "ben abdalla", null, "11025257", "sfax", "ddd", null, null, "55");
			// accountService.addRoleToUser("admin@gmail.com","ADMIN");
			// accountService.addRoleToUser("user1@gmail.com","Etudiant");
			// accountService.addRoleToUser("user2@gmail.com","Enseignant");

		};
	}
}