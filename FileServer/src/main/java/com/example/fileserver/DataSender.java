/**
 * This class is responsible for sending data to a connected client.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileserver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class DataSender {
    private DataOutputStream dataOutputStream;
    private FileInputStream fileInputStream;

    public DataSender(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    /**
     * This method sends a name list of all files that are available for download.
     * It first sends the number of files.
     * Then it sends the length of the file name, and the file name.
     * @param files - The name list of files
     */
    public void sendFileList(ArrayList<File> files) {
        try {
            dataOutputStream.writeInt(files.size());

            for(File file : files) {
                byte[] fileNameBytes = file.getName().getBytes();

                writeToStream(fileNameBytes.length, fileNameBytes);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads a file using a FileInputStream.
     * A byte array is with the same size as the data to be sent is created
     * and filled with the file contents.
     * @param file - The file path and file name of the file
     */
    public void sendFile(File file) {
        try {
            fileInputStream = new FileInputStream(file);

            int fileLength = (int) file.length();
            byte[] fileContentBytes = new byte[fileLength];

            fileInputStream.read(fileContentBytes);

            writeToStream(fileContentBytes.length, fileContentBytes);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sends a file to the client.
     * @param length - The length of the file
     * @param file - The contents of the file
     */
    private void writeToStream(int length, byte[] file) {
        try {
            dataOutputStream.writeInt(length);
            dataOutputStream.write(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
