package pl.gameofthrones.gameboard;

import java.util.LinkedList;
import java.util.Queue;

import pl.gameofthrones.gameboard.fields.Castle;
import pl.gameofthrones.gameboard.fields.Field;
import pl.gameofthrones.gameboard.fields.OpenSea;
import pl.gameofthrones.gameboard.fields.Stronghold;
import pl.gameofthrones.gameboard.fields.Terrain;
import pl.gameofthrones.gameserver.Player;

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

	final Field[] fields = new Field[50];

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
		fields[2] = new Castle(2, "Karnhold");
		fields[3] = new Stronghold(3, "Winterfell");		
		fields[4] = new Terrain(4,"The Srony Shore");
		fields[4] = new Terrain(4,"White Harbor");
		fields[6] = new OpenSea(6, "The Shivearing Sea");
		fields[7] = new Terrain(7,"Window's Watch");
		fields[8] = new Terrain(8,"The Narrow sea");
		fields[9] = new Terrain(9,"Greywater Watch");
		fields[10] = new OpenSea(10,"Sunset Sea");
		fields[11] = new Terrain(11,"Flint's Finger");
		fields[12] = new Terrain(12,"Moat Calin");
		fields[13] = new Terrain(13,"The Fingers");
		fields[14] = new Terrain(14,"Ironman's Bay");
		fields[15] = new Terrain(15,"Seagard");
		fields[16] = new Terrain(16,"The Twins");
		fields[17] = new Terrain(17,"Pyke");
		fields[18] = new Terrain(18,"The Mountains of the Moon");
		fields[19] = new Terrain(19,"Riverrun");
		fields[20] = new Terrain(20,"The Eyrie");
		fields[21] = new Terrain(21,"Lannisport");
		fields[22] = new Terrain(22,"Harrenal");
		fields[23] = new Terrain(23,"Dragonstone");
		fields[24] = new Terrain(24,"Stoney Sept");
		fields[25] = new Terrain(25,"Crackcaw Point");
		fields[26] = new Terrain(26,"The Golden Sound");
		fields[27] = new OpenSea(27,"Blackwater Bay");
		fields[28] = new Terrain(28,"Searoad Marches");
		fields[29] = new Terrain(29,"Blackwater");
		fields[30] = new Terrain(30,"Kings's Landing");
		fields[31] = new Terrain(31,"Kingswood");
		fields[32] = new Terrain(32,"Shipsreaker Bay");
		fields[33] = new Terrain(33,"Higgarden");
		fields[34] = new Terrain(34,"The Reach");
		fields[35] = new Terrain(35,"Dornish Marches");
		fields[36] = new Terrain(36,"Storms End");
		fields[37] = new Terrain(37,"The Boneway");
		fields[38] = new Terrain(38,"Oldtown");
		fields[39] = new Terrain(39,"Prince's Pass");
		fields[40] = new Terrain(40,"Sea of Dorne");
		fields[41] = new Terrain(41,"Three Towers");
		fields[42] = new Terrain(42,"Yronwood");
		fields[43] = new Terrain(43,"Sunspear");
		fields[44] = new Terrain(44,"Redwyne Straights");
		fields[45] = new Terrain(45,"Salt Shore");
		fields[46] = new Terrain(46,"Starfall");
		fields[47] = new Terrain(47,"The arbor");
		fields[48] = new OpenSea(48,"West Summer Sea");
		fields[49] = new Terrain(49,"East Summer Sea");
		
		fields[0].addNeighbor(fields[1]);
		fields[0].addNeighbor(fields[3]);
		fields[0].addNeighbor(fields[4]);
		fields[0].addNeighbor(fields[9]);
		fields[0].addNeighbor(fields[10]);
		fields[0].addNeighbor(fields[11]);
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

	public Player getNextPlayerInTour() {
		// TODO Auto-generated method stub
		return null;
	}
}
