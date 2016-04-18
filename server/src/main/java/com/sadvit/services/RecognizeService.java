package com.sadvit.services;

import com.sadvit.analysis.recognizer.statistic.StatisticalRecognizer;
import com.sadvit.analysis.recognizer.statistic.distribution.HistogramDistribution;
import com.sadvit.models.NetworkEntity;
import com.sadvit.models.RecognizeResult;
import com.sadvit.models.RecognizeValue;
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

    public static int INPUT_PARAMS_NUMBER = 8;

    @Value("${medimage.content}")
    private String content;

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private NetworkService networkService;

    public void learn(Integer userId, RecognizeResult recognizeResult) {
        List<String> answers = RecognizeUtils.extractAnswers(recognizeResult);
        int outputParamsNumber = answers.size();
        DataSet dataSet = new DataSet(INPUT_PARAMS_NUMBER, outputParamsNumber);
        NetworkEntity networkEntity = new NetworkEntity();
        networkEntity.setName(recognizeResult.getName());
        networkEntity.setPerceptron(new Kohonen(INPUT_PARAMS_NUMBER, outputParamsNumber));
        networkEntity.setAnswers(answers);
        for (RecognizeValue recognizeValue: recognizeResult.getValues()) {
            String imageId = recognizeValue.getTempId();
            String value = recognizeValue.getValue();
            double[] params = paramsService.findParams(imageId);
            dataSet.addRow(new DataSetRow(params, RecognizeUtils.getInput(answers, value)));
        }
        networkEntity.getNeuralNetwork().learn(dataSet);
        networkService.addNetwork(userId, networkEntity);
    }

    public RecognizeResult recognize(Integer networkId, List<String> images) {
        NetworkEntity networkEntity = networkService.getNetwork(networkId);
        if (networkEntity.getNeuralNetwork() != null) {
            RecognizeResult recognizeResult = new RecognizeResult();
            Set<RecognizeValue> values = new HashSet<>();
            for (String imageId : images) {
                double[] params = paramsService.findParams(imageId);
                networkEntity.getNeuralNetwork().setInput(params);
                networkEntity.getNeuralNetwork().calculate();
                double[] result = networkEntity.getNeuralNetwork().getOutput();
                String answer = RecognizeUtils.getOutput(result, networkEntity.getAnswers());
                RecognizeValue value = new RecognizeValue();
                value.setTempId(imageId);
                value.setValue(answer);
                values.add(value);
            }
            recognizeResult.setValues(values);
            return recognizeResult;
        } else if (networkEntity.getMemory() != null) {
            StatisticalRecognizer recognizer = new StatisticalRecognizer(new HistogramDistribution(), networkEntity.getMemory());
            RecognizeResult recognizeResult = new RecognizeResult();
            Set<RecognizeValue> values = new HashSet<>();
            for (String imageId : images) {
                double[] params = paramsService.findParams(imageId);
                String answer = recognizer.recognize(params);
                RecognizeValue value = new RecognizeValue();
                value.setTempId(imageId);
                value.setValue(answer);
                values.add(value);
            }
            recognizeResult.setValues(values);
            return recognizeResult;
        }
        throw new InternalError();
    }

}
