package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveFileManager {

    private final RasterBoard board;
    private final GameConfiguration gameConfiguration;
    private final long time;
    private final int takenHints;

    SaveFileManager(RasterBoard board, long time,GameConfiguration gameConfiguration, int takenHints) {
        this.board = board;
        this.gameConfiguration = gameConfiguration;
        this.time = time;
        this.takenHints = takenHints;
    }

    private JSONObject toJsonObject(String key, int value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key,value);
        return jsonObject;
    }

    public void writeToFile(String fileName) {
        JSONManager myJsonManager = new JSONManager();

        myJsonManager.putJsonObject("seed", gameConfiguration.getSeed());
        myJsonManager.putJsonObject("width",board.getWidth());
        myJsonManager.putJsonObject("height", board.getHeight());
        myJsonManager.putJsonObject("number_of_starting_hints", gameConfiguration.getNumberOfStartingHints());
        myJsonManager.putJsonObject("number_of_available_undos", gameConfiguration.isUndoesAvailable());
        myJsonManager.putJsonObject("taken_hints", this.takenHints);
        myJsonManager.putJsonObject("time", this.time);
        myJsonManager.putJsonObject("board", myJsonManager.toJsonArray(board.getFields()));
        int smallShipAmount = gameConfiguration.getShipAmount(ShipType.SHORT);
        int mediumShipAmount = gameConfiguration.getShipAmount(ShipType.MEDIUM);
        int longShipAmount = gameConfiguration.getShipAmount(ShipType.LONG);

        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(myJsonManager.getJObject().toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}