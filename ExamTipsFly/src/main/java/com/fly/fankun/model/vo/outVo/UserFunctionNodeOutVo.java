package com.fly.fankun.model.vo.outVo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Title: 企业账户功能权限节点信息
 * @author: fly
 * @date: 2019/5/27
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFunctionNodeOutVo {
    /**
     * 功能名称
     */
    private String name;
    /**
     * 页面路径
     */
    private String url;
    /**
     * icon
     */
    private String icon;
    /**
     * 功能类型
     */
    private Integer type;
    /**
     * 子节点
     */
    private List<UserFunctionNodeOutVo> childNodes;
}
