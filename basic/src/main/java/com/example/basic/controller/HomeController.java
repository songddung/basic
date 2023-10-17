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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Emp;
import com.example.basic.model.Food;
import com.example.basic.model.Player;
import com.example.basic.model.Shop;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.FoodRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.ShopRepository;

import lombok.Data;


@Data // get , set 자동으로 생성해줌
class Phone { 
    String brand;
    int price;





    // String getBrand(){
    //     return this.brand;
    // }

    // void setBrand(String barnd){
    //     this.brand = barnd;
    // }

}

@Controller
public class HomeController {
    // @Autowired
    // EmpRepository empRepository;

    // @GetMapping("/emp")
    // @ResponseBody
    // public Emp emp(@ModelAttribute Emp emp){
    //     // Emp emp = new Emp();
    //     // emp.setEmpno(2);
    //     // emp.setEname("abcd");
    //     // emp.setJob("developer");
    //     // emp.setMgr(100);
    //     empRepository.save(emp);
    //     return emp;
    // }

     @Autowired
    ShopRepository shopRepository;

    @GetMapping("/shop")
    @ResponseBody
    public Shop shop(@ModelAttribute Shop shop){
        // Shop shop = new Shop();
        // shop.shopId();
        // shop.shopName();
        // shop.shopDesc();
        // shop.rsetDate();
        // shop.parkingInfo();
        // shop.imgPath();

        shopRepository.save(shop);
        return shop;
    }

    @Autowired
    PlayerRepository playerRepository;
    
    @GetMapping("/player")
    @ResponseBody
    public List<Player> player(){
        List<Player> list = playerRepository.findAll();
        return list;
    }



    @Autowired
    FoodRepository foodRepository;
    @GetMapping("/food")
    @ResponseBody
    public String food(){
        Food food = new Food();
        food.setId(21357077);
        food.setName("충무김밥");
        food.setAddress("경상남도 거제시 동부면 거제대로 909");
        food.setDesc("거제도의 명소 학동몽돌해수욕장에 자리하고 있으며 학동교회 맞은편 도로변에 위치하고 있습니다. 거제도의 향토음식 멍게비빔밥을 정성스럽게 준비하여 맛있게 제공하고 있으며, 친절히 손님을 맞이합니다. 거제8미전문음식점으로 지정되어 깨끗하게 운영하며 멍게·성게비빔밥이 맛이 있습니다.");
        food.setTel("055-635-7159");
        food.setLatitude("34.77246370");
        food.setLongitude("128.6378563");
        foodRepository.save(food);
        return "입력완료";
    }

    // @GetMapping("/emp-all-data")
    // @ResponseBody
    // public List<Emp> empAllData(){
    //     List<Emp> list = empRepository.findAll();
    //     System.out.println(list);
    //     return list;
    // }

   @GetMapping("pagination")
   public String pagination(
           Model model, @RequestParam(defaultValue = "1") int page) {
       int startPage = (page - 1) / 10 * 10 + 1;
       int endPage = startPage + 9;
       model.addAttribute("startPage", startPage);
       model.addAttribute("endPage", endPage);
       model.addAttribute("page", page);
       return "pagination";
   }


    @GetMapping("/foodList")
    @ResponseBody
    public List<Food> foodList(@RequestParam(defaultValue = "1") int p){
        // Sort sort = Sort.by("name");
        Pageable page = PageRequest.of(p - 1, 10);
        Page<Food> list = foodRepository.findAll(page);
        return list.getContent();
    }



    @GetMapping("/shopList")
    @ResponseBody
    public List<Shop> shopList(){
        List<Shop> list = shopRepository.findAll();
        return list;
    }






    @RequestMapping("/html/exam")
    public void html(){
        Phone p = new Phone();
        p.setBrand(null);
        
    }


    
    @RequestMapping("/json/exam")
    @ResponseBody
    public Map<String,Object> jsonExam() {
        Map<String,Object> map = new HashMap<String,Object>();
        
        map.put("count", 2);
        
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("name", "가");
        map2.put("userId", "A");
        list.add(map2);


        map2 = new HashMap<String,Object>();
        map2.put("name", "나");
        map2.put("userId", "B");
        list.add(map2);
        map.put("list", list);
        
        return (map);
    }


    @RequestMapping("/phone") @ResponseBody
    public Phone phone(){
        Phone phone = new Phone();
        phone.setBrand("LG");
        phone.setPrice(10000);
        return phone;
    }



    



    @RequestMapping("/")
    public String home(@RequestParam(defaultValue = "zzz", required = false) String UserId,
    @RequestParam String UserPw,
    @ModelAttribute Phone phone) {
        System.out.println(UserId + UserPw);
        System.out.println(phone);
        return ("home");
    }

    @RequestMapping("/json/{user}.do")
    @ResponseBody // 응답
    public Map<String,Object> path(@PathVariable String user) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "hong");
        map.put("name2", 1234);
        map.put("user",user);
        return (map);
    }
    @RequestMapping("/json")
    @ResponseBody // 응답
    public Map<String,Object> json() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "hong");
        map.put("name2", 1234);
        return (map);
    }

    //같은 주소라도 RequestMapping method가 다르면 사용가능
    // @RequestMapping(method = RequestMethod.GET, value = "/json")
    // @ResponseBody // 응답
    // public Map<String,Object> json() {
    //     Map<String,Object> map = new HashMap<String,Object>();
    //     map.put("name", "hong");
    //     map.put("name2", 1234);
    //     return (map);
    // }
    // @RequestMapping(method = RequestMethod.POST, value = "/json")
    // @ResponseBody // 응답
    // public Map<String,Object> jsonPost() {
    //     Map<String,Object> map = new HashMap<String,Object>();
    //     map.put("name", "hong");
    //     map.put("name2", 1234);
    //     return (map);
    // }
}
