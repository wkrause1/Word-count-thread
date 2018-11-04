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
    ListView listViewFiles;
    ObservableList<String> files = FXCollections.observableArrayList();
    FileChooser fileChooser;
    private Stage stage;
    List<File> fileList;
    List <String> filePaths;
    List<String> fileNames;

    public Controller() {
        //fileChooser = new FileChooser();
        fileList = new ArrayList<>();
        filePaths = new ArrayList<>();
        fileNames = new ArrayList<>();
    }

    private void makeFilePathList() {
        for (File f : fileList) {
            filePaths.add(f.getPath());
        }
    }

    public void showFileChooser() {
        fileChooser = new FileChooser();
        fileList = fileChooser.showOpenMultipleDialog(stage);
        makeFilePathList();
        mamkeFileNameList();
        fillListView();
    }

    private void mamkeFileNameList() {
        for (File f : fileList) {
            fileNames.add(f.getName());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void fillListView() {
        files.addAll(fileNames);
        listViewFiles.setItems(files);
    }
}
