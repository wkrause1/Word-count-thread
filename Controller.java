package WCThread;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private ListView listViewFiles;
    private ObservableList<String> files = FXCollections.observableArrayList();
    private Stage stage;
    private List<File> fileList;
    private List <String> filePaths;
    private List<String> fileNames;
    private List<Counter> runnables;

    public Controller() {
        fileList = new ArrayList<>();
        filePaths = new ArrayList<>();
        fileNames = new ArrayList<>();
        runnables = new ArrayList<>();
    }

    private void makeFilePathList() {
        if (!fileList.isEmpty()) {
            for (File f : fileList) {
                filePaths.add(f.getPath());
            }
        }
    }

    public void showFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files","*.*"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Java Files", "*.java"));
        fileList = fileChooser.showOpenMultipleDialog(stage);
        makeFilePathList();
        makeFileNameList();
        fillListView();
    }

    private void makeFileNameList() {
        if(!fileList.isEmpty()) {
            for (File f : fileList) {
                fileNames.add(f.getName());
            }
        }
    }

    public void resetFileList() {
        //fileList.clear();
        filePaths.clear();
        fileNames.clear();
        files.clear();
        listViewFiles.setItems(files);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void fillListView() {
        files.addAll(fileNames);
        listViewFiles.setItems(files);
    }

    public void runThreads() {
        for (String path : filePaths) {
            Counter c = new Counter(path);
            runnables.add(c);
        }
        for (Counter c : runnables) {
            Thread t = new Thread(c);
            t.start();
        }
    }
}
