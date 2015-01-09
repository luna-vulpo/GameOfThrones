package pl.gameofthrones.gameboard.decks;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deck<C> implements Iterable<C> {
	
	List<C> pileOfCards;
	
	public Deck(){
		 pileOfCards = new LinkedList<C>();
	}
	
	public void addCard(C card){
		pileOfCards.add(card);
	}
	
	public void shuffle(){
		Collections.shuffle(pileOfCards);
	}
	
	public C peek(){
		return pileOfCards.get(0);
	}

	public int cardsLeft(){
		return pileOfCards.size();
	}
	
	public void moveFirstToLast(){
		C card = pileOfCards.remove(0);
		pileOfCards.add(card);
	}

	@Override
	public Iterator<C> iterator() {
		return new DeckIterator();
	}
	
	class DeckIterator implements Iterator<C>{
		int currentIndex;
		
		public DeckIterator() {
			currentIndex=0;
		}
		
		@Override
		public boolean hasNext() {
			if(currentIndex<pileOfCards.size())return true;
			return false;
		}

		@Override
		public C next() {
			return pileOfCards.get(currentIndex);
		}
		
	}

	

}
