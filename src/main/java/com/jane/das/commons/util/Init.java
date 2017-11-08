package com.jane.das.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jane.das.entity.User;
import com.jane.das.model.Sex;
import com.jane.das.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Init implements CommandLineRunner{

    private Logger logger = LoggerFactory.getLogger(Init.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        if (userService.getSingleById(1L) == null){
            User systemUser = new User();
            systemUser.setLoginName("admin");
            systemUser.setSex(Sex.Male);
            systemUser.setUsername("管理员");
            systemUser.setPhoneNumber("1234567");
            systemUser.setEmail("123456789@qq.com");
            systemUser.setCreateTime(new Date());
            systemUser.setPassword("Admin123");
            userService.createUser(systemUser);
            logger.info("系统初始化系统用户成功");
        }
    }
}
