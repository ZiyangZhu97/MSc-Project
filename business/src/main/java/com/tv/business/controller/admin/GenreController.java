package com.tv.business.controller.admin;

import com.tv.server.dto.GenreDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.GenreService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/genre")
public class GenreController {

    private static final Logger LOG = LoggerFactory.getLogger(GenreController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private GenreService genreService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        genreService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody GenreDto genreDto) {
        // 保存校验
        ValidatorUtil.require(genreDto.getName(), "Name");
        ValidatorUtil.length(genreDto.getName(), "Name", 1, 50);
        ResponseDto responseDto = new ResponseDto();
        genreService.save(genreDto);
        responseDto.setContent(genreDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        genreService.delete(id);
        return responseDto;
    }
}
