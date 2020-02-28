package com.zouwu.trade.model;

public class Product {
    
    private Integer id;

    
    private String name;

    
    private String desc;

    
    private Integer price;

    
    private Integer status;

    
    private String comment;

    
    private Integer userId;

    
    private String time;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public String getDesc() {
        return desc;
    }

    
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    
    public Integer getPrice() {
        return price;
    }

    
    public void setPrice(Integer price) {
        this.price = price;
    }

    
    public Integer getStatus() {
        return status;
    }

    
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    public String getComment() {
        return comment;
    }

    
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    
    public Integer getUserId() {
        return userId;
    }

    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    public String getTime() {
        return time;
    }

    
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}