package com.sadvit.analysis.recognizer;

import com.sadvit.analysis.recognizer.statistic.distribution.Distribution;

import java.util.Map;

/**
 *
 * @author meskill
 */
public abstract class RecognizerBuilder {

    public Map<String, double[][]> sample;

    public RecognizerBuilder(String filedir, String classes[]) {
        /*sample = new HashMap<String, double[][]>();
        File d = new File(filedir);
        for (final String cls : classes) {
            System.out.println(cls);
            File fi[] = d.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().startsWith(cls);
                }
            });
            double buf[][] = new double[fi.length][];
            for (int i = 0; i < fi.length; i++) {
                try {
                    buf[i] = ImageProcess.getParams(ImageHandler.process(ImageIO.read(fi[i])));
                } catch (Throwable e) {
                    System.err.println("Error with " + fi[i].getName());
                }
            }
            this.sample.put(cls, buf);
        }*/
    }

    public abstract Recognizer buildRecognizer(Distribution func);
}
