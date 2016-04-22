package com.sadvit.utils;

import com.sadvit.to.RecognizeResultTO;
import com.sadvit.to.RecognizeValueTO;

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

    public static List<String> extractAnswers(RecognizeResultTO recognizeResult) {
        Set<String> result = new TreeSet<>();
        for (RecognizeValueTO value : recognizeResult.getValues()) {
            result.add(value.getValue());
        }
        return result.stream().collect(Collectors.toList());
    }

}
