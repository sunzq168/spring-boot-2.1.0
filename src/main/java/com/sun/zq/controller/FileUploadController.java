package com.sun.zq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class FileUploadController {

    @RequestMapping("/")
    public String index(){
        return "upload";
    }

    //上传文件会自动绑定到MultipartFile中
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file) throws IOException {
        log.info("description=" + description);
        // 接收参数description
        System.out.println("description=" + description);
        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println("path=" + path);
        // 如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            // 上传文件名
            String fileName = file.getName();
            File filePath = new File(path, fileName);
            // 判断路径是否存在，不存在就创建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdir();
            }
            // 将上传文件保存到一个目标文件当中
            file.transferTo(new File(path +File.separator + fileName));
            return "success";
        } else {
            return "error1";
        }

    }
}
