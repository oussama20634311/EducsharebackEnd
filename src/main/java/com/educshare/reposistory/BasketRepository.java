package com.educshare.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educshare.entities.Basket;



@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>{

}
