package com.sadvit.ws;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadvit.services.ImageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * Created by vitaly.sadovskiy.
 */
public class GatewayHandler extends AbstractWebSocketHandler {

	private static final Log logger = LogFactory.getLog(GatewayHandler.class);

	@Autowired
	private ImageService imageService;

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(message.getPayload());
		logger.info("rerere: " + imageService);
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
