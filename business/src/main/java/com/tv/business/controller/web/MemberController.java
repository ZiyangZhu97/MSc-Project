package com.tv.business.controller.web;

import com.tv.server.dto.*;
import com.tv.server.service.MemberService;
import com.tv.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController("webMemberController")
@RequestMapping("/web/member")
public class MemberController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    public static final String BUSINESS_NAME = "Member";

    @Resource
    private MemberService memberService;

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/register")
    public ResponseDto register(@RequestBody MemberDto memberDto) {
        // 保存校验
        ValidatorUtil.require(memberDto.getEmail(), "email");
        ValidatorUtil.require(memberDto.getPassword(), "password");
        ValidatorUtil.length(memberDto.getName(), "name", 1, 50);

        // 密码加密
        memberDto.setPassword(DigestUtils.md5DigestAsHex(memberDto.getPassword().getBytes()));

        ResponseDto responseDto = new ResponseDto();
        memberService.save(memberDto);
        responseDto.setContent(memberDto);
        return responseDto;
    }
    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseDto login(@RequestBody MemberDto memberDto, HttpServletRequest request) {
        memberDto.setPassword(DigestUtils.md5DigestAsHex(memberDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();
        LoginMemberDto loginMemberDto = memberService.login(memberDto);

        request.getSession().setAttribute(Constants.LOGIN_USER, loginMemberDto);

        responseDto.setContent(loginMemberDto);
        return responseDto;
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public ResponseDto logout(HttpServletRequest request) {
        ResponseDto responseDto = new ResponseDto();
        request.getSession().removeAttribute(Constants.LOGIN_USER);
        return responseDto;
    }

}
