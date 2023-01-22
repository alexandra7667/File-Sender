/**
 * This class is responsible for communication with the server and downloading.
 * When the client has chosen a file to download from the list, this class handles
 * the downloading from server and stores the file locally.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileclient;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileDownloader extends StreamReader{
    private DataOutputStream dataOutputStream;
    private FileChooser fileChooser;

    public FileDownloader(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        super(dataInputStream);
        this.dataOutputStream = dataOutputStream;
        fileChooser = new FileChooser();
    }

    /**
     * This method handles all communication with the server when the client has chosen a file to download.
     * It requests a file from the server, downloads the provided file, and lets the user store the file locally.
     * @param fileName - The file name of the file the client has chosen to download
     */
    public void downloadFile(String fileName) {
        requestFile(fileName);

        byte[] fileContents = readFromStream();

        fileChooser.setInitialFileName(fileName);
        File file = fileChooser.showSaveDialog(new Stage());

        writeToDisk(file, fileContents);
    }

    /**
     * This method requests a file from the server.
     * It sends the length of the file name, and then the file name.
     * @param fileName - The name of the file the client requested
     */
    private void requestFile(String fileName) {
        byte[] fileNameBytes  = fileName.getBytes();

        try {
            dataOutputStream.writeInt(fileNameBytes.length);
            dataOutputStream.write(fileNameBytes);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes the file to disk. FileOutputStream allows to write data into a file.
     * @param file - The location of the file including file path and file name
     * @param fileContentBytes - The contents of the file
     */
    private void writeToDisk(File file, byte[] fileContentBytes) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(fileContentBytes);
            fileOutputStream.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
