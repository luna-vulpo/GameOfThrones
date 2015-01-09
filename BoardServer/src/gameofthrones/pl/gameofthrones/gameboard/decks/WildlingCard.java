package pl.gameofthrones.gameboard.decks;

public final class WildlingCard {

	private String cardName;
	private String wildlingVictoryLowestBiderText;
	private String wildlingVictoryEveryoneElseText;
	private String nightsWatchVictoryText;

	public WildlingCard(String cardName, String wildlingVictoryLowestBiderText, String wildlingVictoryEveryoneElseText,
			String nightsWatchVictoryText) {
		super();
		this.cardName = cardName;
		this.wildlingVictoryLowestBiderText = wildlingVictoryLowestBiderText;
		this.wildlingVictoryEveryoneElseText = wildlingVictoryEveryoneElseText;
		this.nightsWatchVictoryText = nightsWatchVictoryText;
	}

}
