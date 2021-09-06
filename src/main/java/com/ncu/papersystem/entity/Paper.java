package com.ncu.papersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Paper {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private String questionId;
    private Date createTime;
    private Integer isDelete;
}
