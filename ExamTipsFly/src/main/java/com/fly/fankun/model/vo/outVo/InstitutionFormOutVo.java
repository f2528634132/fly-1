package com.fly.fankun.model.vo.outVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2020/3/7
 */
@Data
public class InstitutionFormOutVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("上课形式：0：线上；1：线下；2：线上+线下")
    private String courseType;
    @ApiModelProperty("课程名称")
    private String courseTitle;
    @ApiModelProperty("封面")
    private String coursePicture;
    @ApiModelProperty("课程描述")
    private String courseComment;
    @ApiModelProperty("状态：0：下线；1：发布")
    private Integer status = 1;
    @ApiModelProperty("所属机构")
    private String institution;
    @ApiModelProperty("课程价格")
    private BigDecimal coursePrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @ApiModelProperty("开课时间")
    private Date courseTime;
    @ApiModelProperty("机构类型：0：自有课程；1：第三方合作")
    private Integer institutionType;
    @ApiModelProperty("详情链接")
    private String courseUrl;
}
