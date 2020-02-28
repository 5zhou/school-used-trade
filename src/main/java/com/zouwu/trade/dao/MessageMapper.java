package com.zouwu.trade.dao;

import com.zouwu.trade.model.Message;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface MessageMapper {

    @Delete({
            "delete from message",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @Insert({
            "insert into message (title, ",
            "content, user_id, ",
            "is_read, create_time)",
            "values (#{title,jdbcType=VARCHAR}, ",
            "#{content,jdbcType=VARCHAR}, #{userId,jdbcType=INTEGER}, ",
            "#{isRead,jdbcType=INTEGER}, datetime(CURRENT_TIMESTAMP,'localtime'))"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Message record);


    @Select({
            "select",
            "id, title, content, user_id, is_read, create_time",
            "from message",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_read", property = "isRead", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.VARCHAR)
    })
    Message selectByPrimaryKey(Integer id);


    @Select({
            "select",
            "id, title, content, user_id, is_read, create_time",
            "from message",
            "where user_id = #{userId}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_read", property = "isRead", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.VARCHAR)
    })
    List<Message> selectByUserId(@Param("userId") Integer userId);


    @Update({
            "update message",
            "set title = #{title,jdbcType=VARCHAR},",
            "content = #{content,jdbcType=VARCHAR},",
            "user_id = #{userId,jdbcType=INTEGER},",
            "is_read = #{isRead,jdbcType=INTEGER},",
            "create_time = #{createTime,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Message record);
}