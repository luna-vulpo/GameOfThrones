package pl.gameofthrones.gameboard.fields;

/**
 * 
 * pl: port morski
 * 
 * Short rules explanation (from http://boardgamegeek.com/thread/748613/ports
 * -explained-four-simple-rules)
 * 
 * A Port is considered a sea area (bordering a Castle/Stronghold and a sea area
 * proper) except for the following:
 * 
 * 1 No more than three Ships allowed in a Port.
 * 
 * 2 A Port belongs to the player owning the connected Castle/Stronghold and can
 * never be attacked directly: Ships can never march into a non-friendly Port.
 * When the Castle/Stronghold is conquered, the new owner can either destroy or
 * take over the Ships in the Port (substituting one or more Ship units in the
 * Port with units of the conqueror's colour).
 * 
 * 3 Ships in a Port may not raid nor support land areas.
 * 
 * 4 When there are friendly Ships in the Port and no enemy Ships in the
 * connected sea area, the Port provides one Power Token when a
 * "Consolidate Power" order is assigned to it, and when the "Game of Thrones"
 * Westeros card is resolved, but not when the "Dark Wings, Dark Words" Westeros
 * card is resolved. * 
 * 
 * 
 * @author arek
 * 
 *
 */
public final class Port extends Sea {

	Port(int id, String name) {
		super(id, name);
	}

}
