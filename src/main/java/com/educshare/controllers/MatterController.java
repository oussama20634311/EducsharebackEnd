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

import com.educshare.entities.Matter;
import com.educshare.reposistory.MatterRepository;


@RestController
@CrossOrigin(origins = "*")
public class MatterController {

	@Autowired
	MatterRepository matterRepository;

	@GetMapping("/matters")
	public List<Matter> getAllMatters() {
		return (List<Matter>) matterRepository.findAll();
	}

	@PostMapping("/matters")
	public Matter createMatter(@Valid @RequestBody Matter matter) {
		return matterRepository.save(matter);
	}

	@GetMapping("/matters/{id}")
	public Matter getMatterById(@PathVariable(value = "id") Long MatterId) {
		return matterRepository.findById(MatterId).orElseThrow(() -> new ResourceNotFoundException());
	}

	@PutMapping("/matters/{id}/edit")
	public Matter updateMatter(@PathVariable(value = "id") Long MatterId, @Valid @RequestBody Matter MatterDetails) {

		Matter Matter = matterRepository.findById(MatterId).orElseThrow(() -> new ResourceNotFoundException());
		Matter.setMatterName(MatterDetails.getMatterName());

		Matter updatedMatter = matterRepository.save(Matter);
		return updatedMatter;
	}

	@DeleteMapping("/matters/{id}")
	public ResponseEntity<?> deleteDepartement(@PathVariable(value = "id") Long MatterId) {
		Matter Matter = matterRepository.findById(MatterId).orElseThrow(() -> new ResourceNotFoundException());
		matterRepository.delete(Matter);
		return ResponseEntity.ok().build();
	}

}
