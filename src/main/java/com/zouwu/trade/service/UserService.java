package com.zouwu.trade.service;

import com.zouwu.trade.dao.UserMapper;
import com.zouwu.trade.dto.ApiResponse;
import com.zouwu.trade.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {


    @Resource
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;


    public ApiResponse<String> register(User user) {
        User user1 = userMapper.selectBySchoolNo(user.getSchoolNo());
        if(user1 != null){
            return new ApiResponse<>(500, "该学号已被注册", null);
        }
        String passwordHash = DigestUtils.sha1Hex(user.getPassword());
        user.setPassword(passwordHash);
        userMapper.insert(user);
        return new ApiResponse<>(0, "success", null);
    }

    public ApiResponse<User> login(String schoolNo, String password) {
        User user = userMapper.selectBySchoolNoAndPassword(schoolNo, DigestUtils.sha1Hex(password));
        if (user == null) {
            return new ApiResponse<>(500, "用户名或密码错误", null);
        }
        request.getSession().setAttribute("schoolNo", user.getSchoolNo());
        request.getSession().setAttribute("name", user.getName());
        request.getSession().setAttribute("id", user.getId());
        return new ApiResponse<>(0, "success", user);
    }

    public ApiResponse<User> select() {
        int id = (int) request.getSession().getAttribute("id");
        User user = userMapper.selectByPrimaryKey(id);
        return new ApiResponse<>(0, "success", user);
    }

    public ApiResponse<String> update(User user) {
        userMapper.updateData(user);
        return new ApiResponse<>(0, "success", "");
    }


}
