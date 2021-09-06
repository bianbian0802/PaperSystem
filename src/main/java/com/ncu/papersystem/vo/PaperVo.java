package com.ncu.papersystem.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class PaperVo {
    //@TableId(value = "id",type = IdType.AUTO)
    //private Long id;
    private String name;
    private String question_id;
    //private Date upload_time;
    //private Integer isDelete;
}