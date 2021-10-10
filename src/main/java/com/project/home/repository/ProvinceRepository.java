package com.project.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.home.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{

}
