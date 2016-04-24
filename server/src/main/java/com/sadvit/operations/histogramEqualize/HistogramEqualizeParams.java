package com.sadvit.operations.histogramEqualize;

import javax.persistence.*;

/**
 * Created by sadvit on 4/24/16.
 */
@Entity
@Table(name = "HISTOGRAM_EQUALIZE_PARAMS")
public class HistogramEqualizeParams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int radius;

    private boolean isGlobal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

}
