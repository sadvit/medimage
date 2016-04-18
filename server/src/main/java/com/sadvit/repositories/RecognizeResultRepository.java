package com.sadvit.repositories;

import com.sadvit.models.Chain;
import com.sadvit.models.NetworkEntity;
import com.sadvit.models.RecognizeResult;
import com.sadvit.models.User;
import org.hibernate.Hibernate;
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

    public Map<NetworkEntity, List<RecognizeResult>> getRecognizeResults(Integer userId) {
        return template.execute(session -> {
            User user = (User) session.get(User.class, userId);
            Map<NetworkEntity, List<RecognizeResult>> results = new HashMap<>();
            user.getNetworkEntities().forEach(networkEntity -> {
                networkEntity.getRecognizeResults().forEach(recognizeResult -> {
                    if (results.get(networkEntity) == null) {
                        List<RecognizeResult> list = new ArrayList<>();
                        list.add(recognizeResult);
                        results.put(networkEntity, list);
                    } else {
                        results.get(networkEntity).add(recognizeResult);
                    }
                });
            });
            return results;
        });
    }

    public RecognizeResult getRecognizeResult(Integer recognizeResultId) {
        return template.get(RecognizeResult.class, recognizeResultId);
    }

    public void addRecognizeResult(Integer userId, Integer networkId, RecognizeResult recognizeResult) {
        template.execute(session -> {
            User user = (User) session.get(User.class, userId);
            NetworkEntity network = user.getNetworkEntities().stream().filter(entity -> entity.getId().equals(networkId)).findAny().get();
            Set<RecognizeResult> recognizeResults = network.getRecognizeResults();
            if (recognizeResults == null) {
                recognizeResults = new HashSet<>();
                recognizeResults.add(recognizeResult);
            } else {
                recognizeResults.add(recognizeResult);
            }
            session.update(user);
            session.flush();
            return null;
        });
    }

}
