package com.sadvit.ws;

import org.springframework.web.socket.AbstractWebSocketMessage;

/**
 * Created by vitaly.sadovskiy.
 */
public class WebSocketTransfer {

	private String id;

	// TODO model with some data

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
