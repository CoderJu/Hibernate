package cn.eleven.dao;


import cn.eleven.com.User;
import org.junit.Test;

import java.util.List;


/**
 * Created by User on 2017/6/25.
 */

public class UserDaoTest {
    private  UserDao  userDao= new UserDao();
    @Test
    public void save() throws Exception {
        User user = new User();
        user.setName("历史");
        userDao.save(user);
    }

    @Test
    public void update() throws Exception {
        User user = userDao.getById(1);
        user.setName("历史");
        userDao.update(user);
    }

    @Test
    public void delete() throws Exception {
        userDao.delete(1);
    }

    @Test
    public void getById() throws Exception {
       User user =  userDao.getById(1);
       System.out.println(user.getName());
    }

    @Test
    public void findAll() throws Exception {
        List<User> list = userDao.findAll();
        for (User user:list) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void findAllPage() throws Exception {

    }


}