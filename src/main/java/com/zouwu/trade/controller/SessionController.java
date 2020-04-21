package com.zouwu.trade.controller;

import com.zouwu.trade.listener.SessionListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class SessionController {


    @GetMapping("sessionTest")
    public void test(HttpSession httpSession) {
        System.out.println(httpSession.getId());
    }

    @GetMapping("invalid")
    public void invalid() {
        for (HttpSession httpSession : SessionListener.sessionSet) {
            httpSession.invalidate();
        }
    }

}
