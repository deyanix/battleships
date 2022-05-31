package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FileManagerPrototype<T> {
    private List<T> gameBoard;
    private List<T> playerBoard;
    private int time;

    public FileManagerPrototype() {
        gameBoard = null;
        playerBoard = null;
        time = 0;
    }

    public void setGameBoard(List<T> list1) {
        this.gameBoard = list1;
    }

    public List<T> getGameBoard() {
        return gameBoard;
    }

    public void setPlayerBoard(List<T> list2) {
        this.playerBoard = list2;
    }

    public List<T> getPlayerBoard() {
        return playerBoard;
    }

    public void setTime(int number) {
        this.time = number;
    }

    public int getTime() {
        return time;
    }

    public void showJsonObject(JSONObject someJObject) {
        System.out.println(someJObject);
    }

    private JSONArray toJsonArray(List<T> list) {
        JSONArray jArray = new JSONArray();
        for(T element : list) {
            jArray.put(element);
        }
        return jArray;
    }

    private JSONObject toJsonObject() {
        JSONObject jObject = new JSONObject();
        JSONArray array1 = toJsonArray(this.gameBoard);
        JSONArray array2 = toJsonArray(this.playerBoard);
        jObject.put("Game Board", array1);
        jObject.put("Player Board",array2);
        jObject.put("Time",this.time);

        return jObject;
    }

    private JSONObject toJsonObject(String data) {
        return new JSONObject(data);
    }

    public void writeFile(String fileName) {
        JSONObject jObject = toJsonObject();
        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(jObject.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            StringBuilder data = new StringBuilder();
            while (reader.hasNextLine()) {
                data.append(reader.nextLine());
            }
            reader.close();
            return toJsonObject(data.toString());
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
