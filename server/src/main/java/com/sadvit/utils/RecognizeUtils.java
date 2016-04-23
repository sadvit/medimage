package com.sadvit.utils;

import com.sadvit.to.ResultTO;
import com.sadvit.to.ValueTO;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

    public static List<String> extractAnswers(ResultTO recognizeResult) {
        Set<String> result = new TreeSet<>();
        for (ValueTO value : recognizeResult.getValues()) {
            result.add(value.getValue());
        }
        return result.stream().collect(Collectors.toList());
    }

}
