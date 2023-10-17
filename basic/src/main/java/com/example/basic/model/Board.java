package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@ToString(exclude = { "fileAtchs" }) 
@Entity
@Data
public class Board {
    @Id
    @GeneratedValue
    int id;
    String member;
    String title;
    String content;

    @OneToMany(mappedBy = "board")
   List<FileAtch> fileAtchs = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    List<Comment> comments = new ArrayList<>();

}
