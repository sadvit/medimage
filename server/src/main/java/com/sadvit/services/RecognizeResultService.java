package com.sadvit.services;

import com.sadvit.dto.NetworkEntityInfo;
import com.sadvit.dto.RecognizeResultInfo;
import com.sadvit.dto.RecognizeValueInfo;
import com.sadvit.models.RecognizeResult;
import com.sadvit.repositories.RecognizeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sadvit on 4/19/16.
 */
@Service
public class RecognizeResultService {

    @Autowired
    private RecognizeResultRepository repository;

    public List<NetworkEntityInfo> getRecognizeResults(Integer userId) {
        return repository.getRecognizeResults(userId);
    }

    public void saveRecognizeResult(Integer networkId, RecognizeResultInfo result) {
        repository.addRecognizeResult(networkId, result);
    }

}
