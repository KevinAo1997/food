package com.food.service.Impl;

import com.food.dao.CommentMapper;
import com.food.model.qo.InsertCommentReq;
import com.food.po.Comment;
import com.food.service.CommentService;
import com.food.utils.BeanUtil;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/4/23 14:12
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getComment() {
        // 获取评论列表
        List<Comment> commentList = commentMapper.selectAll();
        return commentList;
    }

    @Override
    public Boolean insertComment(InsertCommentReq insertCommentReq) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(insertCommentReq, comment);
        comment.setCreateTime(LocalDateTime.now());

        // 新增评论
        Integer insert = commentMapper.insert(comment);
        return insert > 0;
    }
}
