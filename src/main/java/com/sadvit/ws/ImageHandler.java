package com.sadvit.ws;

import com.sadvit.services.ImageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

/**
 * Created by vitaly.sadovskiy.
 */
public class ImageHandler extends AbstractWebSocketHandler {

	private static final Log logger = LogFactory.getLog(GatewayHandler.class);

	@Autowired
	private ImageService imageService;

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		// TODO save image
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String name = session.getPrincipal().getName();
		String id = message.getPayload();
		byte[] array = imageService.getImageAsByteArray(id, name);
		session.sendMessage(new BinaryMessage(array));
		// TODO return image
	}
}
