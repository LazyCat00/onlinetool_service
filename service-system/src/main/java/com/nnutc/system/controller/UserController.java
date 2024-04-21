package com.nnutc.system.controller;


import com.nnutc.model.entity.Users;
import com.nnutc.system.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    public UsersService usersService;

    @GetMapping("/list")
    public List<Users> getList(){
        return usersService.list();
    }
}
