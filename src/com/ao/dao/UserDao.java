package com.ao.dao;
import com.ao.domain.User;
import com.ao.utils.JDBC_utils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * manipulate user table in database
 * 操作数据库中的user表
 */

public class UserDao {
    //JDBC Template
    private JdbcTemplate template= new JdbcTemplate(JDBC_utils.getDataSource());


    //login function        if username and password are correct it will return a user object else return null
    //如果用户名密码正确，返回user对象，否则返回null
    public User login(User login_user){
       try {
           //1.sql
           String sql = "select * from user where username= ? and password= ?";

           //2.call query and encapsulate result as a user;将查询结果封装为user对象
           User user = template.queryForObject(sql,
                   new BeanPropertyRowMapper<User>(User.class),
                   login_user.getUsername(), login_user.getUserpassword());
           return user;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }
}
