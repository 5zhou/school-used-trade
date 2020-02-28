package com.zouwu.trade.dao;

import com.zouwu.trade.model.ProductOrder;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ProductOrderMapper {
    
    @Delete({
        "delete from product_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    
    @Insert({
        "insert into product_order(product_id,",
        "user_id, create_time)",
        "values (#{productId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER}, datetime(CURRENT_TIMESTAMP,'localtime'))"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(ProductOrder record);

    
    @Select({
        "select",
        "id, product_id, user_id, create_time",
        "from product_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR)
    })
    ProductOrder selectByPrimaryKey(Integer id);

    
    @Select({
        "select",
        "id, product_id, user_id, create_time",
        "from product_order"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR)
    })
    List<ProductOrder> selectAll();

    
    @Update({
        "update product_order",
        "set product_id = #{productId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProductOrder record);

    @Select({
            "select",
            "id, product_id, user_id, create_time",
            "from product_order",
            "where product_id = #{productId,jdbcType=INTEGER}",
            "and user_id = #{userId}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR)
    })
    ProductOrder selectByUserIdAndProductId(Integer userId, Integer productId);
}