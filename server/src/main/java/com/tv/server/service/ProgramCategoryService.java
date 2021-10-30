package com.tv.server.service;

import com.tv.server.domain.ProgramCategory;
import com.tv.server.domain.ProgramCategoryExample;
import com.tv.server.dto.ProgramCategoryDto;
import com.tv.server.dto.PageDto;
import com.tv.server.mapper.ProgramCategoryMapper;
import com.tv.server.util.CopyUtil;
import com.tv.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProgramCategoryService {

    @Resource
    private ProgramCategoryMapper programCategoryMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ProgramCategoryExample programCategoryExample = new ProgramCategoryExample();
        List<ProgramCategory> programCategoryList = programCategoryMapper.selectByExample(programCategoryExample);
        PageInfo<ProgramCategory> pageInfo = new PageInfo<>(programCategoryList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ProgramCategoryDto> programCategoryDtoList = CopyUtil.copyList(programCategoryList, ProgramCategoryDto.class);
        pageDto.setList(programCategoryDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(ProgramCategoryDto programCategoryDto) {
        ProgramCategory programCategory = CopyUtil.copy(programCategoryDto, ProgramCategory.class);
        if (StringUtils.isEmpty(programCategoryDto.getId())) {
            this.insert(programCategory);
        } else {
            this.update(programCategory);
        }
    }

    /**
     * 新增
     */
    private void insert(ProgramCategory programCategory) {
        programCategory.setId(UuidUtil.getShortUuid());
        programCategoryMapper.insert(programCategory);
    }

    /**
     * 更新
     */
    private void update(ProgramCategory programCategory) {
        programCategoryMapper.updateByPrimaryKey(programCategory);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        programCategoryMapper.deleteByPrimaryKey(id);
    }
}
