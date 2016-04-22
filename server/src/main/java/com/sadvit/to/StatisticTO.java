package com.sadvit.to;

import java.util.Map;

/**
 * Created by sadvit on 4/20/16.
 */
public class StatisticTO {

    private String name;

    private String format;

    private int width;

    private int height;

    private Map<Integer, Integer> histogram;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map<Integer, Integer> getHistogram() {
        return histogram;
    }

    public void setHistogram(Map<Integer, Integer> histogram) {
        this.histogram = histogram;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
