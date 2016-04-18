package com.sadvit.services;

import com.sadvit.models.NetworkEntity;
import com.sadvit.models.RecognizeResult;
import com.sadvit.repositories.RecognizeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sadvit on 4/19/16.
 */
@Service
public class RecognizeResultService {

    @Autowired
    private RecognizeResultRepository repository;

    public Map<NetworkEntity, List<RecognizeResult>> getRecognizeResults(Integer userId) {
        return repository.getRecognizeResults(userId);
    }

    public void saveRecognizeResult(Integer userId, Integer networkId, RecognizeResult result) {
        repository.addRecognizeResult(userId, networkId, result);
    }

}
