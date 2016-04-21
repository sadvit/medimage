package com.sadvit.controllers;

import com.sadvit.dto.NetworkEntityInfo;
import com.sadvit.dto.RecognizeResultInfo;
import com.sadvit.dto.RecognizeValueInfo;
import com.sadvit.models.NetworkEntity;
import com.sadvit.models.RecognizeResult;
import com.sadvit.models.User;
import com.sadvit.services.RecognizeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sadvit on 4/19/16.
 */
@RestController
@RequestMapping("/results")
public class RecognizeResultController {

    @Autowired
    private RecognizeResultService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<NetworkEntityInfo> getRecognizeResults(@AuthenticationPrincipal User user) {
        return service.getRecognizeResults(user.getId());
    }

    @RequestMapping(value = "/{networkId}", method = RequestMethod.POST)
    public void saveRecognizeResult(@RequestBody RecognizeResultInfo result, @PathVariable("networkId") Integer networkId) {
        service.saveRecognizeResult(networkId, result);
    }

}