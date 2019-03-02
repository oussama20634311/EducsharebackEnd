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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.educshare.entities.Favori;
import com.educshare.reposistory.FavoriRepository;

@RestController
@CrossOrigin(origins = "*")
public class FavoriController {

	@Autowired
	FavoriRepository favoriRepository;
	@GetMapping(value="/favoris")
	public List<Favori> getAllfavoris() {
		return (List<Favori>) favoriRepository.findAll();
	}

	@PostMapping(value="/favoris")
	public Favori createfavori(@Valid @RequestBody Favori favori) {
		return favoriRepository.save(favori);
	}

	@GetMapping(value="/favoris/{id}")
	public Favori getfavoriById(@PathVariable(value = "id") Long favoriId) {
		return favoriRepository.findById(favoriId).orElseThrow(() -> new ResourceNotFoundException());
	}


	@DeleteMapping(value="/favoris/{id}")
	public ResponseEntity<?> deleteDepartement(@PathVariable(value = "id") Long favoriId) {
		Favori favori = favoriRepository.findById(favoriId).orElseThrow(() -> new ResourceNotFoundException());
		favoriRepository.delete(favori);
		return ResponseEntity.ok().build();
	}

}
