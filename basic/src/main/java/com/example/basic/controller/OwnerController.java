package com.example.basic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.model.Owner;
import com.example.basic.repository.OwnerRepository;

@Controller
@RequestMapping()
public class OwnerController {
    
  @Autowired
    OwnerRepository ownerRepository;

    @GetMapping("html/owner")
    public void htmlowner(Model model) {
        List<Owner> owner = ownerRepository.findAll();
        model.addAttribute("OwnerList", owner);
     
    }




    @Autowired
    HttpSession session;

     @GetMapping("/ownerLogin")
    public String ownerlogin(
     @RequestParam("id") int id,
    @RequestParam("name") String name){

        Owner owner = ownerRepository.findByIdAndName(id,name);
        
        
        if(owner != null){
          session.setAttribute("id", id);
        session.setAttribute("name", name);
        
        return "redirect:/html/emp"; 
        }else{

        return "auth/logout";
        }
    }










    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("user_id");
        // return "redirect:/html/emp";
        return "auth/logout";
    }
}
