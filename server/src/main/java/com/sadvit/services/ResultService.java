package com.sadvit.services;

import com.sadvit.models.Network;
import com.sadvit.models.Result;
import com.sadvit.repositories.NetworkRepository;
import com.sadvit.repositories.ResultRepository;
import com.sadvit.to.NetworkTO;
import com.sadvit.to.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadvit on 4/19/16.
 */
@Service
public class ResultService {

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ConversionService conversionService;

    public List<NetworkTO> getResults(Long userId) {
        List<Network> networks = networkRepository.findByUserId(userId);
        List<NetworkTO> newtworks = new ArrayList<>();
        for (Network network : networks) {
            NetworkTO convert = conversionService.convert(network, NetworkTO.class);
            List<Result> results = resultRepository.findByNetworkId(network.getId());
            List<ResultTO> resultsTO = new ArrayList<>();
            for (Result result : results) {
                resultsTO.add(conversionService.convert(result, ResultTO.class));
            }
            convert.setResults(resultsTO);
            newtworks.add(convert);
        }
        return newtworks;
    }

    public void saveResult(Long networkId, ResultTO resultTO) {
        Result result = conversionService.convert(resultTO, Result.class);
        Network network = networkRepository.findOne(networkId);
        result.setNetwork(network);
        resultRepository.save(result);
    }

}
