package com.ncu.papersystem.service.impl;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ncu.papersystem.entity.*;
import com.ncu.papersystem.mapper.QuestionMapper;
import com.ncu.papersystem.service.QuestionService;
import com.ncu.papersystem.vo.QuestionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;
    @Override
    public Result add(QuestionVo questionVo) {
        Question question = new Question();
        BeanUtils.copyProperties(questionVo,question);
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        question.setCreateTime(dateTime);
        int result = questionMapper.insert(question);
        if (result>0){
            return ResultWrapper.success("新增题目成功");
        }
        return ResultWrapper.error("新增题目失败");
    }

    @Override
    public Result del(Long[] ids) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        for (Long id:ids) {
            queryWrapper.or().eq("id",id);
        }
        Question question = new Question();
        question.setIsDelete(1);
        int result = questionMapper.update(question,queryWrapper);
        if (result>0){
            return ResultWrapper.success("删除题目成功");
        }
        return ResultWrapper.error("删除题目失败");
    }

    @Override
    public Result update(QuestionVo questionVo, Long id) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",0);
        queryWrapper.eq("id",id);
        Question question = new Question();
        BeanUtils.copyProperties(questionVo,question);
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        question.setCreateTime(dateTime);
        int result = questionMapper.update(question,queryWrapper);
        if (result>0){
            return ResultWrapper.success("更新题目成功");
        }
        return ResultWrapper.error("更新题目失败");
    }

    @Override
    public Result list(Condition condition) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",0);
        if(StrUtil.isNotBlank(condition.getKeyWord())&&StrUtil.isNotBlank(condition.getKeyValue())) {
            if (condition.getKeyWord().equals("type")) {
                queryWrapper.eq("type",condition.getKeyValue());
                System.out.println(queryWrapper.eq("type", condition.getKeyValue()));
            }
        }
        Page<Question> page = new Page<>(condition.getCurrentPage(),condition.getPageSize());
        IPage<Question> iPage = questionMapper.selectPage(page,queryWrapper);
        if (ObjectUtil.isEmpty(iPage)){
            return ResultWrapper.error("获取题目失败");
        }
        if (iPage.getRecords().size() == 0){
            return ResultWrapper.success("无题目信息");
        }
        return ResultWrapper.success(iPage);
    }

}
