/**
 * This class is responsible for getting all available files' names from the server.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileclient;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

public class FileNameGetter extends StreamReader{
    private DataInputStream dataInputStream;
    private List<String> fileNames;

    public FileNameGetter(DataInputStream dataInputStream) {
        super(dataInputStream);
        this.dataInputStream = dataInputStream;
    }

    /**
     * This method reads the number of files that will be downloaded, and then reads file by file.
     * The list 'fileNames' is sent back to the Controller (the GUI).
     * @return - The names of all downloaded files (as received)
     */
    public List<String> getFileNamesFromServer() {
        fileNames = new ArrayList<>();

        int numberOfFiles = 0;
        try {
            numberOfFiles = dataInputStream.readInt();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < numberOfFiles; i++) {
            downloadFileName();
        }
        return fileNames;
    }

    /**
     * This method reads a file name.
     * The name is parsed to a String object and added to the name list of all available files.
     */
    private void downloadFileName() {
        try {
            byte[] fileNameBytes = readFromStream();
            String fileName = new String(fileNameBytes);
            fileNames.add(fileName);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
