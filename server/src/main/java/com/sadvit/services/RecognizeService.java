package com.sadvit.services;

import com.sadvit.models.Network;
import com.sadvit.utils.RecognizeUtils;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sadvit
 */
@Service
public class RecognizeService {

    public static int INPUT_PARAMS_NUMBER = 8;
    public static int OUTPUT_PARAMS_NUMBER = 14;

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private NetworkService networkService;

    public void learn(Integer networkId, Map<String, String> images) {
        List<String> answers = RecognizeUtils.extractAnswers(images);
        OUTPUT_PARAMS_NUMBER = answers.size();
        DataSet dataSet = new DataSet(INPUT_PARAMS_NUMBER, OUTPUT_PARAMS_NUMBER);
        Network network = networkService.getNetwork(networkId);
        network.setPerceptron(new MultiLayerPerceptron(INPUT_PARAMS_NUMBER, 20, OUTPUT_PARAMS_NUMBER));
        network.setAnswers(answers);
        for (String imageId : images.keySet()) {
            String value = images.get(imageId);
            double[] params = paramsService.findParams(imageId);
            dataSet.addRow(new DataSetRow(params, RecognizeUtils.getInput(answers, value)));
        }
        network.getPerceptron().learn(dataSet);
    }

    public Map<String, String> recognize(Integer networkId, List<String> images) {
        Network network = networkService.getNetwork(networkId);
        if (network.getPerceptron() != null) {
            Map<String, String> resultMap = new HashMap<>();
            for (String imageId : images) {
                double[] params = paramsService.findParams(imageId);
                network.getPerceptron().setInput(params);
                network.getPerceptron().calculate();
                double[] result = network.getPerceptron().getOutput();
                String answer = RecognizeUtils.getOutput(result, network.getAnswers());
                resultMap.put(imageId, answer);
            }
            return resultMap;
        }
        throw new InternalError();
    }

}
