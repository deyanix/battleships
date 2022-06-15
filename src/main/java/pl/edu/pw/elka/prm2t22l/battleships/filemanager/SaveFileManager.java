package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFileManager {

    private final boolean[] playerBoard;
    private final int seed;
    private final int time;

    SaveFileManager(boolean[] playerBoard, int seed, int time) {
        this.playerBoard = playerBoard;
        this.seed = seed;
        this.time = time;
    }

    public void writeToFile(String fileName) {

        JSONManager myJsonManager = new JSONManager();

        myJsonManager.putJsonObject("player_board",this.playerBoard);
        myJsonManager.putJsonObject("seed",this.seed);
        myJsonManager.putJsonObject("time",this.time);

        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(myJsonManager.getJObject().toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}