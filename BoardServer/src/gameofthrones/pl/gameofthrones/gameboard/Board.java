package pl.gameofthrones.gameboard;

import java.util.ArrayList;
import java.util.List;

import pl.gameofthrones.gameboard.fields.*;


/**
 * 
 * pl: plansza
 * @author arek
 *
 */
public final class Board { 
    
    final Field[] fields = new Field[40];
    final Player[] players;
    int tourIndicator = 1;
    
    private Board(Player[] players){
        this.players = players;
        setupFields();
        schuffleWildlingsCards();
        schuffleWesterosCards();
        spreadNeutralArmy(players.length);
    }
    
    private void setupFields() {
       fields[0] = new OpenSea(0,"Bay of Ice");
       fields[1] = new Castle(1,"Castle Black");
       fields[2] = new OpenSea(2,"The Shivearing Sea");
       fields[3] = new Stronghold(3,"Winterfell");
       fields[4] = new Castle(4,"Karnhold");
//       fields[2] = new OpenSea(2,"The Shivearing Sea");
//       fields[2] = new OpenSea(2,"The Shivearing Sea");
//       fields[2] = new OpenSea(2,"The Shivearing Sea");

       
       fields[0].addNeighbor(fields[1]);
    }
    
    
    private void schuffleWildlingsCards(){
        
    }
    
    private void schuffleWesterosCards(){
        
    }
    
    private void spreadNeutralArmy(int numberOfPlayers){
        
    }
    /**
     * configure and setup Board object
     * @author arek
     *
     */
    public static class Builder {
        int playerCounter = 0;

        // TODO: PlayerTask is first approach. so should be replaced 
        private List<Player> players = new ArrayList<Player>();
       
       /**
        * Attached new player to the Board
        * @param player
        * @return
        * @throws TooManyPlayersException
        */
       public Builder addPlayer(Player player) throws TooManyPlayersException {
           if(players.size() >= 6)
               throw new TooManyPlayersException();
               
           players.add(player);
           return this;
        }
  
       public Board build(){           
           return new Board(players.toArray(new Player[0]));
       }
        
       public class TooManyPlayersException extends Exception {
        private static final long serialVersionUID = 1L;
       }        
    }
}
