package com.sadvit.services;

import com.sadvit.models.NetworkEntity;
import com.sadvit.repositories.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@Service
public class NetworkService {

    @Autowired
    private NetworkRepository networkRepository;

    public NetworkEntity getNetwork(Long networkId) {
        return networkRepository.findOne(networkId);
    }

    public Set<NetworkEntity> getNetworks(Long userId) {
        return null; //networkRepository.getNetworks(userId); TODO impl find by user
    }

    public void addNetwork(Long userId, NetworkEntity networkEntity) {
        //networkRepository.addNetwork(userId, networkEntity); TODO add by user
    }

}
