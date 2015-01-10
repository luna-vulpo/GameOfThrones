package pl.gameofthrones.gameboard.fields;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.annotations.Expose;

import pl.gameofthrones.gameboard.pieces.Piece;

/**
 * 
 * pl: pole
 * @author arek
 *
 */
public abstract class Field {
    
    protected List<Field> neighborFields = new LinkedList<Field>();
    
    @Expose
    private List<Integer> neighborFieldIds = new LinkedList<Integer>();
    
    protected List<Piece> pieces = new LinkedList<Piece>();
    
    @Expose
    protected final int id;
    
    @Expose
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
        neighborFieldIds.add(field.id);
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
