package com.project.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.home.entity.City;
import com.project.home.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	
	public List<City> getCitiesByProvinceId(Long id)
	{
		return cityRepository.getCitiesByProvinceId(id);
	}

}
