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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educshare.entities.Document;
import com.educshare.entities.Favori;
import com.educshare.reposistory.FavoriRepository;

@RestController
@CrossOrigin(origins = "*")
public class FavoriController {

	@Autowired
	FavoriRepository favoriRepository;
	@GetMapping("/favoris")
	public List<Favori> getAllfavoris() {
		return (List<Favori>) favoriRepository.findAll();
	}

	@PostMapping("/favoris")
	public Favori createfavori(@Valid @RequestBody Favori favori) {
		return favoriRepository.save(favori);
	}

	@GetMapping("/favoris/{userId}")
	public List<Favori> getfavoriByUserId(@Valid @RequestBody @PathVariable(value = "userId") Long userId) {
		return favoriRepository.findByUserId(userId);
	}

	@DeleteMapping("/favoris/{id}")
	public ResponseEntity<?> deleteDepartement(@PathVariable(value = "id") Long favoriId) {
		Favori favori = favoriRepository.findById(favoriId).orElseThrow(() -> new ResourceNotFoundException());
		favoriRepository.delete(favori);
		return ResponseEntity.ok().build();
	}

}
