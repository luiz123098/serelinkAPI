package com.example.demo.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login",nullable = false)
    private String login;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "name",nullable = false)
    private String name;

    public User(String login, String password, String name) {this.login = login;this.password = password;this.name = name;}
    public User(String login, String password) {this.login = login;this.password = password;}

    public Long getId() {return id;    }
    public void setId(Long id) {this.id = id;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
