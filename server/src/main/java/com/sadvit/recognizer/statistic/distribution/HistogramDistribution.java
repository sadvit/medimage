package com.sadvit.recognizer.statistic.distribution;

public class HistogramDistribution implements Distribution {

    @Override
    public int getNumberOfParams() {
        return 0;
    }

//    public double function(double[] args, double xw[]) {
//        double s = args[0];
//        double h = args[1];
//        double x = args[args.length - 1];
//        if (s <= x && x <= s + h * n + h) {
//            double r = args[2 + (int) ((x - s) / h)];
//            return Math.max(r, 0.0001);
//        }
//        return 0.0001;
//    }
    @Override
    public double function(double[][] args, double[] x) {
//        l:
//        for (int i = 2; i < args.length; i++) {
//            for (int j = 0; j < x.length; j++) {
//                if (x[j] < args[i][j] || x[j] > args[i][j] + args[1][j]) {
//                    continue l;
//                }
//            }
//            return args[i][x.length];
//        }
//        return 0;
        double r = 1;
        for (int i = 0; i < args.length; i++) {
            if (args[i][0] <= x[i] && x[i] <= args[i][0] + (args[0].length - 2) * args[i][1]) {
                r *= 10*Math.max(0.001, args[i][2 + (int) ((x[i] - args[i][0]) / args[i][1])]);
            } else {
                r *= 0.00001;
            }
        }
        return r;
    }

    @Override
    public double function(double[] args, double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
