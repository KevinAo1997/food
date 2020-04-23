package com.food.service.Impl;

import com.food.dao.UserMapper;
import com.food.model.qo.InsertUserReq;
import com.food.model.qo.UserReq;
import com.food.po.User;
import com.food.service.UserService;
import com.food.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/3/29 19:23
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(UserReq userReq) {
        String username = userReq.getUsername();
        String password = MD5Util.string2MD5(userReq.getPassword());
        // 通过账号和密码获取用户信息
        return userMapper.checkUser(username, password);
    }

    @Override
    public Boolean checkUserName(String username) {
        // 判断用户名是否存在
        User user = userMapper.checkUserName(username);
        return user != null;
    }

    @Override
    public Boolean insertUser(InsertUserReq insertUserReq) {
        User user = new User();
        user.setName(insertUserReq.getName());
        user.setUsername(insertUserReq.getUsername());
        user.setPassword(MD5Util.string2MD5(insertUserReq.getPassword()));

        // 新增用户
        Integer insertUer = userMapper.insert(user);
        return insertUer > 0;
    }

}
