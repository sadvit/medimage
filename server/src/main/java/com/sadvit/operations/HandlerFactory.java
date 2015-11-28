package com.sadvit.operations;

import com.sadvit.operations.binary.BinaryProcessHandler;
import com.sadvit.operations.blur.BlurProcessHandler;

/**
 * Created by vitaly.sadovskiy.
 */
public class HandlerFactory {

	public static ProcessHandler getHandler(HandlerType type) {
		switch (type) {
			case BINARY:
				return new BinaryProcessHandler();
			case BLUR:
				return new BlurProcessHandler();
			default:
				return null;
		}
	}

}
