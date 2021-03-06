package com.tv.business.controller.web;

import com.tv.server.dto.PageDto;
import com.tv.server.dto.ProgramDto;
import com.tv.server.dto.ProgramPageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.enums.ProgramStatusEnum;
import com.tv.server.service.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("webProgramController")
@RequestMapping("/web/program")
public class ProgramController {

    private static final Logger LOG = LoggerFactory.getLogger(ProgramController.class);
    public static final String BUSINESS_NAME = "Program";

    @Resource
    private ProgramService programService;

    /**
     * 列表查询，查询最新的3门已发布的节目
     */
    @GetMapping("/list-new")
    public ResponseDto listNew() {
        PageDto pageDto = new PageDto();
        pageDto.setPage(1);
        pageDto.setSize(3);
        ResponseDto responseDto = new ResponseDto();
        List<ProgramDto> programDtoList = programService.listNew(pageDto);
        responseDto.setContent(programDtoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody ProgramPageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pageDto.setStatus(ProgramStatusEnum.PUBLISH.getCode());
        programService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @GetMapping("/find/{id}")
    public ResponseDto findProgram(@PathVariable String id) {
        LOG.info("查找课程开始：{}", id);
        ResponseDto responseDto = new ResponseDto();
        ProgramDto programDto = programService.findProgram(id);
        responseDto.setContent(programDto);
        LOG.info("查找课程结束：{}", responseDto);
        return responseDto;
    }
}


