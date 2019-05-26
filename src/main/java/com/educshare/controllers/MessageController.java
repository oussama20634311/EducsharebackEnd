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

import com.educshare.entities.Message;
import com.educshare.reposistory.MessageRepository;


@RestController
@CrossOrigin(origins = "*")
public class MessageController {

	@Autowired
	MessageRepository messageRepository;


	@GetMapping("/messages")
	public List<Message> getAllMessages() {
		return (List<Message>) messageRepository.findAll();
	}

		
	@PostMapping("/messages")
	public Message createMessage(@Valid @RequestBody Message message) {
		return messageRepository.save(message);
	}
	
	@GetMapping("/messages/{id}")
	public Message getMessageById(@PathVariable(value = "id") Long MessageId) {
		return messageRepository.findById(MessageId)
				.orElseThrow(() -> new ResourceNotFoundException());
	}

	
	// Delete a Message
	@DeleteMapping("/messages/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable(value = "id") Long MessageId) {
		Message Message = messageRepository.findById(MessageId)
				.orElseThrow(() -> new ResourceNotFoundException());

		messageRepository.delete(Message);

		return ResponseEntity.ok().build();
	}
}

