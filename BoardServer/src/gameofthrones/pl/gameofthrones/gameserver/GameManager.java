package pl.gameofthrones.gameserver;

import java.util.List;

import pl.gameofthrones.gameboard.Board;
import pl.gameofthrones.gameboard.Player;
import pl.gameofthrones.gameboard.decks.WesterosCard;
import pl.gameofthrones.gameboard.tokens.ConsolidatePowerOrder;
import pl.gameofthrones.gameboard.tokens.MarchOrder;
import pl.gameofthrones.gameboard.tokens.OrderToken;
import pl.gameofthrones.gameboard.tokens.RaidOrder;
import pl.gameofthrones.util.Log;

public final class GameManager implements Runnable {

	private static final String TAG = GameManager.class.getSimpleName();

	private Board board = null;
	
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
		this.board = board;
	}

	@Override
	public void run() {

		////check necessary conditions before start game
		
		if(board == null){
			Log.e(TAG, "the board cannot be a null!");
			return;
		}
		board.sentStateToAllPlayers();
		
		//// run the game!
		while (mRan) {

			// //==============
			// // westeros phase
			if (!mIsFirstTurn) {
				mIsFirstTurn = false;
				board.moveTurnMarker();

				WesterosCard w1 = board.getWesterosCardFromDeckI();
				WesterosCard w2 = board.getWesterosCardFromDeckII();
				WesterosCard w3 = board.getWesterosCardFromDeckIII();

				board.moveWildlingesTokens();

				w1.preformAction();
				w2.preformAction();
				w3.preformAction();
			}
			
			// //==============
			// // planing phase
			Player player = null;
			do {
				player = board.getNextPlayerInPlaningPhase();

				List<RaidOrder> orderTokens = player.getOrderTokenList();
				board.setOrderTokens(orderTokens);

				board.dispatchInfoAboutTokens();
			
			} while (player != null);

			
			
			//??? Messenger Raven ???
			
			
			// //==============
			// // action phase
			
			// resolve ride orders
			do {
				player = board.getNextPlayerInActionPhase(Board.PHASE_RAID);
				List<OrderToken> orderTokens = board.getOrderTokenListFor(Board.PHASE_RAID, player);
				RaidOrder raidOrder = player.getSelectedRaidOrder();
				
				//???????????
				//???????????
				
				//board.setOrderTokens(orderTokens);
				
			} while (player != null);
			
			
			// resolve march orders
			do {
				player = board.getNextPlayerInActionPhase(Board.PHASE_MARCH);
				List<OrderToken> orderTokens = board.getOrderTokenListFor(
						Board.PHASE_MARCH, player);
				MarchOrder marchOrder = player.getSelectedMarchOrder();
				//board.setOrderTokens(orderTokens);
			} while (player != null);
			
			// Resolve Consolidate Power Orders
			do {
				player = board.getNextPlayerInActionPhase(Board.PHASE_CONSOLIDATE_POWER);
				List<OrderToken> orderTokens = board.getOrderTokenListFor(Board.PHASE_CONSOLIDATE_POWER, player);
				ConsolidatePowerOrder consolidatePowerOrder = player.getSelectedConsolidatePowerOrder();
				//board.setOrderTokens(orderTokens);
			} while (player != null);
					 
			
			
			// clean up
			
		}
	}

	public void stop() {
		mRan = false;
	}


	
}
