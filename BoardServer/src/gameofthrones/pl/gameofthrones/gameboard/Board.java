package pl.gameofthrones.gameboard;

import java.util.List;

import pl.gameofthrones.gameboard.decks.WesterosCard;
import pl.gameofthrones.gameboard.decks.WesterosDeck;
import pl.gameofthrones.gameboard.decks.WildlingsDeck;
import pl.gameofthrones.gameboard.fields.Castle;
import pl.gameofthrones.gameboard.fields.Field;
import pl.gameofthrones.gameboard.fields.OpenSea;
import pl.gameofthrones.gameboard.fields.Port;
import pl.gameofthrones.gameboard.fields.Stronghold;
import pl.gameofthrones.gameboard.fields.Terrain;
import pl.gameofthrones.gameboard.tokens.Token;


/**
 * 
 * pl: plansza
 * 
 * @author arek
 * 
 */
public final class Board {

	public final static int PLAYER_HOUSE_STARK = 0;
	public final static int PLAYER_HOUSE_GREYJOY = 1;
	public final static int PLAYER_HOUSE_LANNISTER = 2;
	public final static int PLAYER_HOUSE_MARTELL= 3;
	public final static int PLAYER_HOUSE_TYRELL = 4;
	public final static int PLAYER_HOUSE_BARATHEON = 5;
	
	public final static int PHASE_RIDE = 0;
	public final static int PHASE_MARCH = 1;
	public final static int PHASE_CONSOLIDATE_POWER = 2;
	

	public final static int MAX_PLAYER = PLAYER_HOUSE_BARATHEON + 1;
	private Player[] players;

	final Field[] fields = new Field[58];

	WildlingsDeck wildlingsDeck = new WildlingsDeck();
	WesterosDeck westerosDeckI = new WesterosDeck(WesterosDeck.DECK_I);
	WesterosDeck westerosDeckII = new WesterosDeck(WesterosDeck.DECK_II);
	WesterosDeck westerosDeckIII = new WesterosDeck(WesterosDeck.DECK_III);

	int roundTrack = 1;

	private Board(Player[] players) {
		this.players = players;
		setupFields();
		wildlingsDeck.shuffle();
		westerosDeckI.shuffle();
		westerosDeckII.shuffle();
		westerosDeckIII.shuffle();
		spreadNeutralArmy(players.length);
	}

	private void setupFields() {
		
		fields[0] = new OpenSea(0, "Bay of Ice");
		fields[1] = new Castle(1, "Castle Black");
		fields[2] = new Terrain(2, "Karhold");
		fields[3] = new Stronghold(3, "Winterfell");		
		fields[4] = new Terrain(4,"The Stony Shore");
		fields[4] = new Castle(4,"White Harbor");
		fields[6] = new OpenSea(6, "The Shivearing Sea");
		fields[7] = new Terrain(7,"Window's Watch");
		fields[8] = new OpenSea(8,"The Narrow sea");
		fields[9] = new Terrain(9,"Greywater Watch");
		fields[10] = new OpenSea(10,"Sunset Sea");
		fields[11] = new Castle(11,"Flint's Finger");
		fields[12] = new Castle(12,"Moat Calin");
		fields[13] = new Terrain(13,"The Fingers");
		fields[14] = new OpenSea(14,"Ironman's Bay");
		fields[15] = new Stronghold(15,"Seagard");
		fields[16] = new Terrain(16,"The Twins");
		fields[17] = new Stronghold(17,"Pyke");
		fields[18] = new Terrain(18,"The Mountains of the Moon");
		fields[19] = new Stronghold(19,"Riverrun");
		fields[20] = new Castle(20,"The Eyrie");
		fields[21] = new Stronghold(21,"Lannisport");
		fields[22] = new Castle(22,"Harrenhal");
		fields[23] = new Stronghold(23,"Dragonstone");
		fields[24] = new Terrain(24,"Stoney Sept");
		fields[25] = new Castle(25,"Crackclaw Point");
		fields[26] = new OpenSea(26,"The Golden Sound");
		fields[27] = new OpenSea(27,"Blackwater Bay");
		fields[28] = new Terrain(28,"Searoad Marches");
		fields[29] = new Terrain(29,"Blackwater");
		fields[30] = new Stronghold(30,"Kings's Landing");
		fields[31] = new Terrain(31,"Kingswood");
		fields[32] = new OpenSea(32,"Shipsbreaker Bay");
		fields[33] = new Stronghold(33,"Highgarden");
		fields[34] = new Castle(34,"The Reach");
		fields[35] = new Terrain(35,"Dornish Marches");
		fields[36] = new Castle(36,"Storms End");
		fields[37] = new Terrain(37,"The Boneway");
		fields[38] = new Stronghold(38,"Oldtown");
		fields[39] = new Terrain(39,"Prince's Pass");
		fields[40] = new OpenSea(40,"Sea of Dorne");
		fields[41] = new Terrain(41,"Three Towers");
		fields[42] = new Castle(42,"Yronwood");
		fields[43] = new Stronghold(43,"Sunspear");
		fields[44] = new OpenSea(44,"Redwyne Straights");
		fields[45] = new Terrain(45,"Salt Shore");
		fields[46] = new Castle(46,"Starfall");
		fields[47] = new Terrain(47,"The Arbor");
		fields[48] = new OpenSea(48,"West Summer Sea");
		fields[49] = new OpenSea(49,"East Summer Sea");
		fields[50] = new Port(50, "Winterfell Port");
		fields[51] = new Port(51, "White Harbor Port");
		fields[52] = new Port(52, "Pyke Port");
		fields[53] = new Port(53, "Lannisport Port");
		fields[54] = new Port(54, "Dragonstone Port");
		fields[55] = new Port(55, "Storms End Port");
		fields[56] = new Port(56, "Oldtown Port");
		fields[57] = new Port(57, "Sunspear Port");
		
		fields[0].addNeighbor(fields[1]);
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
		for(Player p : players){
			if(p != null)
				p.sentBoardState(this);
		}
		
	}

	public void moveTurnMarker() {
		// TODO Auto-generated method stub
		
	}
	
	public WesterosCard getWesterosCardFromDeckI() {
		
		return westerosDeckI.peek();
	}


	public WesterosCard getWesterosCardFromDeckII() {
		
		return westerosDeckII.peek();
	}
	
	public WesterosCard getWesterosCardFromDeckIII() {
		
		return westerosDeckIII.peek();
	}

	public void moveWildlingesTokens() {
		// TODO Auto-generated method stub
		
	}

	public Player getNextPlayerInPlaningPhase() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTokens(List<Token> tokens) {
		// TODO Auto-generated method stub
		
	}

	public void dispatchInfoAboutTokens() {
		
		
	}

	public Player getNextPlayerInActionPhase(int phase) {
		
		return null;
	}

	public List<Token> getTokenListFor(int phaseRide, Player player) {
		// TODO Auto-generated method stub
		return null;
	}
}
