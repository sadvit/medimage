package com.sadvit.controllers;

import com.sadvit.to.NetworkTO;
import com.sadvit.to.ResultTO;
import com.sadvit.models.Network;
import com.sadvit.models.User;
import com.sadvit.services.NetworkService;
import com.sadvit.services.RecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sadvit on 3/20/16.
 */
@RestController
@RequestMapping("/networks")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    @Autowired
    private RecognizeService recognizeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<NetworkTO> getNetworks(@AuthenticationPrincipal User user) {
        return networkService.getNetworks(user.getId());
    }

    @RequestMapping(value = "/learn", method = RequestMethod.POST)
    public void learn(@RequestBody ResultTO recognizeResult, @AuthenticationPrincipal User user) {
        recognizeService.learn(user.getId(), recognizeResult);
    }

    @RequestMapping(value = "/recognize/{networkId}", method = RequestMethod.POST)
    public ResultTO recognize(@RequestBody List<String> images, @PathVariable("networkId") Long networkId) {
        return recognizeService.recognize(networkId, images);
    }

}
