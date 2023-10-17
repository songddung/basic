package com.example.basic.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Owner;
import com.example.basic.repository.OwnerRepository;
import com.example.basic.util.Encrypt;

@Controller
@RequestMapping("/auth") // http://localhost:8080/auth/login
public class AuthController {
    @Autowired
    HttpSession session;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Encrypt encrypt;


    // 아이디 중복검사
    @GetMapping("/id-check")
    @ResponseBody
    public String idCheck(@ModelAttribute Owner owner) {
        int id = owner.getId();
        Optional<Owner> opt = ownerRepository.findById(id);
        if (opt != null) { // 값이 있음, 가입불가
            return "가입불가";
        } else {// 값이 없음 , 가입가능
            return "가입가능";
        }

    }

    @GetMapping("/signup")
    public String signup() {

        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signupPost(@ModelAttribute Owner owner) {
        System.out.println(owner);
        //비밀번호 암호화
        String newpassword =
        passwordEncoder.encode(owner.getPassword());
        owner.setPassword(newpassword);

        int id = owner.getId();
        String password = owner.getPassword();
        Optional<Owner> result = ownerRepository.findById(id);
        if (result != null) {
            return "auth/fail2";
        } else {
            ownerRepository.save(owner);
            session.setAttribute("id", id);
            session.setAttribute("password", password);
        }
        return "redirect:/auth/signin"; // redirect : post를 마무리하기 위해 사용
    }


    
    @GetMapping("/signin")
    public String signin() throws NoSuchAlgorithmException {
        String pwd = passwordEncoder.encode("1");
        System.out.println(pwd);

        String pwd2 = encrypt.encode("1");
        System.out.println(pwd2);

        return "auth/signin";
    }

    @PostMapping("/signin")
    public String signinPost(@ModelAttribute Owner owner) {
        int id = owner.getId();
        // String password = owner.getPassword();
        // Owner result = ownerRepository.findByIdAndPassword(id, password);
        Optional<Owner> result = ownerRepository.findById(id);

        //값이 없거나 비어있는지 확인
        boolean ispresent = result.isPresent();
        
        if(ispresent){
        String password = result.get().getPassword();
        String name = result.get().getName();

        Boolean isMatch = 
            passwordEncoder.matches(owner.getPassword(), password);

        if (isMatch) {
            session.setAttribute("id", id);
            session.setAttribute("name", name);
            session.setAttribute("password", password);
            return "redirect:/board/list";
        } else {
            return "auth/fail";
        }
    } return "auth/fail3";
        // ownerRepository.save(owner); 정보수정도 가능

    }

    @GetMapping("/login")
    public String login() {
        session.setAttribute("user_id", "HRD");
        return "redirect:/html/emp"; // 메인페이지로 넘기기(redirect : 재요청)
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("user_id");
        // return "redirect:/html/emp";
        return "auth/logout";
    }
}
