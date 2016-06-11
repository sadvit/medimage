package com.sadvit.services;

import com.sadvit.models.Network;
import com.sadvit.models.User;
import com.sadvit.repositories.NetworkRepository;
import com.sadvit.repositories.UserRepository;
import com.sadvit.to.NetworkTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadvit on 4/16/16.
 */
@Service
public class NetworkService {

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    public Network getNetwork(Long networkId) {
        return networkRepository.findOne(networkId);
    }

    public List<NetworkTO> getNetworks(Long userId) {
        List<Network> networks = networkRepository.findByUserId(userId);
        List<NetworkTO> networkTOs = new ArrayList<>();
        for (Network network: networks) {
            networkTOs.add(conversionService.convert(network, NetworkTO.class));
        }
        return networkTOs;
    }

    public Network addNetwork(Long userId, Network network) {
        User user = userRepository.findOne(userId);
        network.setUser(user);
        return networkRepository.save(network);
    }

}
