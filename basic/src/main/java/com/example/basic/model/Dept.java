package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
// @ToString(exclude = { "emps" }) 

public class Dept {
    @Id
    @GeneratedValue
    Integer deptno;
    String dname;
    String loc;

    @JsonIgnore //출력을 막음
    // @OneToMany(mappedBy = "dept" , fetch = FetchType.EAGER) 
    @OneToMany(mappedBy = "dept")
    List<Emp> emps = new ArrayList<>(); // 외래키x , 필요할때 데이터베이스에서 조회
                                        //eager로 변경되면 오류가 발생하지않음
}
