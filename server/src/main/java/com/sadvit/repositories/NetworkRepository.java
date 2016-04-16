package com.sadvit.repositories;

import com.sadvit.models.Chain;
import com.sadvit.models.ChainElement;
import com.sadvit.models.Network;
import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.nio.ch.Net;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@Repository
public class NetworkRepository {

    @Autowired
    private UserRepository userRepository;

    public Set<Network> getNetworks(String username) {
        User user = userRepository.getUser(username);
        return user.getNetworks();
    }

    public Network getNetwork(String username, Integer networkId) {
        Set<Network> networks = getNetworks(username);
        return networks.stream().filter(network -> Objects.equals(network.getId(), networkId)).findFirst().get();
    }

    public void addNetwork(String username, Network network) {
        User user = userRepository.getUser(username);
        Set<Network> networks = user.getNetworks();
        if (networks == null) {
            networks = new HashSet<>();
            networks.add(network);
            user.setNetworks(networks);
        } else {
            networks.add(network);
        }
        userRepository.updateUser(user);
    }

}
