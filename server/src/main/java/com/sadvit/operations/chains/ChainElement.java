package com.sadvit.operations.chains;

import com.sadvit.models.Entity;
import com.sadvit.operations.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;

/**
 * Created by vitaly.sadovskiy.
 */
public class ChainElement {

	private OperationType operation;

	private BinaryParams binaryParams;

	public OperationType getOperation()	{
		return operation;
	}

	public void setOperation(OperationType operation) {
		this.operation = operation;
	}

	public BinaryParams getBinaryParams() {
		return binaryParams;
	}

	public void setBinaryParams(BinaryParams binaryParams) {
		this.binaryParams = binaryParams;
	}

	// TODO crop, resize...

}
