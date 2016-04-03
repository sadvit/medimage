package com.sadvit.utils;

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
        double max = Arrays.stream(output).max().getAsDouble();
        int index = Arrays.binarySearch(output, max);
        return answers.get(index);
    }

    public static List<String> extractAnswers(Map<String, String> images) {
        Set<String> result = new TreeSet<>();
        for (String value : images.values()) {
            result.add(value);
        }
        return result.stream().collect(Collectors.toList());
    }

}
