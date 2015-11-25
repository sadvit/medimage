package com.sadvit.controllers;

import com.sadvit.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<byte[]> processBinary(@PathVariable String id) {
        return imageResponse(processService.processBinary(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blur/{id}")
    public ResponseEntity<byte[]> processBlur(@PathVariable String id) {
        return imageResponse(processService.processBlur(id));
    }

}
