package com.food.service;

import com.food.model.qo.InsertUserReq;
import com.food.po.User;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/3/29 19:19
 */
public interface UserService {

    /**
     * 获取用户账号信息
     * @param userReq
     * @return
     */
    User checkUser(UserReq userReq);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    Boolean checkUserName(String username);

    /**
     * 新增用户
     * @param insertUserReq
     * @return
     */
    Boolean insertUser(InsertUserReq insertUserReq);

}
