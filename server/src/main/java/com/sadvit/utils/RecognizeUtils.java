package com.sadvit.utils;

import com.sadvit.models.RecognizeResult;
import com.sadvit.models.RecognizeValue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sadvit on 4/3/16.
 */
public class RecognizeUtils {

    public static double[] getInput(List<String> answers, String answer) {
        int index = answers.indexOf(answer);
        double[] result = new double[answers.size()];
        result[index] = 1;
        return result;
    }

    public static String getOutput(double[] output, List<String> answers) {
        double max = output[0];
        int maxpos = 0;
        for (int i = 0; i < output.length; i++) {
            if (output[i] > max) {
                max = output[i];
                maxpos = i;
            }
        }
        return answers.get(maxpos);
    }

    public static List<String> extractAnswers(RecognizeResult recognizeResult) {
        Set<String> result = new TreeSet<>();
        for (RecognizeValue value : recognizeResult.getValues()) {
            result.add(value.getValue());
        }
        return result.stream().collect(Collectors.toList());
    }

    public static Map<String, String> revertMap(Map<String, String> images) {
        Map<String, String> result = new HashMap<>();
        for (String key: images.keySet()) {
            String value = images.get(key);
            result.put(value, key);
        }
        return result;
    }

    public static Map<String, String> extractMap(RecognizeResult recognizeResult) {
        Map<String, String> result = new HashMap<>();
        recognizeResult.getValues().forEach(recognizeValue -> {
            result.put(recognizeValue.getTempId(), recognizeValue.getImageId());
        });
        return result;
    }

}
