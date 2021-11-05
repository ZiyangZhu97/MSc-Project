package com.tv.server.service;

import com.tv.server.domain.MemberProgram;
import com.tv.server.domain.MemberProgramExample;
import com.tv.server.dto.MemberProgramDto;
import com.tv.server.dto.PageDto;
import com.tv.server.mapper.MemberProgramMapper;
import com.tv.server.util.CopyUtil;
import com.tv.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class MemberProgramService {

    @Resource
    private MemberProgramMapper memberProgramMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberProgramExample memberProgramExample = new MemberProgramExample();
        List<MemberProgram> memberProgramList = memberProgramMapper.selectByExample(memberProgramExample);
        PageInfo<MemberProgram> pageInfo = new PageInfo<>(memberProgramList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberProgramDto> memberProgramDtoList = CopyUtil.copyList(memberProgramList, MemberProgramDto.class);
        pageDto.setList(memberProgramDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(MemberProgramDto memberProgramDto) {
        MemberProgram memberProgram = CopyUtil.copy(memberProgramDto, MemberProgram.class);
        if (StringUtils.isEmpty(memberProgramDto.getId())) {
            this.insert(memberProgram);
        } else {
            this.update(memberProgram);
        }
    }

    /**
     * 新增
     */
    private void insert(MemberProgram memberProgram) {
        Date now = new Date();
        memberProgram.setId(UuidUtil.getShortUuid());
        memberProgramMapper.insert(memberProgram);
    }

    /**
     * 更新
     */
    private void update(MemberProgram memberProgram) {
        memberProgramMapper.updateByPrimaryKey(memberProgram);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        memberProgramMapper.deleteByPrimaryKey(id);
    }
}
