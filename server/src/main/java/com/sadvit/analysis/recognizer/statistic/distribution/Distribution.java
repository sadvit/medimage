package com.sadvit.analysis.recognizer.statistic.distribution;

public interface Distribution {

    public int getNumberOfParams();

    public double function(double args[][], double x[]);

    public double function(double args[], double x);
}
