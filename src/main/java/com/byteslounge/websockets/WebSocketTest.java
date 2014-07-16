package com.byteslounge.websockets;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket"/*, encoders = { MessageEncoder.class }, decoders = { MessageDecoder.class }*/)
public class WebSocketTest {

	@OnMessage
	public void onMessage(String message, Session session) throws IOException/*,
			EncodeException*/ {

		/*// Echo the received message back to the client
		Message response = new Message();
		response.setSubject("Response to " + message.getSubject());
		response.setContent("echo " + message.getContent());*/

        /*for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {s.getBasicRemote().sendObject(response);}
        }*/

        session.getBasicRemote().sendText ("[Server] Hello men");

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {
                s.getBasicRemote().sendText(message);
            }

        }
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
