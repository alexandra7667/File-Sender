/**
 * This is the Controller class which handles interaction between user (GUI) and logic classes.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileserver;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Controller {
    private FileChooser fileChooser;
    private FileList fileList;
    private Server server;

    public Controller() {
        fileChooser = new FileChooser();
        fileList = new FileList();
        server = new Server(fileList);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    @FXML
    private ListView<String> listedFiles;

    /**
     * This method lets the user pick files s/he wants to put up for downloading by clients.
     * The names of the files are visible in a ListView (GUI).
     */
    @FXML
    protected void addFiles() {
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        for(File file : files) {
            fileList.addFile(file);

            String fileName = file.getName();
            listedFiles.getItems().add(fileName);
        }
    }

    /**
     * This method stops the Server object when exiting the program.
     */
    public void exit() {
        server.stopAll();
    }
}