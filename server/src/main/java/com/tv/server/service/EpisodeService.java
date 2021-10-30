package com.tv.server.service;

import com.tv.server.domain.Episode;
import com.tv.server.domain.EpisodeExample;
import com.tv.server.dto.EpisodeDto;
import com.tv.server.dto.PageDto;
import com.tv.server.mapper.EpisodeMapper;
import com.tv.server.util.CopyUtil;
import com.tv.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EpisodeExample episodeExample = new EpisodeExample();
        episodeExample.setOrderByClause("sort asc");
        List<Episode> episodeList = episodeMapper.selectByExample(episodeExample);
        PageInfo<Episode> pageInfo = new PageInfo<>(episodeList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EpisodeDto> episodeDtoList = CopyUtil.copyList(episodeList, EpisodeDto.class);
        pageDto.setList(episodeDtoList);
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
        //programService.updateTime(episodeDto.getProgramId());

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
