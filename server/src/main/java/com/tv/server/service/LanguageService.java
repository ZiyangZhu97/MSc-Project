package com.tv.server.service;

import com.tv.server.domain.Language;
import com.tv.server.domain.LanguageExample;
import com.tv.server.dto.LanguageDto;
import com.tv.server.dto.PageDto;
import com.tv.server.mapper.LanguageMapper;
import com.tv.server.util.CopyUtil;
import com.tv.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LanguageService {

    @Resource
    private LanguageMapper languageMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        LanguageExample languageExample = new LanguageExample();
        languageExample.setOrderByClause("sort asc");
        List<Language> languageList = languageMapper.selectByExample(languageExample);
        PageInfo<Language> pageInfo = new PageInfo<>(languageList);
        pageDto.setTotal(pageInfo.getTotal());
        List<LanguageDto> languageDtoList = CopyUtil.copyList(languageList, LanguageDto.class);
        pageDto.setList(languageDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(LanguageDto languageDto) {
        Language language = CopyUtil.copy(languageDto, Language.class);
        if (StringUtils.isEmpty(languageDto.getId())) {
            this.insert(language);
        } else {
            this.update(language);
        }
    }

    /**
     * 新增
     */
    private void insert(Language language) {
        language.setId(UuidUtil.getShortUuid());
        languageMapper.insert(language);
    }

    /**
     * 更新
     */
    private void update(Language language) {
        languageMapper.updateByPrimaryKey(language);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        languageMapper.deleteByPrimaryKey(id);
    }
}
