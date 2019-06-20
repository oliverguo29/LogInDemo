package com.ao.test;

import com.ao.dao.UserDao;
import com.ao.domain.User;
import org.junit.Test;

/**
 * Junit test
 */

public class UserDaoTest {

    @Test
    public void testLogIn(){
        User u=new User("ao","qwecvb123");



        UserDao dao = new UserDao();

        User u2=dao.login(u);

        System.out.println(u2.getUsername()+"***");
    }
}
