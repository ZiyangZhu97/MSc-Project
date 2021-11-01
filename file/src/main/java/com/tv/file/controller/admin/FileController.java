package com.tv.file.controller.admin;

import com.tv.server.dto.FileDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.FileService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/file")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private FileService fileService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto) {
        // 保存校验
        ValidatorUtil.require(fileDto.getPath(), "Relative Path");
        ValidatorUtil.length(fileDto.getPath(), "Relative Path", 1, 100);
        ValidatorUtil.length(fileDto.getName(), "File Name", 1, 100);
        ValidatorUtil.length(fileDto.getSuffix(), "File Suffix", 1, 10);
        ResponseDto responseDto = new ResponseDto();
        fileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        fileService.delete(id);
        return responseDto;
    }
}
