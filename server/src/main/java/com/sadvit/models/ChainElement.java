package com.sadvit.models;

import com.sadvit.enums.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.blur.BlurParams;

import javax.persistence.*;

/**
 * Created by vitaly.sadovskiy.
 */
@Entity
@Table(name = "CHAIN_ELEMENTS")
public class ChainElement {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated
	private OperationType operationType;

    @OneToOne(fetch = FetchType.EAGER)
	private BinaryParams binaryParams;

    @OneToOne(fetch = FetchType.EAGER)
	private BlurParams blurParams;

	public OperationType getOperationType()	{
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
