package com.ncu.papersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ncu.papersystem.entity.Paper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author ypp
 */
@Repository
@MapperScan("com.ncu.papersystem.mapper")

public interface PaperMapper extends BaseMapper<Paper> {
}
