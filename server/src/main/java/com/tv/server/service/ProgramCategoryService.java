package com.tv.server.service;

import com.tv.server.domain.ProgramCategory;
import com.tv.server.domain.ProgramCategoryExample;
import com.tv.server.dto.CategoryDto;
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

    
    public void saveBatch(String programId, List<CategoryDto> dtoList) {
        ProgramCategoryExample example = new ProgramCategoryExample();
        example.createCriteria().andProgramIdEqualTo(programId);
        programCategoryMapper.deleteByExample(example);
        for (int i = 0, l = dtoList.size(); i < l; i++) {
            CategoryDto categoryDto = dtoList.get(i);
            ProgramCategory programCategory = new ProgramCategory();
            programCategory.setId(UuidUtil.getShortUuid());
            programCategory.setProgramId(programId);
            programCategory.setCategoryId(categoryDto.getId());
            insert(programCategory);
        }
    }
    /**
     * 查找课程下所有分类
     * @param programId
     */
    public List<ProgramCategoryDto> listByProgram(String programId) {
        ProgramCategoryExample example = new ProgramCategoryExample();
        example.createCriteria().andProgramIdEqualTo(programId);
        List<ProgramCategory> programCategoryList = programCategoryMapper.selectByExample(example);
        return CopyUtil.copyList(programCategoryList, ProgramCategoryDto.class);
    }

}
