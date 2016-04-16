package com.sadvit.services;

import com.sadvit.models.Network;
import com.sadvit.repositories.NetworkRepository;
import com.sadvit.repositories.UserRepository;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@Service
public class NetworkService {

    @Autowired
    private UserService userService;

    @Autowired
    private NetworkRepository networkRepository;

    public Network getNetwork(Integer networkId) {
        String username = userService.getCurrentUser();
        return networkRepository.getNetwork(username, networkId);
    }

    public List<Network> getNetworks() {
        String username = userService.getCurrentUser();
        return networkRepository.getNetworks(username);
    }

}
