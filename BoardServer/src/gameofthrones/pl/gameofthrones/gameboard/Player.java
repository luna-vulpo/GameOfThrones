package pl.gameofthrones.gameboard;

import java.util.List;

import pl.gameofthrones.gameboard.tokens.Token;
import pl.gameofthrones.gameboard.tokens.RaidOrder;
import pl.gameofthrones.gameboard.tokens.MarchOrder;
import pl.gameofthrones.gameboard.tokens.ConsolidatePowerOrder;

public interface Player {

	void setHouse(int i);

	void sentBoardState(Board board);

	List<Token> getTokenList();

	RaidOrder selectRideOrder();

	MarchOrder selectMarchOrder();

	ConsolidatePowerOrder selectConsolidatePowerOrder();
}
