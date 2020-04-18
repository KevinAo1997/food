package com.aokai.parking.controller;

import com.aokai.parking.enums.ApplicationEnum;
import com.aokai.parking.model.qo.user.DeleteUserReq;
import com.aokai.parking.model.qo.user.InsertUserReq;
import com.aokai.parking.model.qo.PageReq;
import com.aokai.parking.model.qo.user.SearchUserReq;
import com.aokai.parking.model.qo.user.UpdatePasswordReq;
import com.aokai.parking.model.qo.user.UpdateUserReq;
import com.aokai.parking.model.qo.user.UpdateUserStatusReq;
import com.aokai.parking.model.qo.user.UpdateUserTypeReq;
import com.aokai.parking.model.qo.user.UserReq;
import com.aokai.parking.model.vo.result.FailResult;
import com.aokai.parking.model.vo.result.Result;
import com.aokai.parking.model.vo.result.SuccessResult;
import com.aokai.parking.po.User;
import com.aokai.parking.service.UserService;
import com.aokai.parking.utils.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/3/29 19:15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userReq
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody @Validated UserReq userReq) {
        // 通过账号密码获取用户信息
        User user = userService.checkUser(userReq);
        if (user == null) {
            return new FailResult<>(ApplicationEnum.USER_OR_PWD_ERR);
        }
        // 生成token
        HashMap<String, Object> userMap = new HashMap<>();
        String token = TokenUtil.sign(user);
        userMap.put("user", user);
        userMap.put("token", token);
        return new SuccessResult<>(userMap);
    }

    /**
     * 更改用户权限类型
     * @param updateUserTypeReq
     * @return
     */
    @RequestMapping(value = "/updateType", method = RequestMethod.POST)
    @ResponseBody
    public Result updateType(@RequestBody @Validated UpdateUserTypeReq updateUserTypeReq) {
        // 更改用户权限类型
        Boolean isUpdateType = userService.updateType(updateUserTypeReq);
        if (isUpdateType) {
            return new SuccessResult<>();
        }
        return new FailResult<>();
    }


    /**
     * 更改用户状态
     * @param updateUserStatusReq
     * @return
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result updateStatus(@RequestBody @Validated UpdateUserStatusReq updateUserStatusReq) {
        // 更改用户状态
        Boolean isUpdateStatus = userService.updateStatus(updateUserStatusReq.getUserId(), updateUserStatusReq.getStatus());
        if (isUpdateStatus) {
            return new SuccessResult<>();
        }
        return new FailResult<>();
    }

    /**
     * 删除用户
     * @param deleteUserReq
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteUser(@RequestBody @Validated DeleteUserReq deleteUserReq) {
        // 删除用户
        Boolean isDeleteUser = userService.deleteUser(deleteUserReq.getUserId());
        if (isDeleteUser) {
            return new SuccessResult<>();
        }
        return new FailResult<>();
    }

    /**
     * 新增用户
     * @param insertUserReq
     * @return
     */
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    @ResponseBody
    public Result insertUser(@RequestBody @Validated InsertUserReq insertUserReq) {
        // 判断用户名是否存在
        Boolean isUserName = userService.checkUserName(insertUserReq.getUsername());
        if (isUserName) {
            return new FailResult<>(ApplicationEnum.USER_NAME_REPETITION);
        }
        // 新增用户
        Boolean isInsertUser = userService.insertUser(insertUserReq);
        if (isInsertUser) {
            return new SuccessResult<>();
        }
        return new FailResult<>();
    }


    /**
     * 编辑用户信息
     * @param updateUserReq
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUser(@RequestBody @Validated UpdateUserReq updateUserReq) {
        // 编辑用户信息
        Boolean isUpdateUser = userService.updateUser(updateUserReq);
        if (isUpdateUser) {
            return new SuccessResult<>();
        }
        return new FailResult<>();
    }


    /**
     * 修改密码
     * @param updatePasswordReq
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassword(@RequestBody @Validated UpdatePasswordReq updatePasswordReq) {
        // 检查用户密码
        Boolean isUserPassword = userService.checkPassword(updatePasswordReq.getUserId(), updatePasswordReq.getOldPassword());
        if (isUserPassword) {
            // 修改密码
            Boolean isUpdatePassword = userService.updatePassword(updatePasswordReq.getUserId(), updatePasswordReq.getNewPassword());
            if (isUpdatePassword) {
                return new SuccessResult<>();
            }
        }
        return new FailResult<>();
    }


    /**
     * 分页获取用户列表
     * @param pageReq
     * @return
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserList(@RequestBody @Validated PageReq pageReq) {
        Integer pageNum = pageReq.getPageNum() == null ? 1 : pageReq.getPageNum();
        Integer pageSize = pageReq.getPageSize() == null ? 10 : pageReq.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        // 获取用户列表
        List<User> userList = userService.getUserList();
        if (CollectionUtils.isEmpty(userList)) {
            return new SuccessResult<>(userList);
        }
        // 分页获取
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return new SuccessResult<>(userPageInfo);
    }



    /**
     * 搜索用户
     * @param searchUserReq
     * @return
     */
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    @ResponseBody
    public Result searchUser(@RequestBody SearchUserReq searchUserReq) {
        Integer pageNum = searchUserReq.getPageNum() == null ? 1 : searchUserReq.getPageNum();
        Integer pageSize = searchUserReq.getPageSize() == null ? 10 : searchUserReq.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        // 搜索用户
        List<User> userList = userService.searchUser(searchUserReq);
        // 分页获取
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return new SuccessResult<>(userPageInfo);
    }









}
