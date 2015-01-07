package pl.gameofthrones.gameboard;

import java.util.LinkedList;
import java.util.Queue;

import pl.gameofthrones.gameboard.fields.Castle;
import pl.gameofthrones.gameboard.fields.Field;
import pl.gameofthrones.gameboard.fields.OpenSea;
import pl.gameofthrones.gameboard.fields.Stronghold;

/**
 * 
 * pl: plansza
 * 
 * @author arek
 * 
 */
public final class Board {

	public final static int PLAYER_name0 = 0;
	public final static int PLAYER_name1 = 1;
	public final static int PLAYER_name2 = 2;
	public final static int PLAYER_name3 = 3;
	public final static int PLAYER_name4 = 4;
	public final static int PLAYER_name5 = 5;

	public final static int MAX_PLAYER = PLAYER_name5 + 1;
	private Player[] mPlayers;

	final Field[] fields = new Field[40];

	final Queue<WildlingsCard> mWildingsCardStack = new LinkedList<WildlingsCard>();
	final Queue<WesterosCard> mWesterosCardStack = new LinkedList<WesterosCard>();

	int tourIndicator = 1;

	private Board(Player[] players) {
		this.mPlayers = players;
		setupFields();
		schuffleWildlingsCards();
		schuffleWesterosCards();
		spreadNeutralArmy(players.length);
	}

	private void setupFields() {
		fields[0] = new OpenSea(0, "Bay of Ice");
		fields[1] = new Castle(1, "Castle Black");
		fields[2] = new OpenSea(2, "The Shivearing Sea");
		fields[3] = new Stronghold(3, "Winterfell");
		fields[4] = new Castle(4, "Karnhold");
		// fields[2] = new OpenSea(2,"The Shivearing Sea");
		// fields[2] = new OpenSea(2,"The Shivearing Sea");
		// fields[2] = new OpenSea(2,"The Shivearing Sea");

		fields[0].addNeighbor(fields[1]);
	}

	private void schuffleWildlingsCards() {

	}

	private void schuffleWesterosCards() {

	}

	private void spreadNeutralArmy(int numberOfPlayers) {

	}

	/**
	 * configure and setup Board object
	 * 
	 * @author arek
	 * 
	 */
	public static class Builder {

		private Player[] mPlayers = new Player[6];
		private int mAttachedPlayerCounter = 0;

		/**
		 * Attached new player to the game board
		 * 
		 * @param player
		 * @return
		 * @throws TooManyPlayersException
		 */
		public synchronized void attachePlayer(Player player)
				throws TooManyPlayersException {

			for (int i = 0; i < mPlayers.length; i++) {
				if (mPlayers[i] != null) {
					mPlayers[i] = player;
					player.setHouse(i);
					mAttachedPlayerCounter++;
				}
			}
			// it should never happen
			throw new TooManyPlayersException();
		}
		/**
		 * Try to attache new player to the game board with selected house by
		 * the player. If house is taken is assigns first free house.
		 * 
		 * @param player
		 * @param houseId
		 * @return
		 * @throws TooManyPlayersException
		 */
		public synchronized void attachePlayer(Player player, int houseId)
				throws TooManyPlayersException {
			if (mPlayers[houseId] == null) {
				mPlayers[houseId] = player;
				player.setHouse(houseId);
				mAttachedPlayerCounter++;
			} else
				attachePlayer(player);

		}

		/**
		 * 
		 * @return
		 */
		public boolean isFull() {

			return mAttachedPlayerCounter >= MAX_PLAYER;
		}

		public Board build() {
			return new Board(mPlayers);
		}

		public class TooManyPlayersException extends Exception {
			private static final long serialVersionUID = 1L;
		}

	}

	public void sentStateToAllPlayers() {
		for(Player p : mPlayers){
			if(p != null)
				p.sentBoardState(this);
		}
		
	}
}
