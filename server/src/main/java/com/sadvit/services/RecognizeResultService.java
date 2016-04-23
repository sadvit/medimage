package com.sadvit.services;

import com.sadvit.to.NetworkEntityTO;
import com.sadvit.to.RecognizeResultTO;
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

    public List<NetworkEntityTO> getRecognizeResults(Long userId) {
        return null; //repository.getRecognizeResults(userId); TODO get by user
    }

    public void saveRecognizeResult(Long networkId, RecognizeResultTO result) {
        //repository.addRecognizeResult(networkId, result); TODO add by user
    }

}
