package com.educshare.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.educshare.entities.Course;

@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course, Long> {

}
