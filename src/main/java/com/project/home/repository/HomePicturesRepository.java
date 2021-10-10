package com.project.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.home.entity.HomePictures;

@Repository
public interface HomePicturesRepository extends JpaRepository<HomePictures, Long>{
	

}
