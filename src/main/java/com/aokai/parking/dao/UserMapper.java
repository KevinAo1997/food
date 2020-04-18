package com.aokai.parking.dao;

import com.aokai.parking.po.User;
import java.util.List;
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
     * 更改用户权限类型
     * @param userType
     * @param userId
     * @return
     */
    Integer updateType(@Param("userType") String userType, @Param("userId") Integer userId);

    /**
     * 更改用户状态
     * @param userId
     * @param status
     * @return
     */
    Integer updateStatus(@Param("userID") Integer userId, @Param("status") Integer status);

    /**
     * 删除用户
     * @param userID
     * @return
     */
    Integer deleteUser(@Param("userID") Integer userID);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    User checkUserName(@Param("username") String username);

    /**
     * 查询用户密码
     * @param userID
     * @return
     */
    String checkPassword(@Param("userID") Integer userID);

    /**
     * 修改用户密码
     * @param userID
     * @param newPassword
     * @return
     */
    Integer updatePassword(@Param("userID") Integer userID, @Param("newPassword") String newPassword);

    /**
     * 根据用户IDS获取用户
     * @param userIds
     * @return
     */
    List<User> selectUserByUserIds(@Param("userIds") List<Integer> userIds);

    /**
     *
     * @param code
     * @param username
     * @param name
     * @return
     */
    List<User> searchUser(
            @Param("code") String code, @Param("username") String username, @Param("name") String name);
}
