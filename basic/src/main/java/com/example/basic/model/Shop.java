package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue
    Integer shopId;
    String shopName;
    String shopDesc;
    String rsetDate;
    String parkingInfo;
    String imgPath;
    
}
