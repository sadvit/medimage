package com.sadvit.repositories;

import com.sadvit.dto.NetworkEntityInfo;
import com.sadvit.dto.RecognizeResultInfo;
import com.sadvit.dto.RecognizeValueInfo;
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

    public List<NetworkEntityInfo> getRecognizeResults(Integer userId) {
        return template.execute(session -> {
            User user = (User) session.get(User.class, userId);
            Set<NetworkEntity> entities = user.getNetworkEntities();
            List<NetworkEntityInfo> infos = new ArrayList<>();
            entities.forEach(entity -> {
                NetworkEntityInfo info = new NetworkEntityInfo();
                info.createFromEntity(entity);
                infos.add(info);
            });
            return infos;
        });
    }

    public RecognizeResultInfo getRecognizeResult(Integer recognizeResultId) {
        RecognizeResult result = template.get(RecognizeResult.class, recognizeResultId);
        RecognizeResultInfo recognizeResultInfo = new RecognizeResultInfo();
        recognizeResultInfo.createFromEntity(result);
        return recognizeResultInfo;
    }

    public void addRecognizeResult(Integer networkId, RecognizeResultInfo recognizeResultInfo) {
        template.execute(session -> {
            NetworkEntity network = (NetworkEntity) session.get(NetworkEntity.class, networkId);
            Set<RecognizeResult> results = network.getRecognizeResults();
            if (results == null) {
                results = new HashSet<>();
                results.add(recognizeResultInfo.convertToEntity());
            } else {
                results.add(recognizeResultInfo.convertToEntity());
            }
            session.update(network);
            session.flush();
            return null;
        });
    }

}
