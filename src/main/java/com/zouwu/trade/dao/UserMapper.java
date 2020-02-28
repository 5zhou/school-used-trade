package com.zouwu.trade.dao;

import com.zouwu.trade.model.User;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserMapper {

    @Delete({
            "delete from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @Insert({
            "insert into user (password, ",
            "name, sex, school_no, ",
            "phone, mail, register_time)",
            "values (#{password,jdbcType=VARCHAR}, ",
            "#{name,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{schoolNo,jdbcType=VARCHAR}, ",
            "#{phone,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, datetime(CURRENT_TIMESTAMP,'localtime'))"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User record);


    @Select({
            "select",
            "id, password, name, sex, school_no, phone, mail, register_time",
            "from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
            @Result(column = "school_no", property = "schoolNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "register_time", property = "registerTime", jdbcType = JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer id);


    @Select({
            "select",
            "id, password, name, sex, school_no, phone, mail, register_time",
            "from user"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
            @Result(column = "school_no", property = "schoolNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "register_time", property = "registerTime", jdbcType = JdbcType.VARCHAR)
    })
    List<User> selectAll();


    @Update({
            "update user",
            "set password = #{password,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "sex = #{sex,jdbcType=VARCHAR},",
            "school_no = #{schoolNo,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},",
            "register_time = #{registerTime,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "select",
            "id, name, sex, school_no, phone, mail, register_time",
            "from user",
            "where school_no = #{schoolNo} and password = #{password}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
            @Result(column = "school_no", property = "schoolNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "register_time", property = "registerTime", jdbcType = JdbcType.VARCHAR)
    })
    User selectBySchoolNoAndPassword(@Param("schoolNo") String schoolNo, @Param("password") String password);

    @Select({
            "select",
            "id, name, sex, school_no, phone, mail, register_time",
            "from user",
            "where school_no = #{schoolNo}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
            @Result(column = "school_no", property = "schoolNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "register_time", property = "registerTime", jdbcType = JdbcType.VARCHAR)
    })
    User selectBySchoolNo(@Param("schoolNo") String schoolNo);


    @Update({
            "update user",
            "set",
            "name = #{name,jdbcType=VARCHAR},",
            "sex = #{sex,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateData(User record);
}