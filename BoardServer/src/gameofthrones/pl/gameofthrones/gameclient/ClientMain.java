package pl.gameofthrones.gameclient;

public class ClientMain {

	public static void main(String[] args) {

		new Thread(new GameClient()).start();

	}

}
