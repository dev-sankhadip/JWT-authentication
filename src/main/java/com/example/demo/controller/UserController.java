package com.example.demo.controller;


import com.example.demo.exception.UserNotFoundException;
import com.example.demo.jwt.model.JwtRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/check")
    public ResponseEntity<?> checkToken()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        String password=userDetails.getPassword();
        System.out.println(username+" "+password);
        return ResponseEntity.status(HttpStatus.FOUND).body("Found");
    }


//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody User user)
//    {
//        boolean result=userService.userSignUp(user);
//        if(result!=false)
//        {
//            return new ResponseEntity<>("User Signed Up", HttpStatus.CREATED);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body("User Already exist");
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@Valid @RequestBody User user)
//    {
//        User isUserExist= userService.userLogin(user);
//        if(isUserExist!=null){
//            return ResponseEntity.status(HttpStatus.OK).body("Loggedin");
//        }
//        throw new UserNotFoundException("User not found");
//    }

}
