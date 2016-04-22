package com.sadvit.repositories;

import com.sadvit.to.NetworkEntityTO;
import com.sadvit.to.RecognizeResultTO;
import com.sadvit.models.NetworkEntity;
import com.sadvit.models.RecognizeResult;
import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by sadvit on 4/19/16.
 */
@Repository
@SuppressWarnings("unchecked")
public class RecognizeResultRepository {

    @Autowired
    private HibernateTemplate template;

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
    }

    public RecognizeResultTO getRecognizeResult(Integer recognizeResultId) {
        RecognizeResult result = template.get(RecognizeResult.class, recognizeResultId);
        RecognizeResultTO recognizeResultTO = new RecognizeResultTO();
        recognizeResultTO.createFromEntity(result);
        return recognizeResultTO;
    }

    public void addRecognizeResult(Integer networkId, RecognizeResultTO recognizeResultTO) {
        template.execute(session -> {
            NetworkEntity network = (NetworkEntity) session.get(NetworkEntity.class, networkId);
            Set<RecognizeResult> results = network.getRecognizeResults();
            if (results == null) {
                results = new HashSet<>();
                results.add(recognizeResultTO.convertToEntity());
            } else {
                results.add(recognizeResultTO.convertToEntity());
            }
            session.update(network);
            session.flush();
            return null;
        });
    }

}
