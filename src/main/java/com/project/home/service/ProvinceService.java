package com.project.home.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.home.entity.Province;
import com.project.home.repository.ProvinceRepository;

@Service
public class ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	
	public List<Province> getAllProvince()
	{
		return provinceRepository.findAll();
	}

	
}
