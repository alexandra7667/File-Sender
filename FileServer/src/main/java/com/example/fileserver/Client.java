/**
 * This class represents one connected client and runs as a separate thread.
 * It handles all communication with a client.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileserver;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements Runnable{
    private boolean alive;
    private Socket socket;
    private ArrayList<File> files;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public Client(Socket socket, ArrayList<File> files) {
        this.socket = socket;
        this.files = files;
        alive = true;
    }

    /**
     * This method starts by creating data streams for read/write to client.
     * It creates a DataSender object which sends the list of all available files to client.
     * It loops by listening to the client for which file s/he wants to download,
     * and send the requested file.
     * If the client disconnects (the request is null), the thread is terminated and
     * the Socket connection is closed.
     */
    @Override
    public void run() {

        try {
            createDataStreams();

            ClientRequest clientRequest = new ClientRequest(dataInputStream, this);

            DataSender dataSender = new DataSender(dataOutputStream);
            dataSender.sendFileList(files);

            while(alive) {
                String fileName = clientRequest.getFileName();

                if(fileName != null) {
                    for (File file : files) {
                        if (file.getName().equals(fileName)) {
                            dataSender.sendFile(file);
                        }
                    }
                }
                else {
                    stop();
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a DataOutputStream and a DataInputStream.
     */
    private void createDataStreams() {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method terminates the run method from running as a thread.
     * It closes all streams and the Socket connection.
     */
    public void stop() {
        alive = false;

        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
