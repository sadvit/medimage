package com.sadvit.services;

import com.sadvit.models.NetworkEntity;
import com.sadvit.repositories.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sadvit on 4/16/16.
 */
@Service
public class NetworkService {

    @Autowired
    private UserService userService;

    @Autowired
    private NetworkRepository networkRepository;

    public NetworkEntity getNetwork(Integer networkId) {
        if (networkId == -1) {
            return new NetworkEntity();
        }
        return networkRepository.getNetwork(networkId);
    }

    public List<NetworkEntity> getNetworks() {
        String username = userService.getCurrentUser();
        return networkRepository.getNetworks(username);
    }

    public void addNetwork(NetworkEntity networkEntity) {
        String username = userService.getCurrentUser();
        networkRepository.addNetwork(username, networkEntity);
    }

}
