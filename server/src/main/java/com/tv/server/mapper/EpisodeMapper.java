package com.tv.server.mapper;

import com.tv.server.domain.Episode;
import com.tv.server.domain.EpisodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpisodeMapper {
    long countByExample(EpisodeExample example);

    int deleteByExample(EpisodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Episode record);

    int insertSelective(Episode record);

    List<Episode> selectByExample(EpisodeExample example);

    Episode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Episode record, @Param("example") EpisodeExample example);

    int updateByExample(@Param("record") Episode record, @Param("example") EpisodeExample example);

    int updateByPrimaryKeySelective(Episode record);

    int updateByPrimaryKey(Episode record);
}