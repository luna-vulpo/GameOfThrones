package pl.gameofthrones.gameboard;

import java.util.List;

import pl.gameofthrones.gameboard.tokens.ConsolidatePowerOrder;
import pl.gameofthrones.gameboard.tokens.MarchOrder;
import pl.gameofthrones.gameboard.tokens.OrderToken;
import pl.gameofthrones.gameboard.tokens.RaidOrder;
import pl.gameofthrones.gameserver.PlayerConnectionTask;

public class Player {

	int houseId = -1;

	//Connection 
	final PlayerConnectionTask playerConnectionTask;
	
	public Player(PlayerConnectionTask playerConnectionTask){
		this.playerConnectionTask = playerConnectionTask;
	}
	
	public void setHouse(int id) {
		if (id > 0 && id < Board.MAX_PLAYER - 1) {
			this.houseId = id;
		}
	}

	public void sentBoardState(Board board) {
	}

	public List<RaidOrder> getOrderTokenList() {
		return null;
	}

	public RaidOrder getSelectedRaidOrder() {
		return null;
	}

	public MarchOrder getSelectedMarchOrder() {
		return null;
	}

	public ConsolidatePowerOrder getSelectedConsolidatePowerOrder() {
		return null;
	}
}
