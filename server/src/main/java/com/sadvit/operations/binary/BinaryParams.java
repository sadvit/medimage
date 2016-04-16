package com.sadvit.operations.binary;

import javax.persistence.*;

/**
 * Created by sadvit on 29.11.15.
 */
@Entity
@Table(name = "BINARY_PARAMS")
public class BinaryParams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated
    private BinaryType type;

    private double[] localSquare;

    private double[] localGaussian;

    private double[] localSauvola;

    private int thresold;

    private boolean isDown;

    public double[] getLocalSquare() {
        return localSquare;
    }

    public void setLocalSquare(double[] localSquare) {
        this.localSquare = localSquare;
    }

    public double[] getLocalGaussian() {
        return localGaussian;
    }

    public void setLocalGaussian(double[] localGaussian) {
        this.localGaussian = localGaussian;
    }

    public double[] getLocalSauvola() {
        return localSauvola;
    }

    public void setLocalSauvola(double[] localSauvola) {
        this.localSauvola = localSauvola;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setIsDown(boolean isDown) {
        this.isDown = isDown;
    }

    public BinaryType getType() {
        return type;
    }

    public void setType(BinaryType type) {
        this.type = type;
    }

    public int getThresold() {
        return thresold;
    }

    public void setThresold(int thresold) {
        this.thresold = thresold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

}
