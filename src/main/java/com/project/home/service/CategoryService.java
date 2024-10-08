package com.project.home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.home.entity.Category;
import com.project.home.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
		
	}

	public Category addCategory(Category cat){
		return categoryRepository.save(cat);

	}

	public void deleteCategory(Long id){
		 categoryRepository.deleteById(id);

	}
	public Optional<Category> findCategoryById(Long id){
		 return categoryRepository.findById(id);

	}


	public List<Category> findBySearch(Category category){
		return categoryRepository.findBySearch(category.getTitle());
	}

}
