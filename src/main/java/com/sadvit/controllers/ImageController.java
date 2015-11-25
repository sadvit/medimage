package com.sadvit.controllers;

import com.sadvit.services.ImageService;
import com.sadvit.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.sadvit.utils.WebUtils.imageResponse;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

	@Autowired
	private ProcessService processService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable String id) {
		return imageResponse(imageService.getImageAsByteArray(id));
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

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/process/binary")
	public ResponseEntity<byte[]> processBinary(@PathVariable String id) {
		return imageResponse(processService.processBinary(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/process/blur")
	public ResponseEntity<byte[]> processBlur(@PathVariable String id) {
		return imageResponse(processService.processBlur(id));
	}

}