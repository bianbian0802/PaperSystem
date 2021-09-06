package com.ncu.papersystem.web.front;

import com.ncu.papersystem.entity.Condition;
import com.ncu.papersystem.entity.Result;
import com.ncu.papersystem.service.PaperService;
import com.ncu.papersystem.vo.PaperVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author YPP
 */

@Api(tags = "试卷管理")
@RestController
@RequestMapping("/papersystem/paper")
public class PaperController {
    @Resource
    private PaperService paperService;
    @PostMapping(value = "/v1/add")
    @ApiOperation(value = "增加试卷",notes = "增加试卷")
    public Result add(PaperVo paperVo){
        return paperService.add(paperVo);
    }
    @DeleteMapping(value = "/v1/del")
    @ApiOperation(value = "删除试卷",notes = "传入试卷id")
    public Result del(Long[] ids){
        return paperService.del(ids);
    }
    @PutMapping(value = "/v1/update")
    @ApiOperation(value = "修改试卷信息", notes = "修改试卷信息")
    public Result update(PaperVo paperVo,Long id){
        return paperService.update(paperVo,id);
    }
    @GetMapping(value = "/v1/list")
    @ApiOperation(value = "获取试卷信息", notes = "keyWord: '获取试卷信息'")
    public Result list(Condition condition){
        return paperService.list(condition);
    }
    @PostMapping(value = "/v1/listQuestionId")
    @ApiOperation(value = "获取试卷详细信息", notes = "keyWord: '试卷id'")
    public Result listQuestionId(Long id){
        return paperService.listQuestionId(id);
    }
    @PostMapping(value = "/v1/generatePaper")
    @ApiOperation(value = "生成随机试卷",notes = "生成随机试卷")
    public Result generatePaper(Condition condition){
        return paperService.generatePaper(condition);
    }

}