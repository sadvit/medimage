package com.sadvit.services;

import com.sadvit.models.NetworkEntity;
import com.sadvit.utils.RecognizeUtils;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Kohonen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sadvit
 */
@Service
public class RecognizeService {

    public static int INPUT_PARAMS_NUMBER = 8; // params from images

    @Value("${medimage.content}")
    private String content;

    @Autowired
    private UserService userService;

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private NetworkService networkService;

    public void learn(Integer networkId, Map<String, String> images) {
        List<String> answers = RecognizeUtils.extractAnswers(images);
        int outputParamsNumber = answers.size();
        DataSet dataSet = new DataSet(INPUT_PARAMS_NUMBER, outputParamsNumber);
        NetworkEntity networkEntity = networkService.getNetwork(networkId);
        networkEntity.setPerceptron(new Kohonen(INPUT_PARAMS_NUMBER, outputParamsNumber));
        networkEntity.setAnswers(answers);
        for (String imageId : images.keySet()) {
            String value = images.get(imageId);
            double[] params = paramsService.findParams(imageId);
            dataSet.addRow(new DataSetRow(params, RecognizeUtils.getInput(answers, value)));
        }
        networkEntity.getNeuralNetwork().learn(dataSet);
        networkService.addNetwork(networkEntity);
    }

    public Map<String, String> recognize(Integer networkId, List<String> images) {
        NetworkEntity networkEntity = networkService.getNetwork(networkId);
        if (networkEntity.getNeuralNetwork() != null) {
            Map<String, String> resultMap = new HashMap<>();
            for (String imageId : images) {
                double[] params = paramsService.findParams(imageId);
                networkEntity.getNeuralNetwork().setInput(params);
                networkEntity.getNeuralNetwork().calculate();
                double[] result = networkEntity.getNeuralNetwork().getOutput();
                String answer = RecognizeUtils.getOutput(result, networkEntity.getAnswers());
                resultMap.put(imageId, answer);
            }
            return resultMap;
        }
        throw new InternalError();


        /*String name = userService.getCurrentUserName();
        String memoryPath = content + "/" + name + "/networks/memory.txt";
        StatisticalRecognizer recognizer = new StatisticalRecognizer(new HistogramDistribution(), memoryPath);

        Network network = networkService.getNetwork(networkId);
        if (network.getNeuralNetwork() != null) {
            Map<String, String> resultMap = new HashMap<>();
            for (String imageId : images) {
                double[] params = paramsService.findParams(imageId);
                String answer = recognizer.recognize(params);
                resultMap.put(imageId, answer);
            }
            return resultMap;
        }
        throw new InternalError();*/
    }

}
