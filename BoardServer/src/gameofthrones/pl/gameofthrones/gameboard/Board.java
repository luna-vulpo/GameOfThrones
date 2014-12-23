package pl.gameofthrones.gameboard;

import java.util.LinkedList;
import java.util.List;

import pl.gameofthrones.gameboard.fields.*;

/**
 * 
 * pl: plansza
 * @author arek
 *
 */
public final class Board {
    /**
     * not lazy thread safe singleton
     */
    private static volatile Board mBoard = new Board(); 
    Field[] fields = new Field[40];

    
    private Board(){}
    
    public static Board getBoard(){
        return mBoard;
    }
    
}
