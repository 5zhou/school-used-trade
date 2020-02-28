package com.zouwu.trade.model;

public class ProductComment {
    
    private Integer commentId;

    
    private Integer productId;

    
    private Integer userId;

    
    private String comment;

    
    public Integer getCommentId() {
        return commentId;
    }

    
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    
    public Integer getProductId() {
        return productId;
    }

    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    
    public Integer getUserId() {
        return userId;
    }

    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    public String getComment() {
        return comment;
    }

    
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}