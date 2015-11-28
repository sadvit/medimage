package com.sadvit.operations.binary;

import com.sadvit.models.Entity;

/**
 * Created by sadvit on 28.11.15.
 */
public class BinaryOperationModel extends Entity {

    private BinaryType binaryType;

    public BinaryType getBinaryType() {
        return binaryType;
    }

    public void setBinaryType(BinaryType binaryType) {
        this.binaryType = binaryType;
    }

}
