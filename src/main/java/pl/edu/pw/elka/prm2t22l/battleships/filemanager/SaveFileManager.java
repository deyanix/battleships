package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;
import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileManager {

    private final RasterBoard board;
    private final GameConfiguration gameConfiguration;
    private final long time;
    private final int takenHints;

    public SaveFileManager(RasterBoard board, GameConfiguration gameConfiguration, int takenHints, long time) {
        this.board = board;
        this.gameConfiguration = gameConfiguration;
        this.time = time;
        this.takenHints = takenHints;
    }

    public JSONObject createJsonField(Field field) {
        JSONObject jObject = new JSONObject();
        jObject.put("x", field.getX());
        jObject.put("y", field.getY());
        jObject.put("state", field.getState());
        jObject.put("immutable", field.isImmutable());

        return jObject;
    }

    public JSONArray createJsonBoard(RasterBoard rasterBoard) {
        JSONArray jsonArray = new JSONArray();
        for(Field field : rasterBoard) {
            JSONObject jsonObject = createJsonField(field);
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public void writeToFile(File file) throws IOException {
        JSONManager myJsonManager = new JSONManager();
        myJsonManager.putJsonObject("seed", gameConfiguration.getSeed());
        myJsonManager.putJsonObject("width",board.getWidth());
        myJsonManager.putJsonObject("height", board.getHeight());
        myJsonManager.putJsonObject("numberOfStartingHints", gameConfiguration.getNumberOfStartingHints());
        myJsonManager.putJsonObject("undoesAvailable", gameConfiguration.isUndoesAvailable());
        myJsonManager.putJsonObject("takenHints", takenHints);
        myJsonManager.putJsonObject("time", time);
        myJsonManager.putJsonObject("board", createJsonBoard(board));
        JSONObject ships = new JSONObject();
        for (ShipType type : ShipType.values()) {
            ships.put(type.toString(), gameConfiguration.getShipAmount(type));
        }
        myJsonManager.putJsonObject("ships", ships);

        FileWriter writer = new FileWriter(file);
        writer.write(myJsonManager.getJObject().toString(4));
        writer.close();
    }
}
