package pl.gameofthrones.gameboard;

/**
 * 
 * pl: karta rodów
 * 
 * @author arek
 * 
 *         For details about each card please refer to
 *         House_Cards_Reference_Sheet.pdf (in docs folder) - complete reference
 *         of House Cards prepared by DeanAU from BoardGameGeek
 *
 */
public final class HouseCard {
	
	String name;
	String specialAbilityDesc;
	int numberOfSwordIconss;
	int numberOfFortificationIcons;
	boolean isDiscarded = false;
	
	public HouseCard(String name, String specialAbilityDesc,
			int numberOfSwordIconss, int numberOfFortificationIcons) {
		super();
		this.name = name;
		this.specialAbilityDesc = specialAbilityDesc;
		this.numberOfSwordIconss = numberOfSwordIconss;
		this.numberOfFortificationIcons = numberOfFortificationIcons;
	}
	
	public String getName() {
		return name;
	}
	public String getSpecialAbilityDesc() {
		return specialAbilityDesc;
	}
	public int getNumberOfSwordIconss() {
		return numberOfSwordIconss;
	}
	public int getNumberOfFortificationIcons() {
		return numberOfFortificationIcons;
	}
	

}

