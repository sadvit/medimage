package com.sadvit.controllers;

import com.sadvit.to.NetworkTO;
import com.sadvit.to.ResultTO;
import com.sadvit.models.User;
import com.sadvit.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sadvit on 4/19/16.
 */
@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<NetworkTO> getResults(@AuthenticationPrincipal User user) {
        return service.getResults(user.getId());
    }

    @RequestMapping(value = "/{networkId}", method = RequestMethod.POST)
    public void saveResult(@RequestBody ResultTO result, @PathVariable("networkId") Long networkId) {
        service.saveResult(networkId, result);
    }

}
