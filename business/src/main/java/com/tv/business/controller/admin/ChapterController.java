package com.tv.business.controller.admin;

import com.tv.server.domain.Chapter;
import com.tv.server.dto.ChapterDto;
import com.tv.server.dto.PageDto;
import com.tv.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController     //返回json数据用RestController，返回页面用Controller
@RequestMapping("/admin/chapter")

public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public PageDto list(@RequestBody PageDto pageDto) {
        chapterService.list(pageDto);
        return pageDto;
    }
}
