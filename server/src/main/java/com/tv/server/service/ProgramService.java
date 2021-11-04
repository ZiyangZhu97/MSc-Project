package com.tv.server.service;

import com.tv.server.domain.Program;
import com.tv.server.domain.ProgramExample;
import com.tv.server.dto.*;
import com.tv.server.enums.ProgramStatusEnum;
import com.tv.server.mapper.ProgramMapper;
import com.tv.server.mapper.my.MyProgramMapper;
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
public class ProgramService {

    @Resource
    private ProgramMapper programMapper;
    @Resource
    private MyProgramMapper myProgramMapper;
    @Resource
    private ProgramCategoryService programCategoryService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private EpisodeService episodeService;

    private static final Logger LOG = LoggerFactory.getLogger(ProgramService.class);

    /**
     * 列表查询：关联课程分类表
     * @param pageDto
     */
    public void list(ProgramPageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<ProgramDto> programDtoList = myProgramMapper.list(pageDto);
        PageInfo<ProgramDto> pageInfo = new PageInfo<>(programDtoList);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(programDtoList);
    }
    
    
    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     */
    public List<ProgramDto> listNew(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ProgramExample programExample = new ProgramExample();
        programExample.createCriteria().andStatusEqualTo(ProgramStatusEnum.PUBLISH.getCode());
        programExample.setOrderByClause("created_at desc");
        List<Program> programList = programMapper.selectByExample(programExample);
        return CopyUtil.copyList(programList, ProgramDto.class);
    }
    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(ProgramDto programDto) {
        Program program = CopyUtil.copy(programDto, Program.class);
        if (StringUtils.isEmpty(programDto.getId())) {
            this.insert(program);
        } else {
            this.update(program);
        }
        // 批量保存课程分类
        programCategoryService.saveBatch(program.getId(), programDto.getCategorys());

    }

    /**
     * 新增
     */
    private void insert(Program program) {
        Date now = new Date();
        program.setCreatedAt(now);
        program.setUpdatedAt(now);
        program.setId(UuidUtil.getShortUuid());
        programMapper.insert(program);
    }

    /**
     * 更新
     */
    private void update(Program program) {
        program.setUpdatedAt(new Date());
        programMapper.updateByPrimaryKey(program);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        programMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新课程时长
     * @param programId
     * @return
     */
    public void updateTime(String programId) {
        LOG.info("更新课程时长：{}", programId);
        myProgramMapper.updateTime(programId);
    }


    /**
     * 查找某一节目，供web模块用，只能查已发布的
     * @param id
     * @return
     */
    public ProgramDto findProgram(String id) {
        Program program = programMapper.selectByPrimaryKey(id);
        if (program == null || !ProgramStatusEnum.PUBLISH.getCode().equals(program.getStatus())) {
            return null;
        }
        ProgramDto programDto = CopyUtil.copy(program, ProgramDto.class);


        // 查找章信息
        List<ChapterDto> chapterDtoList = chapterService.listByProgram(id);
        programDto.setChapters(chapterDtoList);

        // 查找节信息
        List<EpisodeDto> episodeDtoList = episodeService.listByProgram(id);
        programDto.setEpisodes(episodeDtoList);

        return programDto;
    }
}
