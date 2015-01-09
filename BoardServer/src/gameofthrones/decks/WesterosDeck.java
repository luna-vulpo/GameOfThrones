package decks;

public final class WesterosDeck extends Deck<WesterosCard> {

	public static final int WESTEROS_I = 1;
	public static final int WESTEROS_II = 2;
	public static final int WESTEROS_III = 3;

	int type;

	public WesterosDeck(int type) {
		super();
		this.type = type;
	}
	

}
