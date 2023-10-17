package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data

public class Owner {
    @Id
    int id;
    String name;
    String password;


    @OneToMany(mappedBy = "owner")
    List<Animal> animals = new ArrayList<>();

    public boolean isEmpty() {
        return false;
    }

    public Owner get() {
        return null;
    }

}
