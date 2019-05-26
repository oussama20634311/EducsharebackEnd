package com.educshare.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educshare.entities.AppUser;
import com.educshare.entities.Document;
import com.educshare.entities.Favori;

@Repository
public interface FavoriRepository extends JpaRepository<Favori, Long> {

	List<Favori> findByUserId(Long userId);

}
