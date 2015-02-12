package pl.gameofthrones.gameserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerMain {

	public static final Gson GSON = new GsonBuilder()
											.excludeFieldsWithoutExposeAnnotation()
											//.setPrettyPrinting()
											.create();

	public static void main(String[] args) {

		new Thread(new BoardServer()).start();

	}

}
