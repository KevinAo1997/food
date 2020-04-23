package com.food.model.qo;

import java.io.Serializable;
import javax.annotation.security.DenyAll;
import lombok.Data;
import lombok.ToString;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/4/23 14:18
 */
@Data
@ToString
public class InsertCommentReq {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 内容
     */
    private String content;

}
