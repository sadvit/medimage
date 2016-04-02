package com.sadvit.controllers;

import com.sadvit.services.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sadvit on 4/2/16.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private ImageService imageService;

    Logger logger = Logger.getLogger(UploadController.class);

    @RequestMapping(method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file) {
        logger.info("GET FILE: " + file.getName());
        if (!file.isEmpty()) {
            imageService.saveImage(file);
        }
    }

}
