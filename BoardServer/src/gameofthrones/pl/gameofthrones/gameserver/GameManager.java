package pl.gameofthrones.gameserver;

import java.util.List;

import pl.gameofthrones.gameboard.Board;
import pl.gameofthrones.gameboard.Player;
import pl.gameofthrones.gameboard.WesterosCard;
import pl.gameofthrones.gameboard.tokens.Token;
import pl.gameofthrones.util.Log;

public final class GameManager implements Runnable {

	private static final String TAG = GameManager.class.getSimpleName();

	private Board mBoard = null;
	
	/**
	 * not lazy thread safe singleton
	 */
	private static GameManager mGameManager = new GameManager();
	
	private boolean mRan = true;
	
	boolean mIsFirstTurn = true;

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
		mBoard.sentStateToAllPlayers();
		
		//// run the game!
		while (mRan) {

			// //==============
			// // westeros phase
			if (!mIsFirstTurn) {
				mIsFirstTurn = false;
				mBoard.moveTurnMarker();

				WesterosCard w1 = mBoard.getWesterosCard1();
				WesterosCard w2 = mBoard.getWesterosCard2();
				WesterosCard w3 = mBoard.getWesterosCard3();

				mBoard.moveWildlingesTokens();

				w1.preformAction();
				w2.preformAction();
				w3.preformAction();
			}
			
			// //==============
			// // planing phase
			Player player = null;
			do {
				player = mBoard.getNextPlayerInPlaningPhase();
				List<Token> tokens	= player.getTokenList();
				mBoard.setTokens(tokens);
			} while (player != null);

			mBoard.dispatchInfoAboutTokens();
			
			//??? Messenger Raven ???
			
			
			// //==============
			// // action phase
			
			// resolve ride orders
			do {
				player = mBoard.getNextPlayerInActionPhase("ride");
				List<Token> tokens = mBoard.getRideTokenListFor(player);
				RideToken rideToken = player.pickUpRideToken();
				mBoard.setTokens(tokens);
			} while (player != null);
			
			
			// resolve march orders
			do {
				player = mBoard.getNextPlayerInActionPhase("march");
				List<Token> tokens = mBoard.getRideTokenListFor(player);
				RideToken rideToken = player.pickUpRideToken();
				mBoard.setTokens(tokens);
			} while (player != null);
			
			// Resolve Consolidate Power Orders
			do {
				player = mBoard.getNextPlayerInActionPhase("ConsolidatePower");
				List<Token> tokens = mBoard.getRideTokenListFor(player);
				RideToken rideToken = player.pickUpRideToken();
				mBoard.setTokens(tokens);
			} while (player != null);
					 
			
			
			// clean up
			
		}
	}

	public void stop() {
		mRan = false;
	}


	
}
