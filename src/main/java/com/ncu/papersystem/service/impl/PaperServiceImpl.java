package com.ncu.papersystem.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ncu.papersystem.entity.*;
import com.ncu.papersystem.mapper.PaperMapper;
import com.ncu.papersystem.mapper.QuestionMapper;
import com.ncu.papersystem.service.PaperService;
import com.ncu.papersystem.vo.PaperVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiazhihang
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Resource
    private PaperMapper paperMapper;
    @Resource
    private QuestionMapper questionMapper;

    public Result<Object> add(PaperVo paperVo) {
        Paper paper = new Paper();
        BeanUtils.copyProperties(paperVo,paper);
        Date date = new Date();
        //DateTime?
        DateTime dateTime = new DateTime(date);
        paper.setCreateTime(dateTime);
        paper.setQuestionId(paperVo.getQuestion_id());
        int result = paperMapper.insert(paper);
        if (result>0){
            return ResultWrapper.success("新增试卷成功");
        }
        return ResultWrapper.error("新增试卷失败");
    }

    @Override
    public Result del(Long[] ids) {
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        for (Long id:ids) {
            queryWrapper.or().eq("id",id);
        }
        Paper paper = new Paper();
        paper.setIsDelete(1);
        int result = paperMapper.update(paper,queryWrapper);
        if (result>0){
            return ResultWrapper.success("删除试卷成功");
        }
        return ResultWrapper.error("删除试卷失败");
    }

    @Override
    public Result update(PaperVo paperVo, Long id) {
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Paper paper = new Paper();
        BeanUtils.copyProperties(paperVo,paper);
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        paper.setCreateTime(dateTime);
        paper.setQuestionId(paperVo.getQuestion_id());
        int result = paperMapper.update(paper,queryWrapper);
        if (result>0){
            return ResultWrapper.success("更新试卷成功");
        }
        return ResultWrapper.error("更新试卷失败");
    }

    @Override
    public Result list(Condition condition) {
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",0);
        if(StrUtil.isNotBlank(condition.getKeyWord())&&StrUtil.isNotBlank(condition.getKeyValue())){
            if(condition.getKeyWord().equals("name")){
                queryWrapper.eq("name",condition.getKeyValue());
            }
        }
        Page<Paper> page = new Page<>(1,10);
        IPage<Paper> iPage = paperMapper.selectPage(page,queryWrapper);
        if (ObjectUtil.isEmpty(iPage)){
            return ResultWrapper.error("获取试卷失败");
        }
        if (iPage.getRecords().size() == 0){
            return ResultWrapper.success("无试卷信息");
        }
        return ResultWrapper.success(iPage);
    }

    @Override
    public Result listQuestionId(Long id) {
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        questionQueryWrapper.eq("is_delete",0);
        List<Paper> paperList = paperMapper.selectList(queryWrapper);

        Paper paper = paperList.get(0);

        String questionString = paper.getQuestionId();

        String[] questionId_string = questionString.split("[,，]");

        List<Long> questionIdList = Arrays.stream(questionId_string)
                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

        for(Long questionId:questionIdList){

            questionQueryWrapper.or().eq("id",questionId);

        }
        Page<Question> page = new Page<>(1,10);
        IPage<Question> iPage = questionMapper.selectPage(page,questionQueryWrapper);
        if (ObjectUtil.isEmpty(iPage)){
            return ResultWrapper.error("获取试卷题目失败");
        }
        if (iPage.getRecords().size() == 0){
            return ResultWrapper.success("无题目信息");
        }
        return ResultWrapper.success(iPage);
    }

    @Override
    public Result generatePaper(Condition condition) {
        Paper paper = new Paper();
        Map<String,Object> map = new HashMap<>();
        map.put("is_delete","0");
        //获取单选
        map.put("is_multiple",0);
        List<Question> singleList = questionMapper.selectByMap(map);
        if(singleList.size()<condition.getSingleQuestionSize()){
            return ResultWrapper.error("题库中的单选题不足"+condition.getSingleQuestionSize()+"道，请添加单选题到数据库");
        }
        //获取多选
        map.put("is_multiple",1);
        List<Question> mutipleList = questionMapper.selectByMap(map);
        if(mutipleList.size()<condition.getMultipleQuestionSize()){
            return ResultWrapper.error("题库中的多选题不足"+condition.getMultipleQuestionSize()+"道，请添加多选题到数据库");
        }
        //抽取单选
        List<Question> singleQuestions = getRandom(singleList,condition.getSingleQuestionSize());
        //抽取多选
        List<Question> mutipleQuestions = getRandom(mutipleList,condition.getMultipleQuestionSize());

        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        paper.setCreateTime(dateTime);
        paper.setName(condition.getGenerateName());
        StringBuffer stringBuffer = new StringBuffer();
        for (Question question:singleQuestions) {
            stringBuffer.append(question.getId());
            stringBuffer.append(",");
        }
        for (Question question:mutipleQuestions) {
            stringBuffer.append(question.getId());
            stringBuffer.append(",");
        }
        paper.setQuestionId(stringBuffer.toString());
        int result = paperMapper.insert(paper);
        if (result>0){
            return ResultWrapper.success("随机生成试卷成功");
        }
        return ResultWrapper.error("随机生成试卷失败");
    }

    private List<Question> getRandom(List<Question> questionList, int n) {
        List backList = null;
        backList = new ArrayList<Question>();
        Random random = new Random();
        for(int i = 0;i<n;i++){
            int target = random.nextInt(questionList.size());
            backList.add(questionList.get(target));
            questionList.remove(target);
        }
        return backList;
    }


}
