package com.sadvit.ws;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadvit.ws.transfer.ImageTransfer;
import com.sadvit.ws.transfer.TextTransfer;
import com.sadvit.ws.transfer.Transfer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

/**
 * Created by vitaly.sadovskiy.
 */
public abstract class TransferWebSocketHandler extends AbstractWebSocketHandler {

	private static final Log logger = LogFactory.getLog(TransferWebSocketHandler.class);

    private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userName = session.getPrincipal().getName();

        Transfer response;
        try {
            TextTransfer transfer = toRequestTransfer(message);
            transfer.setUser(userName);
            response = handleTextTransfer(transfer);
        } catch (Exception e) {
            ImageTransfer transfer = toImageTransfer(message);
            transfer.setUser(userName);
            response = handleImageTransfer(transfer);
        }
        session.sendMessage(new TextMessage(response.toJSON()));

    }

    protected TextTransfer toRequestTransfer(TextMessage message) throws IOException {
        return mapper.readValue(message.getPayload(), TextTransfer.class);
    }

    protected ImageTransfer toImageTransfer(TextMessage message) {
        return null; // TODO impl
    }

    protected TextMessage toMessage(String transfer) {
        return new TextMessage(transfer);
    }

    protected BinaryMessage toMessage(byte[] image) {
        return new BinaryMessage(image);
    }

    protected abstract Transfer handleTextTransfer(TextTransfer transfer);

    protected abstract Transfer handleImageTransfer(ImageTransfer transfer);

}
