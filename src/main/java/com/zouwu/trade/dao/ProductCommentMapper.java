package com.zouwu.trade.dao;

import com.zouwu.trade.model.ProductComment;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ProductCommentMapper {
    
    @Delete({
        "delete from product_comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer commentId);

    
    @Insert({
        "insert into product_comment (product_id, ",
        "user_id, comment)",
        "values (#{productId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER}, #{comment,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "commentId", keyColumn = "comment_id")
    int insert(ProductComment record);

    
    @Select({
        "select",
        "comment_id, product_id, user_id, comment",
        "from product_comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR)
    })
    ProductComment selectByPrimaryKey(Integer commentId);

    
    @Select({
        "select",
        "comment_id, product_id, user_id, comment",
        "from product_comment"
    })
    @Results({
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR)
    })
    List<ProductComment> selectAll();

    
    @Update({
        "update product_comment",
        "set product_id = #{productId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "comment = #{comment,jdbcType=VARCHAR}",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProductComment record);
}