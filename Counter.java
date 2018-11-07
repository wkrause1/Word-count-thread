package WCThread;

import org.apache.commons.io.FileUtils;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Counter implements Runnable {
    private String filePath;
    private String fileText;
    private File file;
    private int wordCount;
    private int charCount;
    private Stage newStage;
    private TextArea tx;
    private final int WIDTH = 1280;
    private final int HEIGHT = 800;


    public Counter(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        wordCount = 0;
        charCount = 0;
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
        tx.appendText("\nNumber of characters: " + charCount + "\n\n");
        tx.appendText("Contents of file:\n");
        tx.appendText(fileText);
    }

    private void countWords() {
        try(Scanner sc = new Scanner(new FileInputStream(file))){
            fileText = FileUtils.readFileToString(file);
            while(sc.hasNext()) {
                String s = sc.next();
                s.toLowerCase();
                wordCount++;
                for (int i = 0; i<s.length(); i++) {
                    if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                        charCount++;
                    }
                }
            }
        }
        catch(FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        countWords();
        updateGUI();
    }

}
