package decks;

/**
 * 
 * @author pablo
 * 
 *         Westeros Deck I 3 x Supply (adjust supply) 3 x Mustering (muster
 *         units) 2 x Thrones of Blades image (Iron Throne holder chooses
 *         Mustering or Supply or none) 1 x Winter is Coming (reshuffle) 1 x
 *         Last Days of Summer image (nothing happens)
 * 
 *         Westeros Deck II 3 x Clash of Kings (bid power tokens on influence
 *         tracks) 3 x Game of Thrones (gain power tokens) 2 x Dark Wings, Dark
 *         Words image (Messenger Raven holder chooses COK or GOT or none) 1 x
 *         Winter is Coming (reshuffle) 1 x Last Days of Summer image (nothing
 *         happens)
 * 
 *         Westeros Deck III 3 x Wilding Attack (bid power tokens to fend off
 *         Wildling Attack) 1 x Sea of Storms image (No raid orders) 1 x Rains
 *         of Autumn image (No +1 march orders) 1 x Feast for Crows image (No
 *         consolidate power orders) 1 x Web of Lies image (No support orders) 1
 *         x Storm of Swords image (No defence orders) 2 x Put to the Sword
 *         (Valyrian Steel holder chooses ROA or StOSw or none)
 * 
 *
 */

public final class WesterosDeck extends Deck<WesterosCard> {

	public static final int DECK_I = 1;
	public static final int DECK_II = 2;
	public static final int DECK_III = 3;

	int type;

	public WesterosDeck(int type) {
		super();
		this.type = type;
	}

}
