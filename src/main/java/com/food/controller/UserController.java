package com.food.controller;

import com.food.enums.ApplicationEnum;
import com.food.model.qo.InsertUserReq;
import com.food.model.qo.UserReq;
import com.food.model.vo.result.FailResult;
import com.food.model.vo.result.Result;
import com.food.model.vo.result.SuccessResult;
import com.food.po.User;
import com.food.service.UserService;
import com.food.utils.TokenUtil;
import java.util.HashMap;
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
    @RequestMapping(value = "login", method = RequestMethod.POST)
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
        // 确认密码是否相同
        if (! insertUserReq.getPassword().equals(insertUserReq.getCheckPassword())) {
            return new FailResult<>(ApplicationEnum.PASSWORD_ERR);
        }
        // 新增用户
        Boolean isInsertUser = userService.insertUser(insertUserReq);
        if (isInsertUser) {
            return new SuccessResult<>();
        }
        return new FailResult<>();
    }


//
//    /**
//     * 修改密码
//     * @param updatePasswordReq
//     * @return
//     */
//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    @ResponseBody
//    public Result updatePassword(@RequestBody @Validated UpdatePasswordReq updatePasswordReq) {
//        // 检查用户密码
//        Boolean isUserPassword = userService.checkPassword(updatePasswordReq.getUserId(), updatePasswordReq.getOldPassword());
//        if (isUserPassword) {
//            // 修改密码
//            Boolean isUpdatePassword = userService.updatePassword(updatePasswordReq.getUserId(), updatePasswordReq.getNewPassword());
//            if (isUpdatePassword) {
//                return new SuccessResult<>();
//            }
//        }
//        return new FailResult<>();
//    }
//
//








}
