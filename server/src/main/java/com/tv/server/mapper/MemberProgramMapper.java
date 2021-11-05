package com.tv.server.mapper;

import com.tv.server.domain.MemberProgram;
import com.tv.server.domain.MemberProgramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberProgramMapper {
    long countByExample(MemberProgramExample example);

    int deleteByExample(MemberProgramExample example);

    int deleteByPrimaryKey(String id);

    int insert(MemberProgram record);

    int insertSelective(MemberProgram record);

    List<MemberProgram> selectByExample(MemberProgramExample example);

    MemberProgram selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MemberProgram record, @Param("example") MemberProgramExample example);

    int updateByExample(@Param("record") MemberProgram record, @Param("example") MemberProgramExample example);

    int updateByPrimaryKeySelective(MemberProgram record);

    int updateByPrimaryKey(MemberProgram record);
}