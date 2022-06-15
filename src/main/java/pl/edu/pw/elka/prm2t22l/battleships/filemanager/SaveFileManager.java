package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveFileManager {

    private final RasterBoard board;
    private final GameConfiguration gameConfiguration;
    private final long time;
    private final int takenHints;

    SaveFileManager(RasterBoard board, GameConfiguration gameConfiguration, int takenHints) {
        this.board = board;
        this.gameConfiguration = gameConfiguration;
        this.takenHints = takenHints;
    }

    public void writeToFile(String fileName) {
        JSONManager myJsonManager = new JSONManager();

        myJsonManager.putJsonObject("seed", gameConfiguration.getSeed());
        myJsonManager.putJsonObject("width",board.getWidth());
        myJsonManager.putJsonObject("height", board.getHeight());
        myJsonManager.putJsonObject("number_of_starting_hints", gameConfiguration.get);
        myJsonManager.putJsonObject("number_of_available_undos", gameConfiguration.getNumberOfAvailableUndoes());
        myJsonManager.putJsonObject("taken_hints", this.takenHints);
        myJsonManager.putJsonObject("time", this.time);

        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(myJsonManager.getJObject().toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}