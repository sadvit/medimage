package com.sadvit.services;

import com.sadvit.analysis.recognizer.statistic.StatisticalRecognizer;
import com.sadvit.analysis.recognizer.statistic.distribution.HistogramDistribution;
import com.sadvit.to.NetworkTO;
import com.sadvit.to.ResultTO;
import com.sadvit.to.ValueTO;
import com.sadvit.models.Network;
import com.sadvit.utils.RecognizeUtils;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Kohonen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sadvit
 */
@Service
public class RecognizeService {

    public static int INPUT_PARAMS_NUMBER = 8;

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private NetworkService networkService;

    @Autowired
    private ConversionService conversionService;

    public NetworkTO learn(Long userId, ResultTO recognizeResult) {
        List<String> answers = RecognizeUtils.extractAnswers(recognizeResult);
        int outputParamsNumber = answers.size();
        DataSet dataSet = new DataSet(INPUT_PARAMS_NUMBER, outputParamsNumber);
        Network network = new Network();
        network.setName(recognizeResult.getName());
        network.setPerceptron(new Kohonen(INPUT_PARAMS_NUMBER, outputParamsNumber));
        network.setAnswers(answers);
        for (ValueTO recognizeValue: recognizeResult.getValues()) {
            String imageId = recognizeValue.getTempId();
            String value = recognizeValue.getValue();
            double[] params = paramsService.findParams(imageId);
            dataSet.addRow(new DataSetRow(params, RecognizeUtils.getInput(answers, value)));
        }
        network.getNeuralNetwork().learn(dataSet);
        Network newNetwork = networkService.addNetwork(userId, network);
        return conversionService.convert(newNetwork, NetworkTO.class);
    }

    public ResultTO recognize(Long networkId, List<String> images) {
        Network network = networkService.getNetwork(networkId);
        if (network.getNeuralNetwork() != null) {
            ResultTO recognizeResult = new ResultTO();
            List<ValueTO> values = new ArrayList<>();
            for (String imageId : images) {
                double[] params = paramsService.findParams(imageId);
                network.getNeuralNetwork().setInput(params);
                network.getNeuralNetwork().calculate();
                double[] result = network.getNeuralNetwork().getOutput();
                String answer = RecognizeUtils.getOutput(result, network.getAnswers());
                ValueTO value = new ValueTO();
                value.setTempId(imageId);
                value.setValue(answer);
                values.add(value);
            }
            recognizeResult.setValues(values);
            return recognizeResult;
        } else if (network.getMemory() != null) {
            StatisticalRecognizer recognizer = new StatisticalRecognizer(new HistogramDistribution(), network.getMemory());
            ResultTO recognizeResult = new ResultTO();
            List<ValueTO> values = new ArrayList<>();
            for (String imageId : images) {
                double[] params = paramsService.findParams(imageId);
                String answer = recognizer.recognize(params);
                ValueTO value = new ValueTO();
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
