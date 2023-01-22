/**
 * This class creates a Socket connection and in- and output streams for the client.
 * It handles interactivity between the Controller class and the other logic classes.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class Downloader {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private FileNameGetter fileNameGetter;
    private FileDownloader fileDownloader;

    /**
     * This method creates a Socket object for connection to the server.
     * It creates a DataInputStream to be able to read data from server
     * and a DataOutputStream to write data to the server (requests).
     * @param hostName - The server's address
     * @param hostPort - The server's port
     */
    public void setSocket(String hostName, int hostPort) {
        try {
            socket = new Socket(hostName, hostPort);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        fileNameGetter = new FileNameGetter(dataInputStream);
        fileDownloader = new FileDownloader(dataInputStream, dataOutputStream);
    }

    /**
     * This method closes the DataInputStream, DataOutputStream and the Socket.
     */
    public void close() {
        try {
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls on the FileNameGetter object to get file names from the server.
     * @return - A name list with all the server's available files
     */
    public List<String> getFileNamesFromServer() {
        return fileNameGetter.getFileNamesFromServer();
    }

    /**
     * This method calls on the FileDownloader object to download a specified file.
     * @param fileToDownload - The file name of the file to download
     */
    public void downloadFile(String fileToDownload) {
        fileDownloader.downloadFile(fileToDownload);
    }
}
