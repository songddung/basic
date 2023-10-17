package com.example.basic.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Emp;
import java.util.List;


@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer>{
    // Page<Emp> findAll(Pageable pageable);
}
