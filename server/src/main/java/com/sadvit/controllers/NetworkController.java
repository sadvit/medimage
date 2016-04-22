package com.sadvit.controllers;

import com.sadvit.to.RecognizeResultTO;
import com.sadvit.models.NetworkEntity;
import com.sadvit.models.User;
import com.sadvit.services.NetworkService;
import com.sadvit.services.RecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public Set<NetworkEntity> getNetworks(@AuthenticationPrincipal User user) {
        return networkService.getNetworks(user.getId());
    }

    @RequestMapping(value = "/learn", method = RequestMethod.POST)
    public void learn(@RequestBody RecognizeResultTO recognizeResult, @AuthenticationPrincipal User user) {
        recognizeService.learn(user.getId(), recognizeResult);
    }

    @RequestMapping(value = "/recognize/{networkId}", method = RequestMethod.POST)
    public RecognizeResultTO recognize(@RequestBody List<String> images, @PathVariable("networkId") Integer networkId) {
        return recognizeService.recognize(networkId, images);
    }

}
