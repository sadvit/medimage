package com.sadvit.controllers;

import com.sadvit.models.User;
import com.sadvit.services.ChainService;
import com.sadvit.to.ChainTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sadvit on 21.12.15.
 */
@RestController
@RequestMapping("/chains")
public class ChainController {

    @Autowired
    private ChainService chainService;

    @RequestMapping(method = RequestMethod.POST)
    public ChainTO saveChain(@AuthenticationPrincipal User user, @RequestBody ChainTO chainTO) {
        return chainService.saveChain(user.getId(), chainTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ChainTO> getChains(@AuthenticationPrincipal User user) {
        return chainService.getChains(user.getId());
    }

}
