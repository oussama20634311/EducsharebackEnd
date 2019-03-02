package com.educshare.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educshare.entities.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long>{

}

