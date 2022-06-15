package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;

public class JSONManager {

    private JSONObject jObject;

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

    public JSONArray toJsonArray(RasterBoard rasterBoard) {

        JSONArray jsonArray = new JSONArray();
        for(Field field : rasterBoard) {
            JSONObject jsonObject = toJsonObject(field);
            jsonArray.put(jsonObject);
        }
        return jsonArray;
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

    public JSONObject toJsonObject(Field field) {

        JSONObject jObject = new JSONObject();
        jObject.put(field.getX());
        jObject.put(field.getY());
        jObject.put(field.getState());
        jObject.put(field.isImmutable());

        return jObject;
    }

    public void putJsonObject(String key, JSONArray value) {
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
