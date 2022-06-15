package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;

public class JSONManager {

    private final JSONObject jObject;

    JSONManager() {
        this.jObject = new JSONObject();
    }

    public void showMyJsonObject() {
        System.out.println(this.jObject);
    }

    public JSONArray toJsonArray(char[] array) {
        JSONArray jArray = new JSONArray();
        for(char element : array) {
            jArray.put(element);
        }
        return jArray;
    }

    public JSONArray shipsToJsonArray(JSONObject shortShip, JSONObject mediumShip, JSONObject longShip) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(shortShip);
        jsonArray.put(mediumShip);
        jsonArray.put(longShip);
        return jsonArray;
    }

    public JSONArray toJsonArray(Field[][] fields) {
        JSONArray jArray = new JSONArray();
        for(Field[] fieldsInRow : fields) {
            for(Field field : fieldsInRow) {
                jArray.put(field);
            }
        }
        return jArray;
    }

    public JSONArray toJsonArray(boolean[] array) {
        JSONArray jArray = new JSONArray();
        for(boolean element : array) {
            jArray.put(element);
        }
        return jArray;
    }

    public JSONArray toJsonArray(int[] array) {
        JSONArray jArray = new JSONArray();
        for(int element : array) {
            jArray.put(element);
        }
        return jArray;
    }

    public void putJsonObject(String key, int value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, long value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, double value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, char value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, JSONArray value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, JSONObject value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, boolean value) {
        this.jObject.put(key,value);
    }

    public JSONObject getJObject() {
        return jObject;
    }

    public JSONObject toJsonObject(String data) {
        return new JSONObject(data);
    }
}
