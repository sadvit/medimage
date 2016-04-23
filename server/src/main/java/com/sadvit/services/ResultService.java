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

import java.util.List;
import java.util.stream.Collectors;

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
        return networks.stream()
                .map(network -> {
                    NetworkTO convert = conversionService.convert(network, NetworkTO.class);
                    List<Result> results = resultRepository.findByNetworkId(network.getId());
                    List<ResultTO> resultsTO = results.stream()
                            .map(result -> conversionService.convert(result, ResultTO.class))
                            .collect(Collectors.toList());
                    convert.setResults(resultsTO);
                    return convert;
                })
                .collect(Collectors.toList());
    }

    public void saveResult(Long networkId, ResultTO resultTO) {
        Result result = conversionService.convert(resultTO, Result.class);
        Network network = networkRepository.findOne(networkId);
        result.setNetwork(network);
        resultRepository.save(result);
    }

}
