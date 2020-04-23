package com.food.service;

import com.food.model.qo.InsertCommentReq;
import com.food.po.Comment;
import java.util.List;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/4/23 14:12
 */
public interface CommentService {

    /**
     * 获取评论列表
     * @return
     */
    List<Comment> getComment();

    /**
     * 新增评论
     * @param insertCommentReq
     * @return
     */
    Boolean insertComment(InsertCommentReq insertCommentReq);
}
