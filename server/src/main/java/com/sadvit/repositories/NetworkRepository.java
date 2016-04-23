package com.sadvit.repositories;

import com.sadvit.models.NetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sadvit on 4/16/16.
 */
public interface NetworkRepository extends JpaRepository<NetworkEntity, Long> {

    /*public Set<NetworkEntity> getNetworks(Integer userId) {
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

    public List<NetworkEntityTO> getRecognizeResults(Integer userId) {
        return template.execute(session -> {
            User user = (User) session.get(User.class, userId);
            Set<NetworkEntity> entities = user.getNetworkEntities();
            List<NetworkEntityTO> infos = new ArrayList<>();
            entities.forEach(entity -> {
                NetworkEntityTO info = new NetworkEntityTO();
                info.createFromEntity(entity);
                infos.add(info);
            });
            return infos;
        });
    }*/

}
