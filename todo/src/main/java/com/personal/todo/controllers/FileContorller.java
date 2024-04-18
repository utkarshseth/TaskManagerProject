package com.personal.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileContorller {

    Logger logger = LoggerFactory.getLogger(FileContorller.class);

    @PostMapping("/single")
    public String uploadSingle(@RequestParam("image") MultipartFile file) {
        logger.info("Name:{} ", file.getName());
        logger.info("Content Type:{} ", file.getContentType());
        logger.info("Original name:{} ", file.getOriginalFilename());
        logger.info("Size:{} ", file.getSize());
        return "File test";
    }

    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files) {
        Arrays.stream(files)
                .forEach(file -> {
                    logger.info("Name:{}", file.getOriginalFilename());
                    logger.info("Type:{}", file.getContentType());
                    System.out.println("-------------------------------------");
                });
        return "Uploaded multiple files";
    }

    @GetMapping("/serve-image")
    public void serveFileHandler(HttpServletResponse response) {

        try {
            InputStream inputStream = new FileInputStream("/home/utkarsh/Downloads/image/scenery.jpeg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(inputStream, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
