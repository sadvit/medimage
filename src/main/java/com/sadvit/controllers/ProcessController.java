package com.sadvit.controllers;

import com.sadvit.operations.HandlerType;
import com.sadvit.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sadvit.utils.WebUtils.imageResponse;

/**
 * Created by sadvit on 25.11.15.
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @RequestMapping(method = RequestMethod.GET, value = "/binary/{id}")
    public ResponseEntity<byte[]> processBinary(@PathVariable String id, @RequestBody Object params) {
        return imageResponse(processService.process(id, HandlerType.BINARY, params));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blur/{id}")
    public ResponseEntity<byte[]> processBlur(@PathVariable String id, @RequestBody Object params) {
        return imageResponse(processService.process(id, HandlerType.BLUR, params));
    }

}
