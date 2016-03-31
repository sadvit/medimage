package com.sadvit.controllers;

import com.sadvit.models.Network;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadvit on 3/20/16.
 */
@RestController
@RequestMapping("/networks")
public class NetworkController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Network> getNetworks() {
        List<Network> networks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Network network = new Network();
            network.setName("Network " + i);
            network.setId(generateId());
            networks.add(network);
        }
        return networks;
    }

    public static int generateId() {
        return (int) (Math.random() * 5000);
    }

}
