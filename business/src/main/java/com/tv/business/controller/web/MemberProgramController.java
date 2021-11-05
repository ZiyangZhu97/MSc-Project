package com.tv.business.controller.web;

import com.tv.server.dto.MemberProgramDto;
import com.tv.server.dto.PageDto;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.MemberProgramService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("webMemberProgramController")
@RequestMapping("/web/member-program")
public class MemberProgramController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberProgramController.class);
    public static final String BUSINESS_NAME = "Subscribe program";

    @Resource
    private MemberProgramService memberProgramService;

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/subscribe")
    public ResponseDto subscribe(@RequestBody MemberProgramDto memberProgramDto) {
        // 保存校验
        ValidatorUtil.require(memberProgramDto.getMemberId(), "Member ID");
        ValidatorUtil.require(memberProgramDto.getProgramId(), "Program ID");

        ResponseDto responseDto = new ResponseDto();
        memberProgramDto = memberProgramService.subscribe(memberProgramDto);
        responseDto.setContent(memberProgramDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/get-enroll")
    public ResponseDto getEnroll(@RequestBody MemberProgramDto memberProgramDto) {
        ResponseDto responseDto = new ResponseDto();
        memberProgramDto = memberProgramService.getSub(memberProgramDto);
        responseDto.setContent(memberProgramDto);
        return responseDto;
    }
}
