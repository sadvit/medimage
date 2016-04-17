package com.sadvit.repositories;

import com.sadvit.models.NetworkEntity;
import com.sadvit.models.User;
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
    private UserRepository userRepository;

    @Autowired
    private HibernateTemplate template;

    public List<NetworkEntity> getNetworks(String username) {
        Session session = template.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findNetworksByUser");
        query.setString("username", username);
        return query.list();
    }

    public NetworkEntity getNetwork(Integer networkId) {
        return template.get(NetworkEntity.class, networkId);
    }

    public void addNetwork(String username, NetworkEntity networkEntity) {
        template.execute(session -> {
            Query findUserByName = session.getNamedQuery("findUserByName");
            findUserByName.setString("username", username);
            User user = (User) findUserByName.list().stream().findFirst().get();
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
