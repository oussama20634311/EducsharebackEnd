package com.educshare.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.educshare.entities.University;
import com.educshare.reposistory.UniversityRepository;

@RestController
public class UniversityController {
	@Autowired
	UniversityRepository universityRepository;

	@GetMapping(value="/universities")
	public List<University> getAllUniversitys() {
		return (List<University>) universityRepository.findAll();
	}

	@PostMapping(value="/universities")
	public University createUniversity(@Valid @RequestBody University university) {
		return universityRepository.save(university);
	}

	@GetMapping(value="/universities/{id}")
	public University getUniversityById(@PathVariable(value = "id") Long UniversityId) {
		return universityRepository.findById(UniversityId).orElseThrow(() -> new ResourceNotFoundException());
	}

	@PutMapping(value="/universities/{id}/edit")
	public University updateUniversity(@PathVariable(value = "id") Long UniversityId,
			@Valid @RequestBody University UniversityDetails) {

		University University = universityRepository.findById(UniversityId)
				.orElseThrow(() -> new ResourceNotFoundException());

		University.setUniversityName(UniversityDetails.getUniversityName());
		University.setUniversityAcronyme(UniversityDetails.getUniversityAcronyme() );

		University updatedUniversity = universityRepository.save(University);
		return updatedUniversity;
	}

	@DeleteMapping(value="/universities/{id}")
	public ResponseEntity<?> deleteUniversity(@PathVariable(value = "id") Long UniversityId) {
		University University = universityRepository.findById(UniversityId)
				.orElseThrow(() -> new ResourceNotFoundException());

		universityRepository.delete(University);

		return ResponseEntity.ok().build();
	}

}
