package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.GamePlayManager;
import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

public class ReadFileManager {

    public GamePlayManager readFile(File file) throws IOException {
        JSONTokener tokener = new JSONTokener(new FileReader(file));
        JSONObject object = new JSONObject(tokener);
        long seed = object.getLong("seed");
        int width = object.getInt("width");
        int height = object.getInt("height");
        int numberOfStartingHints = object.getInt("numberOfStartingHints");
        boolean undoesAvailable = object.getBoolean("undoesAvailable");
        int takenHints = object.getInt("takenHints");
        long time = object.getLong("time");

        GameConfiguration configuration = new GameConfiguration();
        configuration.setSeed(seed);
        configuration.setBoardSize(width, height);
        configuration.setNumberOfStartingHints(numberOfStartingHints);
        configuration.setUndoesAvailable(undoesAvailable);
        JSONObject jsonShips = object.getJSONObject("ships");
        for (String typeText : jsonShips.keySet()) {
            ShipType type = ShipType.valueOf(typeText);
            int amount = jsonShips.getInt(typeText);
            configuration.setShipAmount(type, amount);
        }

        GamePlayManager manager = new GamePlayManager(configuration);
        manager.createBoard();
        manager.setPassedTime(Duration.ofSeconds(time));
        manager.getHintManager().setTakenHints(takenHints);
        RasterBoard board = manager.getBoard().getPlayerBoard();
        JSONArray jsonBoard = object.getJSONArray("board");
        for (Object jsonField : jsonBoard) {
            JSONObject jsonObjField = (JSONObject) jsonField;
            int x = jsonObjField.getInt("x");
            int y = jsonObjField.getInt("y");
            boolean immutable = jsonObjField.getBoolean("immutable");
            String stateText = jsonObjField.getString("state");
            Field field = board.getField(x, y);
            field.setState(FieldState.valueOf(stateText));
            field.setImmutable(immutable);
        }
        return manager;
    }
}
