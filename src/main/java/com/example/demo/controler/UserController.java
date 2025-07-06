package com.example.demo.controler;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.model.exceptions.exceptions.BusinessRules;
import com.example.demo.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/signIn")
    public ResponseEntity save(@RequestBody UserDTO userDTO) {
        try {
            User user = new User(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName());
            User userSave = userService.save(user);
            return new ResponseEntity(userSave, HttpStatus.CREATED);

        } catch (BusinessRules e) {
            return new ResponseEntity(new BusinessRules("Sign In error"), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(path = "/logIn")
    public ResponseEntity<User> getUser(@RequestBody UserDTO userDTO) {
        try {
            User userReq = new User( userDTO.getLogin(), userDTO.getPassword() );
            User userRes = userService.findUser( userReq );

           return new ResponseEntity<>(userRes, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new BusinessRules("Log in error");
        }
    }
}
