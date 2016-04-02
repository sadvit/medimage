package com.sadvit.recognizer.statistic;

import com.sadvit.recognizer.RecognizerBuilder;
import com.sadvit.recognizer.statistic.distribution.Distribution;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author meskill
 */
public class StatisticalHistogramRecognizerBuilder extends RecognizerBuilder {

    int n;

    public StatisticalHistogramRecognizerBuilder(String filedir, String[] classes, int n) {
        super(filedir, classes);
        this.n = n;
    }

//    @Override
//    public StatisticalRecognizer buildRecognizer(Distribution func) {
//        Map<String, double[][]> result = new TreeMap<>();
//        for (String cls : this.sample.keySet()) {
//            double buf[][] = this.sample.get(cls);
//            TreeMap<double[], Integer> msi = new TreeMap<>(new Comparator<double[]>() {
//
//                @Override
//                public int compare(double[] o1, double[] o2) {
//                    for (int j = 0; j < o1.length; j++) {
//                        if (Math.abs(o1[j] - o2[j]) > 0.0000001) {
//                            return Double.compare(o1[j], o2[j]);
//                        }
//                    }
//                    return 0;
//                }
//            });
//            int n = buf[0].length;
//            double min[] = new double[n + 1];
//            double max[] = new double[n];
//            for (int i = 0; i < n; i++) {
//                min[i] = buf[0][i];
//                max[i] = buf[0][i];
//                for (int j = 0; j < buf.length; j++) {
//                    min[i] = Math.min(min[i], buf[j][i]);
//                    max[i] = Math.max(max[i], buf[j][i]);
//                }
//            }
//            double h[] = new double[n + 1];
//            for (int i = 0; i < n; i++) {
//                h[i] = (max[i] - min[i]) / this.n;
//            }
//            for (int i = 0; i < buf.length; i++) {
//                double p[] = new double[n + 1];
//                for (int j = 0; j < n; j++) {
//                    p[j] = min[j] + h[j] * ((int) ((buf[i][j] - min[j]) / h[j]));
//                }
//                if (msi.containsKey(p)) {
//                    msi.put(p, msi.get(p) + 1);
//                } else {
//                    msi.put(p, 1);
//                }
//            }
//            double ms[][] = new double[msi.size() + 2][];
//            ms[0] = min;
//            ms[1] = h;
//            int i = 2;
//            for (double b[] : msi.keySet()) {
//                ms[i] = b;
//                ms[i][n] = (double) msi.get(b) / buf.length;
//                i++;
//            }
//            result.put(cls, ms);
//        }
//        return new StatisticalRecognizer(func, result);
//    }
    @Override
    public StatisticalRecognizer buildRecognizer(Distribution func) {
        Map<String, double[][]> result = new TreeMap<String, double[][]>();
        for (String cls : this.sample.keySet()) {
            double buf[][] = this.sample.get(cls);
            double msi[][] = new double[buf[0].length][n + 3];
            for (int i = 0; i < buf[0].length; i++) {
                double min = buf[0][i], max = buf[0][i];
                for (int j = 1; j < buf.length; j++) {
                    min = Math.min(min, buf[j][i]);
                    max = Math.max(max, buf[j][i]);
                }
                double h = (max - min) / n;
                msi[i][0] = min;
                msi[i][1] = h;
                for (int j = 0; j < buf.length; j++) {
                    msi[i][Math.min((int) ((buf[j][i] - min) / h), n - 1) + 2]++;
                }
                for (int j = 0; j < n; j++) {
                    msi[i][j + 2] /= buf.length;
                }
            }
            result.put(cls, msi);
        }
        return new StatisticalRecognizer(func, result);
    }


}
