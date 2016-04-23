package com.sadvit.analysis.recognizer.statistic;

import com.sadvit.analysis.recognizer.Recognizer;
import com.sadvit.analysis.recognizer.statistic.distribution.Distribution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticalRecognizer implements Recognizer {

    Distribution func;
    Map<String, double[][]> map;

    public Distribution getFunc() {
        return func;
    }

    public void setFunc(Distribution func) {
        this.func = func;
    }

    public Map<String, double[][]> getMap() {
        return map;
    }

    public void setMap(Map<String, double[][]> map) {
        this.map = map;
    }

    public StatisticalRecognizer(Distribution func) {
        this.map = new TreeMap<>();
    }

    public StatisticalRecognizer(Distribution func, File file) {
        this.func = func;
        this.map = new TreeMap<>();
        try {
            loadFromFile(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StatisticalRecognizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StatisticalRecognizer(Distribution func, String fileName) {
        this.func = func;
        this.map = new TreeMap<>();
        try {
            loadFromFile(fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StatisticalRecognizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StatisticalRecognizer(Distribution func, Map<String, double[][]> map) {
        this.func = func;
        this.map = map;
    }

    @Override
    public String recognize(double params[]) {
        String maxs = "";
        double maxr = 0;
        for (String k : this.map.keySet()) {
            double p[][] = this.map.get(k);
            double r = this.func.function(p, params);
//            for (int i = 0; i < p.length; i++) {
//                double buf[] = new double[p[i].length + 1];
//                System.arraycopy(p[i], 0, buf, 0, p[i].length);
//                buf[p[i].length] = params[i];
//                r *= 1 * this.func.function(buf);
//            }
            if (r > maxr) {
                maxr = r;
                maxs = k;
            }
        }
        return maxs;
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String name = sc.next().toLowerCase();
            int m = sc.nextInt();
            int k = sc.nextInt();
            double buf[][] = new double[m][k];
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < k; l++) {
                    buf[j][l] = Double.parseDouble(sc.next());
                }
            }
            map.put(name, buf);
            sc.nextLine();
        }
    }

    public void loadFromFile(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String name = sc.next().toLowerCase();
            int m = sc.nextInt();
            int k = sc.nextInt();
            double buf[][] = new double[m][k];
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < k; l++) {
                    buf[j][l] = Double.parseDouble(sc.next());
                }
            }
            map.put(name, buf);
            sc.nextLine();
        }
    }

    public void saveToFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(map.size() + "\n");
        for (Map.Entry a : map.entrySet()) {
            fw.write(a.getKey() + " ");
            double next[][] = (double[][]) a.getValue();
            fw.write(next.length + " " + next[0].length + " ");
            for (double x[] : next) {
                for (double y : x) {
                    fw.write(y + " ");
                }
            }
            fw.write("\n");
        }
        fw.close();
    }

//    public double[][] distribution(double sample[][]) {
//        double msi[][] = new double[sample[0].length][2];
//        for (int i = 0; i < sample[0].length; i++) {
//            double m = 0, si = 0;
//            for (int j = 0; j < sample.length; j++) {
//                m += sample[j][i];
//            }
//            m /= sample.length;
//            for (int j = 0; j < sample.length; j++) {
//                si += (sample[j][i] - m) * (sample[j][i] - m);
//            }
//            si /= sample.length;
//            si = Math.sqrt(si);
//            msi[i][0] = m;
//            msi[i][1] = si;
//        }
//        return msi;
//    }
//
//    public void start() throws IOException {
//        File d = new File("G:\\Java\\study\\CourseWork1\\Objects images");
//        for (final String spec : species) {
//            System.out.println(spec);
//            File fi[] = d.listFiles(new FilenameFilter() {
//
//                @Override
//                public boolean accept(File dir, String name) {
//                    return name.startsWith(spec);
//                }
//            });
//            double[][] sample = new double[fi.length][];
//            for (int i = 0; i < fi.length; i++) {
//                try {
//                    BufferedImage bi = ImageProcess.prepareForRecognize(ImageIO.read(fi[i]));
//                    sample[i] = ImageProcess.getParams(bi);
//                } catch (Throwable e) {
//                    System.err.println("Error with " + fi[i].getName());
//                }
//            }
//            double msi[][] = distribution(sample);
//            this.map.put(spec, msi);
//        }
//    }
}
