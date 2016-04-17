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

    public NetworkEntity getNetwork(Integer networkId) {
        if (networkId == -1) {
            NetworkEntity networkEntity = new NetworkEntity();
            LocalDateTime currentTime = LocalDateTime.now();
            networkEntity.setName(currentTime.getHour() + ":" + currentTime.getMinute());
            return networkEntity;
        }
        return networkRepository.getNetwork(networkId);
    }

    public Set<NetworkEntity> getNetworks(Integer userId) {
        return networkRepository.getNetworks(userId);
    }

    public void addNetwork(Integer userId, NetworkEntity networkEntity) {
        networkRepository.addNetwork(userId, networkEntity);
    }

}
