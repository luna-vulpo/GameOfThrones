package pl.gameofthrones.gameserver;

import pl.gameofthrones.gameboard.Board;
import pl.gameofthrones.util.Log;

public final class GameManager implements Runnable {

	private static final String TAG = GameManager.class.getSimpleName();

	private Board mBoard = null;
	
	/**
	 * not lazy thread safe singleton
	 */
	private static GameManager mGameManager = new GameManager();
	
	private boolean mRan = true;

	private GameManager() {
	}

	public static GameManager getGameManager() {
		return mGameManager;
	}

	
	
	public void setBoard(Board board) {
		mBoard = board;
		
	}

	@Override
	public void run() {

		////check necessary conditions before start game
		
		if(mBoard == null){
			Log.e(TAG, "the board cannot be a null!");
			return;
		}
		
		//// run the game!
		while (mRan) {
			mBoard.sentStateToAllPlayers();
		}
	}

	public void stop() {
		mRan = false;
	}


	
}
