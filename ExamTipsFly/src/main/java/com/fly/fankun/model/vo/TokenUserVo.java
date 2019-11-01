package com.fly.fankun.model.vo;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/11/1
 */
@Data
@ToString
public class TokenUserVo implements Serializable {
    private Integer id;
    private Integer userType;

    public TokenUserVo(Integer id, Integer userType) {
        this.id = id;
        this.userType = userType;
    }

    public TokenUserVo() {
    }
}
