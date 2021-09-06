package com.ncu.papersystem.service;

import com.ncu.papersystem.entity.Condition;
import com.ncu.papersystem.entity.Result;
import com.ncu.papersystem.vo.QuestionVo;

/**
 * @author ypp
 */

public interface QuestionService {
    Result add(QuestionVo questionVo);
    Result del(Long[] ids);
    Result update(QuestionVo questionVo, Long id);
    Result list(Condition condition);
}
