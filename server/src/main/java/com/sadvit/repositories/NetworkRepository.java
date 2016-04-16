package com.sadvit.repositories;

import com.sadvit.models.Network;
import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@SuppressWarnings("unchecked")
@Repository
public class NetworkRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HibernateTemplate template;

    public List<Network> getNetworks(String username) {
        String hql = "SELECT U.networks FROM User AS U WHERE U.name = ?";
        return (List<Network>) template.find(hql, username);
    }

    public Network getNetwork(String username, Integer networkId) {
        List<Network> networks = getNetworks(username);
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
