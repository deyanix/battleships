package pl.edu.pw.elka.prm2t22l.battleships.filemanager;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFileManager {

    private boolean[] playerBoard;
    private int seed;
    private int time;

    SaveFileManager() {
        this.playerBoard = null;
        this.seed = 0;
        this.time = 0;
    }

    public void setPlayerBoard(boolean[] playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private void setAttributes(boolean[] playerBoard, int seed, int time) {
        this.playerBoard = playerBoard;
        this.seed = seed;
        this.time = time;
    }

    public void writeToFile(String fileName, boolean[] playerBoard, int seed, int time) {

        JSONManager myJsonManager = new JSONManager();
        setAttributes(playerBoard,seed,time);

        myJsonManager.putJsonObject("player_board",playerBoard);
        myJsonManager.putJsonObject("seed",seed);
        myJsonManager.putJsonObject("time",time);

        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(myJsonManager.getJObject().toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}