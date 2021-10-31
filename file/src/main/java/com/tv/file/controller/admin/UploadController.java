package com.tv.file.controller.admin;

import com.tv.server.domain.Test;
import com.tv.server.dto.ResponseDto;
import com.tv.server.service.TestService;
import com.tv.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController     //返回json数据用RestController，返回页面用Controller
@RequestMapping("/admin")
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    public static final String BUSINESS_NAME = "文件上传";

//    @Resource
//    private TestService testService;
//    @RequestMapping("/upload")
//    public List<Test> test() {
//        return testService.list();
//    }

    @RequestMapping("/upload")
    //file参数接收.vue里append()里的‘file’
    public ResponseDto upload(@RequestParam MultipartFile file) throws IOException {
        LOG.info("上传文件开始：{}", file);
        LOG.info(file.getOriginalFilename());
        LOG.info(String.valueOf(file.getSize()));

        // 保存文件到本地
        String fileName = file.getOriginalFilename();
        String key = UuidUtil.getShortUuid();
        String fullPath = "D:/MScProject/tv_file/program/" + key + "-" + fileName;
        File dest = new File(fullPath);
        file.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        ResponseDto responseDto = new ResponseDto();
        return responseDto;

    }
}
