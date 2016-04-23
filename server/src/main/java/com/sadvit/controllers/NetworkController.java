package com.sadvit.controllers;

import com.sadvit.models.User;
import com.sadvit.services.NetworkService;
import com.sadvit.to.NetworkTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sadvit on 3/20/16.
 */
@RestController
@RequestMapping("/networks")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    @RequestMapping(method = RequestMethod.GET)
    public List<NetworkTO> getNetworks(@AuthenticationPrincipal User user) {
        return networkService.getNetworks(user.getId());
    }

}