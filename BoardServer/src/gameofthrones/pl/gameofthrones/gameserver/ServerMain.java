package pl.gameofthrones.gameserver;

import pl.gameofthrones.gameboard.fields.Field;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerMain {

	public static final Gson GSON = new GsonBuilder()
											.excludeFieldsWithoutExposeAnnotation()
											.registerTypeAdapter(Field.class, new Field.GsonAdapter())
											.setPrettyPrinting()
											.create();

	public static void main(String[] args) {

		new Thread(new BoardServer()).start();

	}

}
