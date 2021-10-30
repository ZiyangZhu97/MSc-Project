package com.tv.server.mapper.my;

import org.apache.ibatis.annotations.Param;

public interface MyProgramMapper {

    int updateTime(@Param("programId") String programId);
}
