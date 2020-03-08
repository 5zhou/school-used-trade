package com.zouwu.trade.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zouwu.trade.dto.ApiResponse;
import com.zouwu.trade.model.User;
import com.zouwu.trade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        byte[] bytes = Files.readAllBytes(Paths.get("D:\\1.txt"));
        String s = new String(bytes, StandardCharsets.UTF_8);
        Map<String, Object> map = gson.fromJson(s, new TypeToken<Map<String, Object>>(){}.getType());
        List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
        List<String> dataList = new ArrayList<>();
        for (Map<String, Object> datum : data) {
            StringBuilder sb = new StringBuilder();
            sb.append(datum.get("name")).append(",");
            sb.append(datum.get("mobile_phone")).append(",");
            sb.append(datum.get("mail")).append(",");
            sb.append(datum.get("graduation_school")).append(",");
            sb.append(datum.get("working_city"));
            dataList.add(sb.toString());
        }
        Files.write(Paths.get("D:\\2.txt"), dataList);


    }

}
