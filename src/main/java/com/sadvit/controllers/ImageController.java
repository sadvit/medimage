package com.sadvit.controllers;

import com.sadvit.services.ImageService;
import com.sadvit.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.sadvit.utils.WebUtils.imageResponse;

@RestController
@RequestMapping("/images1")
public class ImageController {

    @Autowired
    private ImageService imageService;

	/*@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable String id) {
		return imageResponse(imageService.getImageAsByteArray(id));
	}*/

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
