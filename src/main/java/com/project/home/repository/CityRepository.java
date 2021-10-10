package com.project.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.home.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>  {
	
	@Query(nativeQuery = true,value="select * from city where province_id= :#{#id} ")
	public List<City> getCitiesByProvinceId(long id);

}
