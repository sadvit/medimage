package com.sadvit.controllers;

import com.sadvit.models.CacheObject;
import com.sadvit.models.Chain;
import com.sadvit.enums.OperationType;
import com.sadvit.models.User;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import com.sadvit.models.ChainElement;
import com.sadvit.services.ChainService;
import com.sadvit.to.ChainTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 21.12.15.
 */
@RestController
@RequestMapping("/chains")
public class ChainController {

    @Autowired
    private ChainService chainService;

    @RequestMapping(method = RequestMethod.POST)
    public void saveChain(@RequestBody ChainTO chainTO, @AuthenticationPrincipal User user) {
        chainService.saveChain(user.getId(), chainTO);
    }

    @RequestMapping(value = "/one/{imageId}", method = RequestMethod.POST)
    public CacheObject processChain(@PathVariable String imageId, @RequestBody List<ChainElement> elements) {
        // TODO change to ChainTO
        return chainService.processChain(imageId, elements);
    }

    @RequestMapping(value = "/many/{chainId}", method = RequestMethod.POST)
    public List<String> processChains(@RequestBody List<String> images, @PathVariable("chainId") Long chainId) {
        return chainService.processChains(images, chainId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ChainTO> getChains(@AuthenticationPrincipal User user) {
        return chainService.getChains(user.getId());
    }

}
