package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.operations.OperationType;
import com.sadvit.services.ImageCache;
import com.sadvit.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

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
    public CacheObject processBinary(@PathVariable String id, @RequestBody Map params) {
        // TODO to binary service
		return toCache(processService.process(id, OperationType.BINARY, params));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/blur/{id}")
    public CacheObject processBlur(@PathVariable String id, @RequestBody Map params) {
		return toCache(processService.process(id, OperationType.BLUR, params));
    }

	private CacheObject toCache(byte[] image) {
		String imageId = UUID.randomUUID().toString();
		cache.put(imageId, image);
		return new CacheObject(imageId);
	}

}
