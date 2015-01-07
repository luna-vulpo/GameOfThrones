package pl.gameofthrones.gameserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import pl.gameofthrones.gameboard.Board;
import pl.gameofthrones.gameboard.Board.Builder.TooManyPlayersException;
import pl.gameofthrones.util.Log;



public class BoardServer implements Runnable {

	private static final String TAG = BoardServer.class.getSimpleName();
	//TODO consider rewrite to state design pattern.
	//below draft:
	interface BoardGameState {
		
	}
	//when server wait for players
	class BoardGameListen implements BoardGameState {}
	//when game started and 
	class BoardGemeStarted implements BoardGameState {}
	
	
	
	public static final int PORT = 6666;
	private boolean mRan = true;

	private ServerSocket mServerSocket = null;

	@Override
	public void run() {
		System.out.println("init");
		mServerSocket = null;
		
		GameManager gameManager = GameManager.getGameManager();
		
		Board.Builder boardBuilder = new Board.Builder();
		
		try {
			mServerSocket = new ServerSocket(PORT);

			while (mRan) {

				Socket clientSocket = mServerSocket.accept();				
				PlayerTask gamer = new PlayerTask(clientSocket);
				
				boardBuilder.attachePlayer(gamer);
				
				new Thread(gamer).start();
				
				if(boardBuilder.isFull()){
					gameManager.setBoard(boardBuilder.build());
					new Thread(gameManager).start();
					stop();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TooManyPlayersException e) {
			Log.e(TAG, "too many players");
			// TODO sent info to client that he is not accepted
			// and end connection according to the protocol
//			if (gamer != null)
//				gamer.setConnectionRefuse();

		} finally {
			if (mServerSocket != null)
				try {
					mServerSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	void stop(){
		mRan = false;
	}
}
