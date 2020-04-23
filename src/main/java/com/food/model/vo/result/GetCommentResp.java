package com.food.model.vo.result;

import com.food.model.dto.CommentInfo;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import lombok.Data;
import lombok.ToString;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/4/23 18:28
 */
@Data
@ToString
public class GetCommentResp {

    /**
     * 评论
     */
    private List<CommentInfo> commentInfoList;
}
