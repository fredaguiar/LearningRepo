package org.sticker.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/story/nofitications",  
	encoders = StickerEncoder.class,
	decoders = StickerDecoder.class)
public class StoryWebSocket {
	
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		sessions.add(session);
	}
	
	@OnMessage
	public void onMessage(Sticker sticker) {
		
		for (Session open : sessions) {
			try {
				open.getBasicRemote().sendObject(sticker);
			} catch (IOException | EncodeException e) {
				Logger.getLogger(StoryWebSocket.class.getName()).log(Level.SEVERE, "Error on messaging / websockets");
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
	}

}
