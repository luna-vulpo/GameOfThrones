package pl.gameofthrones.gameboard;

import java.util.List;

import pl.gameofthrones.gameboard.tokens.ConsolidatePowerOrder;
import pl.gameofthrones.gameboard.tokens.MarchOrder;
import pl.gameofthrones.gameboard.tokens.OrderToken;
import pl.gameofthrones.gameboard.tokens.RaidOrder;

public class Player {
	public static String TAG= "Player";
	
	public static int SINGLE_HOUSE_CARD_COUNT=7;
	
	public static int MAX_POWER_POOL=20;
	
	int house;
	
	HouseCard[] houseCards = new HouseCard[SINGLE_HOUSE_CARD_COUNT];
	
	int powerPool=MAX_POWER_POOL;
	
	int availablePowerToken=0;
	

	void setHouse(int house) {
		this.house = house;
	}

	void sentBoardState(Board board) {
	}

	public List<OrderToken> getOrderTokenList() {
		return null;
	}
	
	public void getPowerTokensFromPool(int num) {
		// if(powerPool-num<0){
		// Log.e(TAG,
		// String.format("Taking more power tokens than left in pool: currentPool:%d, taken: %d",powerPool,num));
		// return;
		// }
		availablePowerToken += num;
		powerPool += num;
	}
	
	public void discardPowerTokensToPool(int num) {
		availablePowerToken -= num;
		powerPool += num;
	}

	public RaidOrder selectRaidOrder() {
		return null;
	}

	public MarchOrder selectMarchOrder() {
		return null;
	}

	public ConsolidatePowerOrder selectConsolidatePowerOrder() {
		return null;
	}
}
