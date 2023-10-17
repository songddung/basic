package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "owner")
public class Animal {
    @Id
    int id;
    String name;
    int age;
    
    @JsonIgnore
    @ManyToOne
    Owner owner;


    @OneToMany(mappedBy = "animal")
    List<Product> products = new ArrayList<>();
}
