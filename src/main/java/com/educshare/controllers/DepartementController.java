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

import com.educshare.entities.Departement;
import com.educshare.reposistory.DepartementRepository;


@RestController
@CrossOrigin(origins = "*")
public class DepartementController {
	@Autowired
	DepartementRepository departementRepository;

	@GetMapping(value="/departments")
	public List<Departement> getAllDepartements() {
		return (List<Departement>) departementRepository.findAll();
	}

	@PostMapping(value="/departments")
	public Departement createDepartement(@Valid @RequestBody Departement departement) {
		return departementRepository.save(departement);
	}

	@GetMapping(value="/departments/{id}")
	public Departement getDepartementById(@PathVariable(value = "id") Long DepartementId) {
		return departementRepository.findById(DepartementId).orElseThrow(() -> new ResourceNotFoundException());
	}

	@PutMapping(value="/departments/{id}/edit")
	public Departement updateDepartement(@PathVariable(value = "id") Long DepartementId,
			@Valid @RequestBody Departement DepartementDetails) {

		Departement Departement = departementRepository.findById(DepartementId)
				.orElseThrow(() -> new ResourceNotFoundException());

		Departement.setDepartementName(DepartementDetails.getDepartementName());

		Departement updatedDepartement = departementRepository.save(Departement);
		return updatedDepartement;
	}

	@DeleteMapping(value="/departments/{id}")
	public ResponseEntity<?> deleteDepartement(@PathVariable(value = "id") Long DepartementId) {
		Departement Departement = departementRepository.findById(DepartementId)
				.orElseThrow(() -> new ResourceNotFoundException());

		departementRepository.delete(Departement);

		return ResponseEntity.ok().build();
	}

}
