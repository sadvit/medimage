package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.services.ChainService;
import com.sadvit.to.ChainRequestTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sadvit on 25.11.15.
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ChainService chainService;

    @RequestMapping(value = "/images", method = RequestMethod.PUT)
    public List<CacheObject> processChain(@RequestBody ChainRequestTO request) {
        return chainService.process(request);
    }

}
