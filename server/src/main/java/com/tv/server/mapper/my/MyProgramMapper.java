package com.tv.server.mapper.my;

import com.tv.server.dto.ProgramDto;
import com.tv.server.dto.ProgramPageDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyProgramMapper {

    List<ProgramDto> list(@Param("pageDto") ProgramPageDto pageDto);

    int updateTime(@Param("programId") String programId);

}
