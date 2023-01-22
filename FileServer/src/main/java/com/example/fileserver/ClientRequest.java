/**
 * This class listens for the connected client to request a file to download.
 * When the client has requested a file, the file name is returned to the Client class.
 *
 * @author Alexandra Härnström
 * @version 1
 */
package com.example.fileserver;

import java.io.DataInputStream;

public class ClientRequest {
    private DataInputStream dataInputStream;
    private Client client;

    public ClientRequest(DataInputStream dataInputStream, Client client) {
        this.dataInputStream = dataInputStream;
        this.client = client;
    }

    /**
     * This method reads the name of the file the connected client wants to download.
     * @return - The name of the file the client requests
     */
    public String getFileName() {
        String fileName = null;
        try {
            int fileNameLength = dataInputStream.readInt();

            if (fileNameLength > 0) {
                byte[] fileNameBytes = new byte[fileNameLength];

                //Start reading at offset 0 and read for the entire length of filename
                dataInputStream.readFully(fileNameBytes, 0, fileNameLength);

                fileName = new String(fileNameBytes);
            }
            else {
                client.stop();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
