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

import com.educshare.entities.Faq;
import com.educshare.reposistory.FaqRepository;

@RestController
@CrossOrigin(origins = "*")
public class FaqController {

	@Autowired
	FaqRepository faqRepository;


	@GetMapping("/faqs")
	public List<Faq> getAllFaqs() {
		return (List<Faq>) faqRepository.findAll();
	}

		
	@PostMapping("/faqs")
	public Faq createFaq(@Valid @RequestBody Faq faq) {
		return faqRepository.save(faq);
	}
	
	@GetMapping("/faqs/{id}")
	public Faq getFaqById(@PathVariable(value = "id") Long FaqId) {
		return faqRepository.findById(FaqId)
				.orElseThrow(() -> new ResourceNotFoundException());
	}

	
	// Delete a Faq
	@DeleteMapping("/faqs/{id}")
	public ResponseEntity<?> deleteFaq(@PathVariable(value = "id") Long FaqId) {
		Faq Faq = faqRepository.findById(FaqId)
				.orElseThrow(() -> new ResourceNotFoundException());

		faqRepository.delete(Faq);

		return ResponseEntity.ok().build();
	}
}

