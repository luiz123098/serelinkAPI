package com.example.demo.controler;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.model.exceptions.exceptions.BusinessRules;
import com.example.demo.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> getUser(@RequestBody UserDTO userDTO) {
        User user = userService.findUser(userDTO.getLogin(), userDTO.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping(path = "delete-user")
    public ResponseEntity deleteUser(@RequestBody UserDTO userDTO) {
        try {
            User userRes = userService.findUser(userDTO.getLogin(), userDTO.getPassword());
            userService.deleteById(userRes);
            return new ResponseEntity(new BusinessRules("User deleted"), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            throw new BusinessRules("Error deleting user");
        }
    }
}
