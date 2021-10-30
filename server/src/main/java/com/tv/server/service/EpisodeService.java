package com.tv.server.service;

import com.tv.server.domain.Episode;
import com.tv.server.domain.EpisodeExample;
import com.tv.server.dto.EpisodeDto;
import com.tv.server.dto.EpisodePageDto;
import com.tv.server.dto.PageDto;
import com.tv.server.mapper.EpisodeMapper;
import com.tv.server.util.CopyUtil;
import com.tv.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class EpisodeService {

    @Resource
    private EpisodeMapper episodeMapper;
    @Resource
    private ProgramService programService;


    /**
     * 列表查询
     */
    public void list(EpisodePageDto episodePageDto) {
        PageHelper.startPage(episodePageDto.getPage(), episodePageDto.getSize());
        EpisodeExample episodeExample = new EpisodeExample();
        EpisodeExample.Criteria criteria = episodeExample.createCriteria();
        if (!StringUtils.isEmpty(episodePageDto.getProgramId())) {
            criteria.andProgramIdEqualTo(episodePageDto.getProgramId());
        }
        if (!StringUtils.isEmpty(episodePageDto.getChapterId())) {
            criteria.andChapterIdEqualTo(episodePageDto.getChapterId());
        }
        episodeExample.setOrderByClause("sort asc");
        List<Episode> episodeList = episodeMapper.selectByExample(episodeExample);
        PageInfo<Episode> pageInfo = new PageInfo<>(episodeList);
        episodePageDto.setTotal(pageInfo.getTotal());
        List<EpisodeDto> episodeDtoList = CopyUtil.copyList(episodeList, EpisodeDto.class);
        episodePageDto.setList(episodeDtoList);
    }



    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(EpisodeDto episodeDto) {
        Episode episode = CopyUtil.copy(episodeDto, Episode.class);
        if (StringUtils.isEmpty(episodeDto.getId())) {
            this.insert(episode);
        } else {
            this.update(episode);
        }
        programService.updateTime(episodeDto.getProgramId());

    }

    /**
     * 新增
     */
    private void insert(Episode episode) {
        Date now = new Date();
        episode.setCreatedAt(now);
        episode.setUpdatedAt(now);
        episode.setId(UuidUtil.getShortUuid());
        episodeMapper.insert(episode);
    }

    /**
     * 更新
     */
    private void update(Episode episode) {
        episode.setUpdatedAt(new Date());
        episodeMapper.updateByPrimaryKey(episode);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        episodeMapper.deleteByPrimaryKey(id);
    }

}
