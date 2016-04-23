package com.sadvit.controllers;

import com.sadvit.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.sadvit.utils.WebUtils.responseImage;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable String id) {
		return responseImage(imageService.getImageAsByteArray(id));
	}

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getImages() {
        return imageService.getAllImageNames();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postImage(MultipartFile multipartFile) {
        return imageService.saveImage(multipartFile);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteImages(@PathVariable String id) {
		imageService.deleteImage(id);
    }

}