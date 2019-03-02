package com.educshare.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.educshare.entities.Matter;

@RepositoryRestResource
public interface MatterRepository extends JpaRepository<Matter, Long> {

}
