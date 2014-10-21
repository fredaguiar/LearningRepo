package org.sticker.websocket;

import java.io.IOException;
import java.io.Reader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class StickerDecoder implements Decoder.TextStream<Sticker>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sticker decode(Reader reader) throws DecodeException, IOException {

		Sticker sticker = new Sticker();
		JsonReader stickerReader = Json.createReader(reader);
		JsonObject object = stickerReader.readObject();
		sticker.setX(object.getInt("x"));
		sticker.setY(object.getInt("y"));
		sticker.setImage(object.getString("image"));
		
		return sticker;
	}

}
