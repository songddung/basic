package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data //get,set 자동생성
public class Food {
    @Id 
    Integer id;
    String name;
    String address;
    String desc;
    String tel;
    String latitude;
    String longitude;
    
}
