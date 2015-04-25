package pl.gameofthrones.gameboard.fields;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;

import pl.gameofthrones.gameboard.pieces.Piece;

/**
 * 
 * pl: pole
 * 
 * @author arek
 * 
 */
public abstract class Field {

	protected List<Field> neighborFields = new LinkedList<Field>();

	@Expose
	private List<Integer> neighborFieldIds = new LinkedList<Integer>();

	protected List<Piece> pieces = new LinkedList<Piece>();

	@Expose
	public final int id;

	@Expose
	protected final String name;

	@Expose
	protected int moveCost = 1;

	@Expose
	protected String fieldType;
	/**
	 * 
	 * @param id
	 * @param name
	 */
	Field(String fieldType, int id, String name) {
		this.id = id;
		this.fieldType = fieldType;
		this.name = name;
	}

	public void addNeighbor(Field field) {
		neighborFields.add(field);
		neighborFieldIds.add(field.id);
	}

	void addPiece(Piece piece) {
		pieces.add(piece);
	}

	boolean removePiece(Piece piece) {
		return pieces.remove(piece);
	}

	int getPiecesCount() {
		return pieces.size();
	}

	public String toString(){
		return ""+id + ". " + name +"("+fieldType+")";
	}

	/**
	 * (De)serializer all classes inhered from Field. It base on value of fieldType 
	 * @author arek
	 *
	 */
	public static class GsonAdapter implements JsonSerializer<Field>, JsonDeserializer<Field> {
		
		final static String packageName = Field.class.getPackage().getName();

		@Override
		public JsonElement serialize(Field src, Type typeOfSrc, JsonSerializationContext context) {
			return context.serialize(src);
		}

		@Override
		public Field deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			String type = jsonObject.get("fieldType").getAsString();
			try {
				
				return context.deserialize(json, Class.forName(packageName+"."+type));
			} catch (ClassNotFoundException ex) {
				throw new JsonParseException("Unknown element type: " + type, ex);
			}
		}
	}
}
