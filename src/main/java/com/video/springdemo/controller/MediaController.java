package com.video.springdemo.controller;

import com.video.springdemo.config.MedioHttpRequestHandler;
import io.swagger.annotations.Api;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName: MediaController
 * @Description: 视频文件处理
 * @Author: Administrator
 * @Date: 2022/4/14 18:14
 */
@Api(tags = "视频文件处理API")
@RestController
public class MediaController {

    String url = "D:\\media\\";

    @Resource
    private MedioHttpRequestHandler medioHttpRequestHandler;

    /**
     * 获取视频
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/api/media/video")
    public void getPlayResource(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Path path = Paths.get(url + "meetingrec_0.mp4");
        if (Files.exists(path)) {
            String mimeType = Files.probeContentType(path);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(MedioHttpRequestHandler.ATTR_FILE, path);
            medioHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }
}