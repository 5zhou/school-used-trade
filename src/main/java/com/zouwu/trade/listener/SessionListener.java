package com.zouwu.trade.listener;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.CopyOnWriteArraySet;

@WebListener
public class SessionListener implements HttpSessionListener {

    public static final CopyOnWriteArraySet<HttpSession> sessionSet = new CopyOnWriteArraySet<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session add: " + se.getSession().getId());
        sessionSet.add(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session delete: " + se.getSession().getId());
        sessionSet.remove(se.getSession());

    }
}
