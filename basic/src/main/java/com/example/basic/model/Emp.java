package com.example.basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "dept" }) 

public class Emp {
    @Id 
    @GeneratedValue //데이터베이스가 자동증가기능 활성화
    Integer empno;
    String ename;
    String job;
    Integer mgr;
    String hiredate;
    Integer sal;
    Integer comm;
    
    

    @ManyToOne
    @JoinColumn(name = "deptno")
    Dept dept; //외래키 , 데이터베이스에서 바로 조회가능
}
