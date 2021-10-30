package com.tv.business.controller.admin;

import com.tv.server.domain.Chapter;
import com.tv.server.dto.ChapterDto;
import com.tv.server.dto.ChapterPageDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.ChapterService;
import com.tv.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController     //返回json数据用RestController，返回页面用Controller
@RequestMapping("/admin/chapter")

public class ChapterController {
    public static final String BUSINESS_NAME = "Chapter";


    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto) {
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(chapterPageDto.getProgramId(), "Program ID");
        chapterService.list(chapterPageDto);
        responseDto.setContent(chapterPageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        // 保存校验
        ValidatorUtil.require(chapterDto.getName(), "Title");
        ValidatorUtil.require(chapterDto.getProgramId(), "Program ID");
        ValidatorUtil.length(chapterDto.getProgramId(), "Program ID", 1, 8);

        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }
}
