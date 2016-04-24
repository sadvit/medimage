package com.sadvit.operations.canny;

import javax.persistence.*;

/**
 * Created by sadvit on 4/24/16.
 */
@Entity
@Table(name = "CANNY_PARAMS")
public class CannyParams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int blurRadius;

    private boolean saveTrace;

    private boolean dynamicThreshold;

    private float threshLow;

    private float threshHigh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }

    public boolean isSaveTrace() {
        return saveTrace;
    }

    public void setSaveTrace(boolean saveTrace) {
        this.saveTrace = saveTrace;
    }

    public boolean isDynamicThreshold() {
        return dynamicThreshold;
    }

    public void setDynamicThreshold(boolean dynamicThreshold) {
        this.dynamicThreshold = dynamicThreshold;
    }

    public float getThreshLow() {
        return threshLow;
    }

    public void setThreshLow(float threshLow) {
        this.threshLow = threshLow;
    }

    public float getThreshHigh() {
        return threshHigh;
    }

    public void setThreshHigh(float threshHigh) {
        this.threshHigh = threshHigh;
    }

}
