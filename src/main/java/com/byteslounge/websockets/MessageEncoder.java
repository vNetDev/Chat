package com.byteslounge.websockets;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public String encode(Message message) throws EncodeException {

		JsonObject jsonObject = Json.createObjectBuilder()
				.add("nickname", message.getNickname())
				.add("text", message.getText()).build();
		return jsonObject.toString();

	}

	@Override
	public void init(EndpointConfig ec) {
		System.out.println("MessageEncoder - init method called");
	}

	@Override
	public void destroy() {
		System.out.println("MessageEncoder - destroy method called");
	}

}
