package com.sadvit.controllers;

import com.sadvit.models.Network;
import com.sadvit.services.NetworkService;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping(method = RequestMethod.GET)
    public Set<Network> getNetworks() {
        return networkService.getNetworks();
    }

}
