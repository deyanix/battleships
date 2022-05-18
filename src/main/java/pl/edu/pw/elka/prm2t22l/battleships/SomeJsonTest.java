package pl.edu.pw.elka.prm2t22l.battleships;

import org.json.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SomeJsonTest<T> {
    private List<T> list1 = new ArrayList<>();
    private List<T> list2 = new ArrayList<>();
    private int number;

    public void setList1(List<T> list1) {
        this.list1 = list1;
    }

    public List<T> getList1() {
        return list1;
    }

    public void setList2(List<T> list2) {
        this.list2 = list2;
    }

    public List<T> getList2() {
        return list2;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private JSONArray toJsonArray(List<T> list) {
        JSONArray jArray = new JSONArray();
        for(T element : list) {
            jArray.put(element);
        }
        return jArray;
    }

    public JSONObject toJsonObject() {
        JSONObject jObject = new JSONObject();
        int N = 3;
        JSONArray array1 = toJsonArray(this.list1);
        JSONArray array2 = toJsonArray(this.list2);
        jObject.put("0", array1);
        jObject.put("1",array2);
        jObject.put("2",this.number);

        return jObject;
    }

    public void showJsonObject(JSONObject someJObject) {
        System.out.println(someJObject);
    }

    public void writeFile(String fileName, JSONObject jObject) {
        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(jObject.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Integer> listToTest = new ArrayList<>();
        List<Integer> listToTest2 = new ArrayList<>();
        SomeJsonTest<Integer> jsonTest = new SomeJsonTest();
        int N = 10;
        for(int i = 0; i < N; i++) {
            listToTest.add(i);
            for(int j = 0; j < N; j++) {
                listToTest2.add(j);
            }
        }
        jsonTest.setList1(listToTest);
        jsonTest.setList2(listToTest2);
        jsonTest.setNumber(67);

        JSONObject newJsonObject = jsonTest.toJsonObject();
        jsonTest.showJsonObject(newJsonObject);
        String fileName = "data/jsonFile.json";
        jsonTest.writeFile(fileName,newJsonObject);
    }
}
