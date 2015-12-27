package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.services.BinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
