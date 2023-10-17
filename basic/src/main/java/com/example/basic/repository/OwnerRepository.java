package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Owner;
import java.util.List;


@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
//    public abstract
    Owner  findByIdAndName(int id,String name);
    Owner  findByIdAndPassword(int id,String password);
    
    
}
