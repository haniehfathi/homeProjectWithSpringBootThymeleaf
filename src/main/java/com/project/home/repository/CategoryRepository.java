package com.project.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.home.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query("select c from Category c  where (  :#{#title} is null or " +
            "c.title like concat('%',:#{#title},'%'))" )
    List<Category> findBySearch(String title);
}
