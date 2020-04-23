package com.food.model.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/4/23 18:31
 */
@Data
@ToString
public class CommentInfo {

    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;

}
