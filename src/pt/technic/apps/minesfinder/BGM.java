package pt.technic.apps.minesfinder;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BGM extends Thread {
    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;

    public BGM(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(System.getProperty("user.dir") + "\\mp3\\" + name);
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new Player(bufferedInputStream);

        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            do {
                player.play();
                fileInputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                player = new Player(bufferedInputStream);
            } while (isLoop);

        } catch (JavaLayerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}