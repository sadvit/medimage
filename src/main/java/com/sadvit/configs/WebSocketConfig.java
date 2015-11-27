package com.sadvit.configs;

import com.sadvit.ws.GatewayHandler;
import com.sadvit.ws.TransferWebSocketHandler;
import com.sadvit.ws.ImageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Bean
	public GatewayHandler gatewayHandler() {
		return new GatewayHandler();
	}

	@Bean
	public ImageHandler imageHandler() {
		return new ImageHandler();
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
				.addHandler(gatewayHandler(), "/realtime/gateway")
				.addHandler(imageHandler(), "/realtime/images")
				.addInterceptors(new HttpSessionHandshakeInterceptor());
	}

}