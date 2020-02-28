package com.zouwu.trade.controller;

import com.zouwu.trade.dto.ApiResponse;
import com.zouwu.trade.model.User;
import com.zouwu.trade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("register")
    public ApiResponse<String> register (@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("login")
    public ApiResponse<User> login(@RequestParam("schoolNo") String schoolNo, @RequestParam("password") String password){
        return userService.login(schoolNo, password);
    }

    @GetMapping("select")
    public ApiResponse<User> select(){
        return userService.select();
    }

    @PostMapping("update")
    public ApiResponse<String> update(@RequestBody User user){
        return userService.update(user);
    }

}
