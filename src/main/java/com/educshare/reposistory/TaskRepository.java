package com.educshare.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.educshare.entities.Task;

@RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task, Long>{

}
