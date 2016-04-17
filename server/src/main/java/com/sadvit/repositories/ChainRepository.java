package com.sadvit.repositories;

import com.sadvit.models.Chain;
import com.sadvit.models.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
@Repository
@SuppressWarnings("unchecked")
public class ChainRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HibernateTemplate template;

    public List<Chain> getChains(String username) {
        Session session = template.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findChainsByUser");
        query.setString("username", username);
        return query.list();
    }

    public Chain getChain(Integer chainId) {
        return template.get(Chain.class, chainId);
    }

    public void addChain(String username, Chain chain) {
        template.execute(session -> {
            Query findUserByName = session.getNamedQuery("findUserByName");
            findUserByName.setString("username", username);
            User user = (User) findUserByName.list().stream().findFirst().get();
            Set<Chain> chains = user.getChains();
            if (chains == null) {
                chains = new HashSet<>();
                chains.add(chain);
                user.setChains(chains);
            } else {
                chains.add(chain);
            }
            session.update(user);
            session.flush();
            return null;
        });
    }

}
