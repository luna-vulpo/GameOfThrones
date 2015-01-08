package pl.gameofthrones.gameboard;

import java.util.List;

import pl.gameofthrones.gameboard.tokens.Token;

public interface Player {

	void setHouse(int i);

	void sentBoardState(Board board);

	List<Token> getTokenList();
}
