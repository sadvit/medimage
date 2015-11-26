package com.sadvit.ws;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.AbstractWebSocketMessage;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vitaly.sadovskiy.
 */
public class WebSocketGateway extends AbstractWebSocketHandler {

	private Map<String, AbstractWebSocketMessage> context = new HashMap<String, AbstractWebSocketMessage>();

	private static final Log logger = LogFactory.getLog(WebSocketGateway.class);

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(message.getPayload());
		logger.info(root.get("name"));
		logger.info(root.get("id"));
		//WebSocketTransfer transfer = TransferConverter.toTransfer(message);
		//String id = transfer.getId();
		// TODO обрабатываем модель из Transfer, и отвечаем результатом, установив id запроса.
		//logger.info("GET TEXT MESSAGE: " + message.getPayload());
		session.sendMessage(new TextMessage(root.toString()));
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		// TODO в теории, можно было бы узнать кто шлет картинку... да и сохранять её
		logger.info("GET BINARY MESSAGE: " + message.getPayload());
	}

}
