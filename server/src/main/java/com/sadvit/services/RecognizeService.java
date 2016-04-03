package com.sadvit.services;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by sadvit on 30.12.15.
 */
@Service
public class RecognizeService {

    public static final int INPUT_PARAMS_NUMBER = 7;
    public static final int OUTPUT_PARAMS_NUMBER = 14;

    public List<String> mappingResults(LinkedHashMap<List<Double>, String> map) {
        List<String> results = new ArrayList<>();
        for (List<Double> key : map.keySet()) {
            results.add(map.get(key));
        }
        return results;
    }

    public double[] generateResultForNumber(int number) {
        double[] result = new double[OUTPUT_PARAMS_NUMBER];
        result[number] = 1;
        return result;
    }

    public DataSet generateDataSet(LinkedHashMap<List<Double>, String> map) {
        Set<List<Double>> keys = map.keySet();
        List<String> results = mappingResults(map);
        DataSet dataSet = new DataSet(INPUT_PARAMS_NUMBER, OUTPUT_PARAMS_NUMBER);
        for (List<Double> key: keys) {
            double[] _key = key.stream().mapToDouble(Double::doubleValue).toArray();
            String value = map.get(key);
            int position = results.indexOf(value);
            dataSet.addRow(new DataSetRow(_key, generateResultForNumber(position)));
        }
        return dataSet;
    }

    MultiLayerPerceptron perceptron;

    public void learn(Map<String, String> images) {
        //DataSet dataSet = generateDataSet(images);
        //perceptron = new MultiLayerPerceptron();
        //perceptron.learn(dataSet);
    }

    public Map<String, String> recognize(List<String> params) {
        return null;
    }

}
