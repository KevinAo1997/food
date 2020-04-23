package com.food.dao;

import com.food.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

    /**
     * 通过账号和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    User checkUser(@Param("username") String username, @Param("password") String password);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    User checkUserName(@Param("username") String username);
}
