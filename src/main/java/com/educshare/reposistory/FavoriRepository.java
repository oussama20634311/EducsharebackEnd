package com.educshare.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educshare.entities.Favori;

@Repository
public interface FavoriRepository extends JpaRepository<Favori, Long> {

}
