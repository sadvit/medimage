package com.sadvit.ws;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * Created by vitaly.sadovskiy.
 */
public class ProcessWebSocketHandler extends AbstractWebSocketHandler {

	private static final Log logger = LogFactory.getLog(ProcessWebSocketHandler.class);

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("GET TEXT MESSAGE: " + message.getPayload());
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		logger.info("GET BINARY MESSAGE: " + message.getPayload());

	}

}
