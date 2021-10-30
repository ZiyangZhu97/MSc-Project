package com.tv.business.controller.admin;

import com.tv.server.dto.LanguageDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.LanguageService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/language")
public class LanguageController {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private LanguageService languageService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        languageService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody LanguageDto languageDto) {
        // 保存校验
        ValidatorUtil.require(languageDto.getName(), "Name");
        ValidatorUtil.length(languageDto.getName(), "Name", 1, 50);
        ResponseDto responseDto = new ResponseDto();
        languageService.save(languageDto);
        responseDto.setContent(languageDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        languageService.delete(id);
        return responseDto;
    }
}
