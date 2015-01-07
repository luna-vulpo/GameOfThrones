package pl.gameofthrones.gameserver;

public class Main {

	public static void main(String[] args) {
		
		new Thread(new BoardServer()).start();

	}

}
