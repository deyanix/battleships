package pl.edu.pw.elka.prm2t22l.battleships;

import org.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadWriteFileManager<T> {
    private List<T> gameBoard = new ArrayList<>();
    private List<T> playerBoard = new ArrayList<>();
    private int time;

    public ReadWriteFileManager() {
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
        jObject.put("0", array1);
        jObject.put("1",array2);
        jObject.put("2",this.time);

        return jObject;
    }

    private JSONObject toJsonObject(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject;
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
            String data = "";
            while (reader.hasNextLine()) {
                data = data + reader.nextLine();
            }
            reader.close();
            JSONObject jsonObject = toJsonObject(data);
            return jsonObject;
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Integer> listToTest = new ArrayList<>();
        List<Integer> listToTest2 = new ArrayList<>();
        ReadWriteFileManager jsonTest = new ReadWriteFileManager();

        int N = 10;
        for(int i = 0; i < N; i++) {
            listToTest.add(i);
            for(int j = 0; j < N; j++) {
                listToTest2.add(j);
            }
        }

        jsonTest.setGameBoard(listToTest);
        jsonTest.setPlayerBoard(listToTest2);
        jsonTest.setTime(67);

        JSONObject newJsonObject = jsonTest.toJsonObject();
        String fileName = "data/jsonFile.json";

        jsonTest.writeFile(fileName);
        jsonTest.readFile(fileName);

        JSONObject jObject = jsonTest.readFile(fileName);
        System.out.println(jObject.get("0"));
        System.out.println(jObject.get("1"));
        System.out.println(jObject.get("2"));
    }
}
