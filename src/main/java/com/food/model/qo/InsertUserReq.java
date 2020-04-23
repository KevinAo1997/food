package com.food.model.qo;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * Description :   .
 *
 * @author : aokai
 * @date : Created in 2020/4/8 20:00
 */
@Data
@ToString
public class InsertUserReq implements Serializable {

    private static final long serialVersionUID = -6568258585271537507L;

    /**
     * 账号
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;



}
