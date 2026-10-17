package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/admin")
    public String admin() {

        return "Bienvenido Administrador";
    }
}