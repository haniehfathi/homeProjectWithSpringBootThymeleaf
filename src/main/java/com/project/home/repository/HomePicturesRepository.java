package com.project.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.home.entity.HomePictures;

@Repository
public interface HomePicturesRepository extends JpaRepository<HomePictures, Long>{

    @Modifying
    @Query(nativeQuery = true,value = "delete from home_pictures where home_id=:#{#id} ")
    void deleteByHome(Long id);
}
