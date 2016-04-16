package com.sadvit.repositories;

import com.sadvit.models.Chain;
import com.sadvit.models.User;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@SuppressWarnings("unchecked")
@Repository
public class ChainRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HibernateTemplate template;

    public List<Chain> getChains(String username) {
        String hql = "SELECT USER.chains FROM User AS USER WHERE USER.name = ?";
        return (List<Chain>) template.find(hql, username);
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
