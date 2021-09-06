package com.ncu.papersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Question {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String content;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;
    private String type;
    private Integer isMultiple;
    private Date createTime;
    private Integer isDelete;
}
