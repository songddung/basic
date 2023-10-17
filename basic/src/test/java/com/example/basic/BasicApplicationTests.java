package com.example.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.basic.model.Dept;
import com.example.basic.model.Emp;
import com.example.basic.model.Product;
import com.example.basic.repository.DeptRepository;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.OwnerRepository;
import com.example.basic.repository.ProductRepository;

@SpringBootTest
class BasicApplicationTests {
	@Autowired 
	DeptRepository deptRepository;
	@Autowired 
	EmpRepository empRepository;

	@Test //junit 
	void 급여조회하기() {
	// // 	List<Emp> list = empRepository.findBySalGreaterThanEqual(2000);
	// // 	System.out.println(list);
		
	// // }
	// // @Test //junit 
	// // void Emp테이블의job으로조회하기() {
	// // 	List<Emp> list = empRepository.findByJob("MANAGER");
	// 	System.out.println(list);
		
	}


	@Test //junit 
	void Emp테이블조회하기() {
		List<Emp> list = empRepository.findAll();
		System.out.println(list);
		
	}
	@Test @Transactional // eager로 변경
	void Dept테이블조회하기() {
		 List<Dept> list = deptRepository.findAll();
		System.out.println(list);
	}



	@Autowired
	ProductRepository productRepository;
    @Test
	void Product(){
		 Optional<com.example.basic.model.Product> opt = productRepository.findById(1);
		Product product = opt.get();
		System.out.println(product);
	}
	
	
	@Autowired
	OwnerRepository ownerRepository;
    @Test @Transactional
	void Owner(){
		 Optional<com.example.basic.model.Owner> opt = ownerRepository.findById(1);
		com.example.basic.model.Owner owner = opt.get();
		System.out.println(owner);
	}
}
