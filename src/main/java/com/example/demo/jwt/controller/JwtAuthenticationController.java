package com.example.demo.jwt.controller;

import java.util.Objects;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.jwt.config.JwtTokenUtil;
import com.example.demo.jwt.model.JwtRequest;
import com.example.demo.jwt.model.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> createNewUSer(@RequestBody User user)
    {
        boolean result=userService.userSignUp(user);
        if(result!=false)
        {
            return new ResponseEntity<>("User Signed Up", HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User Already exist");
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception{
        String email=authenticationRequest.getEmail();
        String password=authenticationRequest.getPassword();
        User isExistingUser=userRepository.findByEmail(email);
        if(isExistingUser!=null){
            if(isExistingUser.getPassword().equals(password)){
                final UserDetails userDetails=userService.loadUserByUsername(email);
                final String token=jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtResponse(token));
            }
            else{
                throw new Exception("BAD_CREDENTIALS");
            }
        }
        throw new UserNotFoundException("User not found, "+email);
    }

}
