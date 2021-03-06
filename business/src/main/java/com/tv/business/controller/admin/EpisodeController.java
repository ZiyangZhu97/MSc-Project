package com.tv.business.controller.admin;

import com.tv.server.dto.EpisodeDto;
import com.tv.server.dto.EpisodePageDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.EpisodeService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/episode")
public class EpisodeController {

    private static final Logger LOG = LoggerFactory.getLogger(EpisodeController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private EpisodeService episodeService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EpisodePageDto episodePageDto) {
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(episodePageDto.getProgramId(), "Program ID");
        ValidatorUtil.require(episodePageDto.getChapterId(), "Chapter ID");
        episodeService.list(episodePageDto);
        responseDto.setContent(episodePageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EpisodeDto episodeDto) {
        // 保存校验
        ValidatorUtil.require(episodeDto.getTitle(), "Title");
        ValidatorUtil.length(episodeDto.getTitle(), "Title", 1, 50);
        ValidatorUtil.length(episodeDto.getVideo(), "Video", 1, 200);
        ResponseDto responseDto = new ResponseDto();
        episodeService.save(episodeDto);
        responseDto.setContent(episodeDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        episodeService.delete(id);
        return responseDto;
    }
}
