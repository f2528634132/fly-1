package com.fly.fankun.model.vo.outVo;

import java.io.Serializable;
import lombok.Data;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/10/23
 */
@Data
public class AuthOutVo implements Serializable {
    private static final long serialVersionUID = 5004930693039377707L;
    /**
     *
     */
    private Integer type;

    private Integer id;

    private String userName;

    private String name;
}
