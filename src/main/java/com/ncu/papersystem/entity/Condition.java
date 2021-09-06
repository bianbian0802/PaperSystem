package com.ncu.papersystem.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiazhihang
 */

@Data
@ApiModel("条件")
public class Condition {

    @ApiModelProperty("条件")
    private String keyWord = "";

    @ApiModelProperty("条件值")
    private String keyValue = "";

    @ApiModelProperty("当前页")
    private long currentPage = 1;

    @ApiModelProperty("每页大小")
    private long pageSize = 10;

    @ApiModelProperty("单选题数")
    private int singleQuestionSize = 3;

    @ApiModelProperty("多选题数")
    private int multipleQuestionSize = 3;

    @ApiModelProperty("生成试卷名字")
    private String generateName = "";
}
