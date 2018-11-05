package WCThread;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Counter implements Runnable {
    private String filePath;
    private File file;
    private int wordCount;
    private Stage newStage;
    private TextArea tx;
    private final int WIDTH = 1280;
    private final int HEIGHT = 800;


    public Counter(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        wordCount = 0;
        initGUI();

    }

    private void initGUI() {
        tx = new TextArea();
        tx.setEditable(false);
        Scene scene = new Scene(tx);
        newStage = new Stage();
        newStage.setScene(scene);
        newStage.setX(new Random().nextInt(WIDTH));
        newStage.setY(new Random().nextInt(HEIGHT));
        newStage.show();
    }

    private void updateGUI() {
        tx.appendText("Number of words: " + wordCount);
    }

    private void countWords() {
        try(Scanner sc = new Scanner(new FileInputStream(file))){
            while(sc.hasNext()) {
                sc.next();
                wordCount++;
            }
        }
        catch(FileNotFoundException e) {

        }
    }

    @Override
    public void run() {
        countWords();
        updateGUI();
    }

}
