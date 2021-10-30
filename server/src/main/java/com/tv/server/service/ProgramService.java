package com.tv.server.service;

import com.tv.server.domain.Program;
import com.tv.server.domain.ProgramExample;
import com.tv.server.dto.ProgramDto;
import com.tv.server.dto.PageDto;
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

    private static final Logger LOG = LoggerFactory.getLogger(ProgramService.class);

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ProgramExample programExample = new ProgramExample();
        programExample.setOrderByClause("sort asc");
        List<Program> programList = programMapper.selectByExample(programExample);
        PageInfo<Program> pageInfo = new PageInfo<>(programList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ProgramDto> programDtoList = CopyUtil.copyList(programList, ProgramDto.class);
        pageDto.setList(programDtoList);
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
        programCategoryService.saveBatch(programDto.getId(), programDto.getCategorys());

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


}
