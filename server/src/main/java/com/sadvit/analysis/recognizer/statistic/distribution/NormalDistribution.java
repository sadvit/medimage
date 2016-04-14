package com.sadvit.analysis.recognizer.statistic.distribution;

/**
 *
 * @author meskill
 */
public class NormalDistribution implements Distribution {

    /**
     * m, sigma, x
     *
     * @param args
     * @param x
     * @return
     */
    @Override
    public double function(double args[], double x) {
        return Math.exp(-Math.pow(x - args[0], 2) / (2 * args[1] * args[1])) / (args[1] * Math.sqrt(2 * Math.PI));
    }

    @Override
    public int getNumberOfParams() {
        return 2;
    }

    @Override
    public double function(double args[][], double[] x) {
        double r = 1;
        for (int i = 0; i < x.length; i++) {
            r *= function(args[i], x[i]);
        }
        return r;
    }

}
