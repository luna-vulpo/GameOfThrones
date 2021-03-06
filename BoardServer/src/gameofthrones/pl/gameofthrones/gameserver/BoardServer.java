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
		Log.v(TAG, "init");
		mServerSocket = null;
		
		GameManager gameManager = GameManager.getGameManager();
		
		Board.Builder boardBuilder = new Board.Builder();
		boardBuilder.build();
		try {
			mServerSocket = new ServerSocket(PORT);

			while (mRan) {
				Socket clientSocket = mServerSocket.accept();
				PlayerConnectionTask gamer = new PlayerConnectionTask(clientSocket);
				
				boardBuilder.attachPlayer(gamer.getPlayer());
				
				new Thread(gamer).start();
				
				if(boardBuilder.isFull()){
					gameManager.setBoard(boardBuilder.build());
					new Thread(gameManager).start();
					stop();
				}
				
			}
			Log.d(TAG, "while loop finished. Server stoped");

		} catch (IOException e) {
			Log.e(TAG, "server",e);
			
		} catch (TooManyPlayersException e) {
			Log.e(TAG, "too many players",e);
			// TODO sent info to client that he is not accepted
			// and end connection according to the protocol
//			if (gamer != null)
//				gamer.setConnectionRefuse();

		} finally {
			if (mServerSocket != null)
				try {
					mServerSocket.close();
				} catch (IOException e) {
					Log.e(TAG, "server",e);
				}
		}
	}
	
	void stop(){
		mRan = false;
	}
}
