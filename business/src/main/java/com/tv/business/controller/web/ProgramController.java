package com.tv.business.controller.web;

import com.tv.server.dto.PageDto;
import com.tv.server.dto.ProgramDto;
import com.tv.server.dto.ResponseDto;
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
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        programService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
}


