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
    
    protected List<Field> mNeighborFields = new LinkedList<Field>();
    
    protected List<Piece> mPieces = new LinkedList<Piece>();
    
    protected final int mId;
    
    protected final String mName;
    
    
    /**
     * 
     * @param id
     * @param name
     */
    Field(int id, String name){
        this.mId = id;
        this.mName = name;
    }

    
    public void addNeighbor(Field field) {
        mNeighborFields.add(field);
    }

    void addPiece(Piece piece){
    	mPieces.add(piece);
    }
    
    boolean removePiece(Piece piece){
    	return mPieces.remove(piece);
    }
    
    int getPiecesCount(){
    	return mPieces.size();
    }
}
