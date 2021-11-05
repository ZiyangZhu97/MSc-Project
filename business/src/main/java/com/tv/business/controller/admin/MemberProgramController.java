package com.tv.business.controller.admin;

import com.tv.server.dto.MemberProgramDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.MemberProgramService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/memberProgram")
public class MemberProgramController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberProgramController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private MemberProgramService memberProgramService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        memberProgramService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberProgramDto memberProgramDto) {
        // 保存校验
        ValidatorUtil.require(memberProgramDto.getMemberId(), "Member ID");
        ValidatorUtil.require(memberProgramDto.getProgramId(), "Program ID");
        ValidatorUtil.require(memberProgramDto.getAt(), "Subscribed Time");
        ResponseDto responseDto = new ResponseDto();
        memberProgramService.save(memberProgramDto);
        responseDto.setContent(memberProgramDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        memberProgramService.delete(id);
        return responseDto;
    }
}
