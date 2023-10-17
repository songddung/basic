package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Emp;
import com.example.basic.model.Food;
import com.example.basic.model.Owner;
import com.example.basic.model.Product;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.FoodRepository;
import com.example.basic.repository.OwnerRepository;
import com.example.basic.repository.ProductRepository;

@Controller
public class ThymeleafController {

    @GetMapping("userList")
    public String userList(Model model) {
        List<Map<String, Object>> userList = new ArrayList<>();
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "a");
        user.put("userName", "apple");
        user.put("userAge", 21);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "b");
        user.put("userName", "banana");
        user.put("userAge", 22);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "c");
        user.put("userName", "carrot");
        user.put("userAge", 23);
        userList.add(user);
        model.addAttribute("userList", userList);
        return "userList";
    }

    @Autowired
    EmpRepository empRepository;

    @GetMapping("html/emp")
    public String htmlEmp(Model model, @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Emp> empList = empRepository.findAll(pageable);

        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("empList", empList);

        return "html/emp";

    }
    @Autowired
    FoodRepository foodRepository;

    @GetMapping("html/food")
    public String htmlFood(Model model, @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Food> foodList = foodRepository.findAll(pageable);
        System.out.println(foodList);
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("foodList", foodList);

        return "html/food";

    }

    // @Autowired
    // OwnerRepository ownerRepository;

    // @GetMapping("html/owner")
    // public void htmlowner(Model model) {
    //     List<Owner> owner = ownerRepository.findAll();
    //     model.addAttribute("OwnerList", owner);
    // }

    @Autowired
    ProductRepository productRepository;

    @GetMapping("html/product")
    public void htmlproduct(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("ProductsList", products);
    }

    @GetMapping("linkUrl")
    public String linkUrl(
            Model model, @RequestParam(defaultValue = "1") int page) {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "html/linkUrl";
    }

    @GetMapping("user")
    public String user(Model model) {
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "z");
        user.put("userName", "zoo");
        user.put("userAge", 25);
        model.addAttribute("user", user);
        return "user";
    }
}
