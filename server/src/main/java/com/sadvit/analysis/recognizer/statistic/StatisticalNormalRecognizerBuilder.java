package com.sadvit.analysis.recognizer.statistic;

import com.sadvit.analysis.recognizer.RecognizerBuilder;
import com.sadvit.analysis.recognizer.statistic.distribution.Distribution;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author meskill
 */
public class StatisticalNormalRecognizerBuilder extends RecognizerBuilder {

    public StatisticalNormalRecognizerBuilder(String filedir, String[] classes) {
        super(filedir, classes);
    }

    @Override
    public StatisticalRecognizer buildRecognizer(Distribution func) {
        Map<String, double[][]> result = new TreeMap<String, double[][]>();
        for (String cls : this.sample.keySet()) {
            double buf[][] = this.sample.get(cls);
            double msi[][] = new double[buf[0].length][2];
            for (int i = 0; i < buf[0].length; i++) {
                double m = 0, si = 0;
                for (int j = 0; j < buf.length; j++) {
                    m += buf[j][i];
                }
                m /= buf.length;
                for (int j = 0; j < buf.length; j++) {
                    si += (buf[j][i] - m) * (buf[j][i] - m);
                }
                si /= buf.length;
                si = Math.sqrt(si);
                msi[i][0] = m;
                msi[i][1] = si;
            }
            result.put(cls, msi);
        }
        return new StatisticalRecognizer(func, result);
    }

}
