package com.sadvit.controllers;

import com.sadvit.models.NetworkEntity;
import com.sadvit.services.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<NetworkEntity> getNetworks() {
        return networkService.getNetworks();
    }

}
