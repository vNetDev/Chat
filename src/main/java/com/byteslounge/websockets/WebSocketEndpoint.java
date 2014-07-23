package com.byteslounge.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket", encoders = { MessageEncoder.class },
                                      decoders = { MessageDecoder.class })
public class WebSocketEndpoint {

    private static Set<Session> connections = java.util.Collections.synchronizedSet(new HashSet<Session>());
    private static Map<Session, String> nickNames = new ConcurrentHashMap<>();
    private Session currentSession;

	@OnMessage
	public void onMessage(Message message, Session session) throws IOException, EncodeException {

        connections.add(session);
        this.currentSession = session;

        if (!nickNames.containsKey(currentSession)) {

            nickNames.put(currentSession, message.getNickname());

        }

		Message response = new Message();
		response.setNickname(message.getNickname());
		response.setText(message.getText());
        response.setTime(message.getTime());

        if (nickNames.get(currentSession).equals(message.getNickname())){
          response.setSent(false);
        } else response.setSent(true);

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {s.getBasicRemote().sendObject(response);}
        }
    }

	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose(Session session) {
        connections.remove(session);
        System.out.println("Connection closed");
	}

}
