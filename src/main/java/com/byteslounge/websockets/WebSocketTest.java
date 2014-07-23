package com.byteslounge.websockets;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket", encoders = { MessageEncoder.class },
                                      decoders = { MessageDecoder.class })

public class WebSocketTest {

	@OnMessage
	public void onMessage(Message message, Session session) throws IOException, EncodeException {

		Message response = new Message();
		response.setNickname(message.getNickname());
		response.setText(message.getText());
        response.setTime(message.getTime());

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {s.getBasicRemote().sendObject(response);}
        }

//        session.getBasicRemote().sendText ("[Server] Hello men");
//
//        for (Session s : session.getOpenSessions()) {
//            if (s.isOpen()) {
//                s.getBasicRemote().sendText(message);
//            }
//
//        }
    }

	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}

}
