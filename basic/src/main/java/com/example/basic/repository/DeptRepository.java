package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer>{
    
}
