package pl.gameofthrones.gameboard.fields;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * pl: pole
 * @author arek
 *
 */
public abstract class Field {
    
    protected List<Field> neighborFields = new LinkedList<Field>();
    
    protected final int id;
    
    Field(int id){
        this.id = id;
    }

}
