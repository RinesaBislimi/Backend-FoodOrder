package com.foodwebsite.service;

import com.foodwebsite.config.JwtProvider;
import com.foodwebsite.model.User;
import com.foodwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepositoryl;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user=findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepositoryl.findByEmail(email);
        if(user == null){
            throw new Exception("user not found");
        }
        return user;
    }
}