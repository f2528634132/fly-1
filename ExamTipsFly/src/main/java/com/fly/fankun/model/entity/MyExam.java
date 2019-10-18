package com.fly.fankun.model.entity;

import java.util.Date;
import lombok.Data;

@Data
public class MyExam {
    private Integer id;

    private Integer examId;

    private Integer personId;
    /**
     * 0:未报名，2：报名已过期，3：待考试，4：已考试，5：考试已过期
     */
    private Integer status;

    private Date createtime;

    private Date updatatime;

    private Integer deleted;

}