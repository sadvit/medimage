package com.sadvit.controllers;

import com.sadvit.analysis.recognizer.statistic.StatisticalRecognizer;
import com.sadvit.analysis.recognizer.statistic.distribution.HistogramDistribution;
import com.sadvit.models.Network;
import com.sadvit.models.User;
import com.sadvit.repositories.NetworkRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URL;
import java.util.Map;

/**
 * Created by sadvit on 6/1/16.
 */
@RestController
@RequestMapping("/generate")
public class GenerateController {

    private static Logger logger = Logger.getLogger(GenerateController.class);

    @Autowired
    private NetworkRepository networkRepository;

    public static final String MEMORY = "memory.txt";

    @RequestMapping(method = RequestMethod.GET)
    public void generateNetwork(@AuthenticationPrincipal User user) {
        logger.info("Generate memory for user: " + user);
        Map<String, double[][]> memory = null;
        URL resource = getClass().getClassLoader().getResource(MEMORY);
        if (resource != null) {
            logger.info("Resources found");
            try {
                File file = new File(resource.getFile());
                StatisticalRecognizer recognizer = new StatisticalRecognizer(new HistogramDistribution(), file);
                memory = recognizer.getMap();
                logger.info("Default memory found");
            } catch (Exception e) {
                logger.warn("Default memory not found");
                e.printStackTrace();
            }
            Network networkEntity = new Network();
            networkEntity.setMemory(memory);
            networkEntity.setName("Default");
            networkEntity.setUser(user);
            networkRepository.save(networkEntity);
            logger.info("Default memory saved");
        } else {
            logger.warn("Resources not found");
        }
    }

}