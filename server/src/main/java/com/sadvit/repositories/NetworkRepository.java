package com.sadvit.repositories;

import com.sadvit.models.Chain;
import com.sadvit.models.NetworkEntity;
import com.sadvit.models.User;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
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
    private HibernateTemplate template;

    public Set<NetworkEntity> getNetworks(Integer userId) {
        return template.execute(session -> {
            User user = (User) session.get(User.class, userId);
            Hibernate.initialize(user.getNetworkEntities());
            return user.getNetworkEntities();
        });
    }

    public NetworkEntity getNetwork(Integer networkId) {
        return template.get(NetworkEntity.class, networkId);
    }

    public void addNetwork(Integer userId, NetworkEntity networkEntity) {
        template.execute(session -> {
            User user = (User) session.get(User.class, userId);
            Set<NetworkEntity> networkEntities = user.getNetworkEntities();
            if (networkEntities == null) {
                networkEntities = new HashSet<>();
                networkEntities.add(networkEntity);
                user.setNetworkEntities(networkEntities);
            } else {
                networkEntities.add(networkEntity);
            }
            session.update(user);
            session.flush();
            return null;
        });
    }

}
