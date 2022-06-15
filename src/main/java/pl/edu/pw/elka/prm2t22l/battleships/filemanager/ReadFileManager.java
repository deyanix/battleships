package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileManager {

    public JSONObject readFile(String fileName) {
        try {
            JSONManager jsonManager = new JSONManager();
            File file = new File(fileName);

            Scanner reader = new Scanner(file);
            StringBuilder data = new StringBuilder();
            while (reader.hasNextLine()) {
                data.append(reader.nextLine());
            }
            reader.close();
            return jsonManager.toJsonObject(data.toString());
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
