package com.sadvit.models;

import com.sadvit.enums.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.blur.BlurParams;
import com.sadvit.operations.canny.CannyParams;
import com.sadvit.operations.histogramEqualize.HistogramEqualizeParams;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vitaly.sadovskiy.
 */
@Entity
@Table(name = "CHAIN_ELEMENTS")
public class ChainElement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chain_element_id", nullable = false)
    private Long id;

    @Enumerated
    private OperationType operationType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BinaryParams binaryParams;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BlurParams blurParams;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private HistogramEqualizeParams histogramEqualizeParams;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CannyParams cannyParams;

    public CannyParams getCannyParams() {
        return cannyParams;
    }

    public void setCannyParams(CannyParams cannyParams) {
        this.cannyParams = cannyParams;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BinaryParams getBinaryParams() {
        return binaryParams;
    }

    public void setBinaryParams(BinaryParams binaryParams) {
        this.binaryParams = binaryParams;
    }

    public BlurParams getBlurParams() {
        return blurParams;
    }

    public void setBlurParams(BlurParams blurParams) {
        this.blurParams = blurParams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistogramEqualizeParams getHistogramEqualizeParams() {
        return histogramEqualizeParams;
    }

    public void setHistogramEqualizeParams(HistogramEqualizeParams histogramEqualizeParams) {
        this.histogramEqualizeParams = histogramEqualizeParams;
    }

}
