package com.ncu.papersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ncu.papersystem.entity.Question;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author ypp
 */
@Repository
@MapperScan("com.ncu.papersystem.mapper")
public interface QuestionMapper extends BaseMapper<Question> {
}
