package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.chains.ChainElement;
import com.sadvit.services.BinaryService;
import com.sadvit.services.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sadvit on 21.12.15.
 */
@RestController
@RequestMapping("/chains")
public class ChainController {

    @Autowired
    private ChainService chainService;

    @RequestMapping(method = RequestMethod.POST, value = "/chain/{id}")
    public CacheObject processChain(@PathVariable String id, @RequestBody ChainElement[] chain) {
        return chainService.processChain(id, chain);
    }

}
