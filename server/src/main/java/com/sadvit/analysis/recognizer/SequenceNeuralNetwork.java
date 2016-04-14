package com.sadvit.analysis.recognizer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SequenceNeuralNetwork {

    private double w[][][];

    public SequenceNeuralNetwork(int layouts[]) {
        Initialize(layouts);
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                for (int k = 0; k < w[i][j].length; k++) {
                    w[i][j][k] = Math.random() - 0.5;
                }
            }
        }
    }

    public SequenceNeuralNetwork(String path) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        int layouts[] = new int[dis.readInt()];
        for (int i = 0; i < layouts.length; i++) {
            layouts[i] = dis.readInt();
        }
        Initialize(layouts);
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                for (int k = 0; k < w[i][j].length; k++) {
                    w[i][j][k] = dis.readDouble();
                }
            }
        }
    }

    public void Initialize(int layouts[]) {
        w = new double[layouts.length - 1][][];
        for (int i = 0; i < w.length; i++) {
            w[i] = new double[layouts[i]][layouts[i + 1]];
        }
    }

    public void saveToFile(String path) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
        dos.writeInt(this.w.length + 1);
        dos.writeInt(w[0].length);
        for (int i = 0; i < w.length; i++) {
            dos.writeInt(w[i][0].length);
        }
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                for (int k = 0; k < w[i][j].length; k++) {
                    dos.writeDouble(w[i][j][k]);
                }
            }
        }
    }

    public double function(double x) {
        return Math.tanh(x);
    }

    public double derivative(double x) {
        //return  (1 / Math.cosh(x));
        return (2 * Math.pow(Math.cosh(x), 2)) / (Math.pow(Math.cosh(x) + 1, 2));
    }

    double[] function(double x[]) {
        double result[] = new double[x.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = function(x[i]);
        }
        return result;
    }

    void sout() {
        for (int i = 0; i < w[0].length; i++) {
            for (int j = 0; j < w[0][0].length; j++) {
                System.out.print(w[0][i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public double[] getOutput(double params[], int i) {
        double result[] = new double[w[i][0].length];
        for (int j = 0; j < w[i][0].length; j++) {
            double buf = 0;
            for (int k = 0; k < w[i].length; k++) {
                buf += params[k] * w[i][k][j];
            }
            result[j] = buf;
        }
        return result;
    }

    public double[] process(double params[]) {
        double result[] = new double[params.length];
        System.arraycopy(params, 0, result, 0, params.length);
        for (int i = 0; i < w.length; i++) {
            result = function(getOutput(result, i));
        }
        return result;
    }

    public void training(double params[], double teacher[]) {
        double results[][] = new double[w.length + 1][];
        results[0] = params;
        results[1]=getOutput(params, 0);
        for (int i = 1; i < w.length; i++) {
            results[i + 1] = getOutput(function(results[i]), i);
        }
        double delta[] = new double[teacher.length];
        double result[] = results[results.length - 1];
        for (int i = 0; i < result.length; i++) {
            delta[i] = teacher[i] - result[i];
        }
        for (int i = w.length - 1; i >= 0; i--) {
            result = results[i + 1];
            for (int j = 0; j < w[i].length; j++) {
                for (int k = 0; k < delta.length; k++) {
                    w[i][j][k] += 0.00001* derivative(result[k]) * function(results[i][j]) * delta[k];
                }
            }
            double nextDelta[] = new double[w[i].length];
            for (int j = 0; j < nextDelta.length; j++) {
                double buf = 0;
                for (int k = 0; k < delta.length; k++) {
                    buf += delta[k] * w[i][j][k];
                }
                nextDelta[j] = buf;
            }
            delta = nextDelta;
        }
    }
}
