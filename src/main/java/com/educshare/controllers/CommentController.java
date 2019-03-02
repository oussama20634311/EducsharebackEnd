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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.educshare.entities.Comment;
import com.educshare.reposistory.CommentRepository;



@RestController
//@RequestMapping("/")
public class CommentController {

	@Autowired
	CommentRepository commentRepository;

	@GetMapping(value="/comments")
	public List<Comment> getAllComments() {
		return (List<Comment>) commentRepository.findAll();
	}

		
	@PostMapping(value="/comments")
	@ResponseBody
	public Comment createComment(@Valid @RequestBody Comment comment) {
		
		return commentRepository.save(comment);
	}
	
	@GetMapping(value="/comments/{id}")
	public Comment getCommentById(@PathVariable(value = "id") Long CommentId) {
		return commentRepository.findById(CommentId)
				.orElseThrow(() -> new ResourceNotFoundException());
	}

	
	// Delete a Comment
	@DeleteMapping(value="/comments/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long CommentId) {
		Comment Comment = commentRepository.findById(CommentId)
				.orElseThrow(() -> new ResourceNotFoundException());

		commentRepository.delete(Comment);

		return ResponseEntity.ok().build();
	}

}
