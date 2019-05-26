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

import com.educshare.entities.Course;
import com.educshare.reposistory.CourseRepository;



@RestController
@CrossOrigin(origins = "*")
public class CourseController {

	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/courses")
	public List<Course> getAllCourse() {
		return (List<Course>) courseRepository.findAll();
	}

	@PostMapping("/courses")
	public Course getCourseById(@Valid @RequestBody Course course) {
		return courseRepository.save(course);
	}

	@GetMapping("/courses/{id}")
	public Course getCourseById(@PathVariable(value = "id") Long courseId) {
		return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException());
	}

	@PutMapping("/courses/{id}/edit")
	public Course updateDepartement(@PathVariable(value = "id") Long CourseId,
			@Valid @RequestBody Course CourseDetails) {

		Course course = courseRepository.findById(CourseId).orElseThrow(() -> new ResourceNotFoundException());

		course.setCourseName(CourseDetails.getCourseName());

		Course updatedCourse = courseRepository.save(course);
		return updatedCourse;
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<?> deleteUserType(@PathVariable(value = "id") Long userTypeId) {

		Course course = courseRepository.findById(userTypeId).orElseThrow(() -> new ResourceNotFoundException());
		courseRepository.delete(course);
		return ResponseEntity.ok().build();
	}

}
