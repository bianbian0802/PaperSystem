package com.ncu.papersystem.web.front;

import com.ncu.papersystem.entity.Condition;
import com.ncu.papersystem.entity.Result;
import com.ncu.papersystem.service.QuestionService;
import com.ncu.papersystem.vo.QuestionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YPP
 */

@Api(tags = "问题管理")
@RestController
@RequestMapping("/papersystem/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;
    @PostMapping(value = "/v1/add")
    @ApiOperation(value = "增加题目",notes = "增加题目")
    public Result add(QuestionVo questionVo){
        return questionService.add(questionVo);
    }
    @DeleteMapping(value = "/v1/del")
    @ApiOperation(value = "删除题目",notes = "传入题目id")
    public Result del(Long[] ids){
        return questionService.del(ids);
    }
    @PutMapping(value = "/v1/update")
    @ApiOperation(value = "修改题目信息", notes = "修改题目信息")
    public Result update(QuestionVo questionVo, Long id){
        return questionService.update(questionVo,id);
    }
    @GetMapping(value = "/v1/list")
    @ApiOperation(value = "获取题目信息", notes = "keyWord: 'type'")
    public Result list(Condition condition){
        return questionService.list(condition);
    }
}
