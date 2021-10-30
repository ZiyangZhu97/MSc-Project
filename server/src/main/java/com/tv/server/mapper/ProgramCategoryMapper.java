package com.tv.server.mapper;

import com.tv.server.domain.ProgramCategory;
import com.tv.server.domain.ProgramCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramCategoryMapper {
    long countByExample(ProgramCategoryExample example);

    int deleteByExample(ProgramCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProgramCategory record);

    int insertSelective(ProgramCategory record);

    List<ProgramCategory> selectByExample(ProgramCategoryExample example);

    ProgramCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProgramCategory record, @Param("example") ProgramCategoryExample example);

    int updateByExample(@Param("record") ProgramCategory record, @Param("example") ProgramCategoryExample example);

    int updateByPrimaryKeySelective(ProgramCategory record);

    int updateByPrimaryKey(ProgramCategory record);
}