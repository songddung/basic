package com.example.basic.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString(exclude = { "board" }) 
public class FileAtch {
    @Id
    @GeneratedValue
    int id;
    String originalName;
    String saveName;
    Date creDate;

    @ManyToOne
    Board board;
}
