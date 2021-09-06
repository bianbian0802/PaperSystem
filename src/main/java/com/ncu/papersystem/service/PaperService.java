package com.ncu.papersystem.service;

import com.ncu.papersystem.entity.Condition;
import com.ncu.papersystem.entity.Result;
import com.ncu.papersystem.vo.PaperVo;



/**
 * @author ypp
 */

public interface PaperService {
    Result add(PaperVo paperVo);
    Result del(Long[] ids);
    Result update(PaperVo paperVo, Long id);
    Result list(Condition condition);
    Result listQuestionId(Long id);
    Result generatePaper(Condition condition);
}
