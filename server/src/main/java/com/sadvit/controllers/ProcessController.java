package com.sadvit.controllers;

import com.sadvit.operations.HandlerType;
import com.sadvit.services.ImageCache;
import com.sadvit.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sadvit.utils.WebUtils.imageResponse;

/**
 * Created by sadvit on 25.11.15.
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

	@Autowired
	private ImageCache cache;

    @RequestMapping(method = RequestMethod.POST, value = "/binary/{id}")
    public String processBinary(@PathVariable String id, @RequestBody Object params) {
		return toCache(processService.process(id, HandlerType.BINARY, params));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/blur/{id}")
    public String processBlur(@PathVariable String id, @RequestBody Object params) {
		return toCache(processService.process(id, HandlerType.BLUR, params));
    }

	private String toCache(byte[] image) {
		String imageId = UUID.randomUUID().toString();
		cache.put(imageId, image);
		return "{\"id\": \"" + imageId + "\"}";
		//return "{\"id\":\"" + imageId + "\"}";
	}

}
