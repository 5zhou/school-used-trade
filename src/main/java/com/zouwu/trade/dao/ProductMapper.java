package com.zouwu.trade.dao;

import com.zouwu.trade.model.Product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


@Mapper
public interface ProductMapper {

    @Delete({
            "delete from product",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @Insert({
            "insert into product (name, ",
            "desc, price, status, ",
            " user_id, ",
            "time)",
            "values (#{name,jdbcType=VARCHAR}, ",
            "#{desc,jdbcType=VARCHAR}, #{price,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
            "#{userId,jdbcType=INTEGER}, ",
            "datetime(CURRENT_TIMESTAMP,'localtime'))"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Product record);


    @Select({
            "select",
            "id, name, desc, price, status, comment, user_id, time",
            "from product",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "desc", property = "desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "comment", property = "comment", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR)
    })
    Product selectByPrimaryKey(Integer id);


    @Select({
            "select",
            "id, name, desc, price, status, comment, user_id, time",
            "from product",
            "where user_id = #{userId}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "desc", property = "desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "comment", property = "comment", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR)
    })
    List<Product> selectProductsByUserId(@Param("userId") Integer userId);


    @Select({
            "select",
            "b.id, name, desc, price, comment, a.create_time as time",
            "from product_order a",
            "left join product b on a.product_id = b.id",
            "where a.user_id = #{userId}"
    })
    List<Map<String, Object>> selectProductsByPurchaseUserId(@Param("userId") Integer userId);


    @Update({
            "update product",
            "set name = #{name,jdbcType=VARCHAR},",
            "desc = #{desc,jdbcType=VARCHAR},",
            "price = #{price,jdbcType=INTEGER},",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);

    @Select({
            "<script>",
            "select a.id, a.name, price, desc,",
            "b.name as userName, b.school_no as schoolNo, b.phone, b.mail",
            "from product a",
            "left join user b",
            "on a.user_id = b.id",
            "where status = 0 and user_id != #{userId}",
            "<if test = 'name != null'>",
            "and name like concat('%', '#{name}', '%')",
            "</if>",
            "order by a.time desc",
            "</script>"
    })
    List<Map<String, Object>> pagedQueryOnSaleProducts(@Param("userId") Integer userId, @Param("name") String name);

    @Update({
            "update product",
            "set comment = #{comment}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int addComment(@Param("id") int id, @Param("comment") String comment);

    @Update({
            "update product",
            "set status = #{status}",
            "where id = #{id}"
    })
    int updateStatus(@Param("id") int id, @Param("status") int status);
}