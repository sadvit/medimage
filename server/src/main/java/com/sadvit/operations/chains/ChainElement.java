package com.sadvit.operations.chains;

import com.sadvit.operations.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.blur.BlurParams;

/**
 * Created by vitaly.sadovskiy.
 */
public class ChainElement {

	private OperationType operationType;

	private BinaryParams binaryParams;

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

}
