package pl.gameofthrones.gameboard.fields;

import java.util.LinkedList;
import java.util.List;

import pl.gameofthrones.gameboard.pieces.Piece;

/**
 * 
 * pl: pole
 * @author arek
 *
 */
public abstract class Field {
    
    protected List<Field> neighborFields = new LinkedList<Field>();
    
    protected List<Piece> pieces = new LinkedList<Piece>();
    
    protected final int id;
    
    protected final String name;
    
    
    /**
     * 
     * @param id
     * @param name
     */
    Field(int id, String name){
        this.id = id;
        this.name = name;
    }

    
    public void addNeighbor(Field field) {
        neighborFields.add(field);
    }

    void addPiece(Piece piece){
    	pieces.add(piece);
    }
    
    boolean removePiece(Piece piece){
    	return pieces.remove(piece);
    }
    
    int getPiecesCount(){
    	return pieces.size();
    }
}
