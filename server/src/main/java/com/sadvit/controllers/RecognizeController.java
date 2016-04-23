package com.sadvit.controllers;

import com.sadvit.models.User;
import com.sadvit.services.RecognizeService;
import com.sadvit.to.NetworkTO;
import com.sadvit.to.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sadvit on 4/23/16.
 */
@RestController
@RequestMapping("/recognize")
public class RecognizeController {

    @Autowired
    private RecognizeService recognizeService;

    @RequestMapping(method = RequestMethod.PUT)
    public NetworkTO learn(@AuthenticationPrincipal User user, @RequestBody ResultTO recognizeResult) {
        return recognizeService.learn(user.getId(), recognizeResult);
    }

    @RequestMapping(value = "/{networkId}", method = RequestMethod.POST)
    public ResultTO recognize(@RequestBody List<String> images, @PathVariable("networkId") Long networkId) {
        return recognizeService.recognize(networkId, images);
    }

}
