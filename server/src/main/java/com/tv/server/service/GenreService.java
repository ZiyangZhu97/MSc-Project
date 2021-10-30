package com.tv.server.service;

import com.tv.server.domain.Genre;
import com.tv.server.domain.GenreExample;
import com.tv.server.dto.GenreDto;
import com.tv.server.dto.PageDto;
import com.tv.server.mapper.GenreMapper;
import com.tv.server.util.CopyUtil;
import com.tv.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GenreService {

    @Resource
    private GenreMapper genreMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        GenreExample genreExample = new GenreExample();
        genreExample.setOrderByClause("sort asc");
        List<Genre> genreList = genreMapper.selectByExample(genreExample);
        PageInfo<Genre> pageInfo = new PageInfo<>(genreList);
        pageDto.setTotal(pageInfo.getTotal());
        List<GenreDto> genreDtoList = CopyUtil.copyList(genreList, GenreDto.class);
        pageDto.setList(genreDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(GenreDto genreDto) {
        Genre genre = CopyUtil.copy(genreDto, Genre.class);
        if (StringUtils.isEmpty(genreDto.getId())) {
            this.insert(genre);
        } else {
            this.update(genre);
        }
    }

    /**
     * 新增
     */
    private void insert(Genre genre) {
        genre.setId(UuidUtil.getShortUuid());
        genreMapper.insert(genre);
    }

    /**
     * 更新
     */
    private void update(Genre genre) {
        genreMapper.updateByPrimaryKey(genre);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        genreMapper.deleteByPrimaryKey(id);
    }
}
