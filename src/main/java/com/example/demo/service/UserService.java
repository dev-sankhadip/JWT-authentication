package com.example.demo.service;


import com.example.demo.jwt.model.JwtRequest;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public boolean userSignUp(User user)
    {
        boolean result=true;
        String email=user.getEmail();
        String password=user.getPassword();
        User existingUser=userRepository.findByEmail(email);
        if(existingUser==null) {
            String userId=getRandomStringId(7);
            User newUser=new User();
            newUser.setId(userId);
            newUser.setEmail(email);
            newUser.setPassword(password);
            userRepository.save(newUser);
            result=true;
        }
        else {
            result=false;
        }
        return result;
    }

    public String getRandomStringId(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789" +"abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}
