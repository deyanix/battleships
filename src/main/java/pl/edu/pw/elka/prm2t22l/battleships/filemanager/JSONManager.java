package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public void putJsonObject(String key, double value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, char value) {
        this.jObject.put(key,value);
    }

    public void putJsonObject(String key, boolean[] value) {
        JSONArray jArray = toJsonArray(value);
        this.jObject.put(key,jArray);
    }

    public void putJsonObject(String key, int[] value) {
        JSONArray jArray = toJsonArray(value);
        this.jObject.put(key,jArray);
    }

    public void putJsonObject(String key, char[] value) {
        JSONArray jArray = toJsonArray(value);
        this.jObject.put(key,jArray);
    }

    public JSONObject getJObject() {
        return jObject;
    }

    public JSONObject toJsonObject(String data) {
        return new JSONObject(data);
    }
}
