package com.tv.business.controller.admin;

import com.tv.server.dto.ProgramCategoryDto;
import com.tv.server.dto.ProgramDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.ProgramCategoryService;
import com.tv.server.service.ProgramService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/program")
public class ProgramController {

    private static final Logger LOG = LoggerFactory.getLogger(ProgramController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private ProgramService programService;
    @Resource
    private ProgramCategoryService programCategoryService;

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

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ProgramDto programDto) {
        // 保存校验
        ValidatorUtil.require(programDto.getTitle(), "Title");
        ValidatorUtil.length(programDto.getTitle(), "Title", 1, 50);
        ValidatorUtil.length(programDto.getSummary(), "Summary", 1, 2000);
        ValidatorUtil.length(programDto.getImage(), "Cover", 1, 100);
        ResponseDto responseDto = new ResponseDto();
        programService.save(programDto);
        responseDto.setContent(programDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        programService.delete(id);
        return responseDto;
    }

    /**
     * 查找课程下所有分类
     * @param programId
     */
    @PostMapping("/list-category/{programId}")
    public ResponseDto listCategory(@PathVariable(value = "programId") String programId) {
        ResponseDto responseDto = new ResponseDto();
        List<ProgramCategoryDto> dtoList = programCategoryService.listByProgram(programId);
        responseDto.setContent(dtoList);
        return responseDto;
    }
}
