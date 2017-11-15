package com.jane.das.service;

import com.jane.das.dao.UserDao;
import com.jane.das.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 验证登录用户是否为本系统用户
     * @param loginName 登录用户名
     * @param password 密码
     * @return 验证成功，返回true,否则返回false
     */
    public boolean checkLoginUser(String loginName, String password){
        User loginUser = userDao.checkLoginUser(loginName,password);
        if (loginUser != null){
            return true;
        }
        return false;
    }

    /**
     * 保存用户信息入库
     * @param user 用户信息
     * @return
     */
    @Transient
    public User createUser(User user){
        return userDao.save(user);
    }

    /**
     * 通过用户标识获取用户信息
     * @param id 用户标识
     * @return 用户信息
     */
    public User getSingleById(Long id){
         return userDao.findOne(id);
    }
}
