package org.sticker.websocket;

import java.io.IOException;
import java.io.Writer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class StickerEncoder implements Encoder.TextStream<Sticker>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(Sticker sticker, Writer writer) throws EncodeException,
			IOException {
		JsonObject jsonSticker = Json.createObjectBuilder()
				.add("action", "add")
				.add("x", sticker.getX())
				.add("y", sticker.getY())
				.add("image", sticker.getImage()).build();
		
		JsonWriter jwriter = Json.createWriter(writer);
		jwriter.writeObject(jsonSticker);
	}

}
