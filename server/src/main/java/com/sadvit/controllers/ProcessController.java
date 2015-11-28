package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.operations.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.services.BinaryService;
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
    private BinaryService binaryService;

    @RequestMapping(method = RequestMethod.POST, value = "/binary/{id}")
    public CacheObject processBinary(@PathVariable String id, @RequestBody BinaryParams params) {
		return binaryService.process(id, params);
    }

}
