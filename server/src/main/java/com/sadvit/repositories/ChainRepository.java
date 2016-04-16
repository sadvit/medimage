package com.sadvit.repositories;

import com.sadvit.models.Chain;
import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@Repository
public class ChainRepository {

    @Autowired
    private UserRepository userRepository;

    public Set<Chain> getChains(String username) {
        User user = userRepository.getUser(username);
        return user.getChains();
    }

    public void addChain(String username, Chain chain) {
        User user = userRepository.getUser(username);
        Set<Chain> chains = user.getChains();
        if (chains == null) {
            chains = new HashSet<>();
            chains.add(chain);
            user.setChains(chains);
        } else {
            chains.add(chain);
        }
        userRepository.updateUser(user);
    }

}
