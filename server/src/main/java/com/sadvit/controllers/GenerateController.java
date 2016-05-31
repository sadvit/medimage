package com.sadvit.controllers;

import com.sadvit.analysis.recognizer.statistic.StatisticalRecognizer;
import com.sadvit.analysis.recognizer.statistic.distribution.HistogramDistribution;
import com.sadvit.models.Network;
import com.sadvit.models.User;
import com.sadvit.repositories.NetworkRepository;
import com.sadvit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sadvit on 6/1/16.
 */
@RestController
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public static final String MEMORY = "memory.txt";

    @RequestMapping(method = RequestMethod.GET)
    public String generateNetwork(@AuthenticationPrincipal User user) {
        if (user == null)
            return "Not found";

        Map<String, double[][]> memory = null;
        try {
            File file = resourceLoader.getResource(MEMORY).getFile();
            StatisticalRecognizer recognizer = new StatisticalRecognizer(new HistogramDistribution(), file);
            memory = recognizer.getMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Network networkEntity = new Network();
        networkEntity.setMemory(memory);
        networkEntity.setName("Default");
        networkEntity.setUser(user);
        networkRepository.save(networkEntity);

        return "OK";
    }

}
