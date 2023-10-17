package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Food;

@Repository 
public interface FoodRepository extends JpaRepository<Food, Integer>{
    
}
