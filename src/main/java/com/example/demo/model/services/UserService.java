package com.example.demo.model.services;

import com.example.demo.model.entity.User;
import com.example.demo.model.exceptions.exceptions.BusinessRules;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        try {
                return userRepository.save(user);
        } catch (Exception e) {
            throw new BusinessRules("Fail to save");
        }
    }

    public User findUser(User user){
        return userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword())
                .orElseThrow(() -> new BusinessRules("User not found"));
    }
}
