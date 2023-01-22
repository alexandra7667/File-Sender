/**
 * This is the Controller class which handles interactivity between the user (GUI) and
 * the logic of the program (the class Downloader).
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileclient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Downloader downloader;
    private String fileToDownload;
    private int numberOfDownloadedFiles;

    public Controller() {
        downloader = new Downloader();
    }
    @FXML
    private TextField textFieldHostName;
    @FXML
    private TextField textFieldHostPort;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label labelDownloadedFiles;

    /**
     * This method adds a Listener to the ListView object so when the user clicks on a
     * different file name, the variable 'fileToDownload' is updated.
     * @param arg0 - (defined by Oracle) The location used to resolve relative paths for the root object, or null if the location is not known
     * @param arg1 - (defined by Oracle) The resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) ->
                fileToDownload = listView.getSelectionModel().getSelectedItem());
    }

    /**
     * This method retrieves user input in the form of server name and port
     * to connect to.
     * It instantiates a new Downloader object which handles the downloading of files.
     * A list is sent back from the downloader with the names of all files that were downloaded.
     * The list is displayed to the user (on the GUI).
     */
    @FXML
    protected void getFiles() {
        String hostName = textFieldHostName.getText();
        int hostPort = Integer.parseInt(textFieldHostPort.getText());

        downloader.setSocket(hostName, hostPort);

        List<String> fileNames = downloader.getFileNamesFromServer();

        listView.getItems().addAll(fileNames);
    }

    /**
     * This method retrieves user input in the form of which file s/he wants to download.
     * The logic of the download is handled by the logic classes.
     * When the file has been downloaded, the GUI is updated with the number of files
     * the user has downloaded in total.
     */
    @FXML
    protected void downloadFile() {
        downloader.downloadFile(fileToDownload);

        labelDownloadedFiles.setText("Downloaded files: " + ++numberOfDownloadedFiles);
    }

    /**
     * This method closes the downloader object when exiting the program.
     */
    public void exit() {
        downloader.close();
    }
}