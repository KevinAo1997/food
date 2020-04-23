package com.food.controller;

import com.food.enums.ApplicationEnum;
import com.food.model.qo.InsertCommentReq;
import com.food.model.qo.PageReq;
import com.food.model.qo.InsertUserReq;
import com.food.model.vo.result.FailResult;
import com.food.model.vo.result.Result;
import com.food.model.vo.result.SuccessResult;
import com.food.po.Comment;
import com.food.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
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
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取评论列表
     * @param pageReq
     * @return
     */
    @RequestMapping(value = "getComment", method = RequestMethod.POST)
    @ResponseBody
    public Result getComment(@RequestBody @Validated PageReq pageReq) {
        // 分页
        Integer pageNum = pageReq.getPageNum() == null ? 1 : pageReq.getPageNum();
        Integer pageSize = pageReq.getPageSize() == null ? 10 : pageReq.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        // 获取评论列表
        List<Comment> commentList = commentService.getComment();
        PageInfo<Comment> commentPageInfo = new PageInfo<>(commentList);
        return new SuccessResult<>(commentPageInfo);
    }



    /**
     * 新增评论
     * @param insertCommentReq
     * @return
     */
    @RequestMapping(value = "/insertComment", method = RequestMethod.POST)
    @ResponseBody
    public Result insertComment(@RequestBody @Validated InsertCommentReq insertCommentReq) {
        // 新增评论
        Boolean insertComment = commentService.insertComment(insertCommentReq);
        if (insertComment) {
            return new SuccessResult<>();
        }
        return new FailResult<>();
    }


}
