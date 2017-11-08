package com.jane.das.dao;

import com.jane.das.commons.config.repository.ICustomRepository;
import com.jane.das.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ICustomRepository<User,Long> {

    @Query("select user from User user where user.loginName=:loginName and user.password=:password")
    public User checkLoginUser(@Param("loginName") String loginName, @Param("password") String password);

    @Query("select user from User user where user.id = ?1")
    public User getUsersById(Long id);
}