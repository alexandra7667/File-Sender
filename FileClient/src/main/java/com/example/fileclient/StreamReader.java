/**
 * This class reads from the Socket's input stream.
 * It reads bytes that the server sends.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileclient;

import java.io.DataInputStream;

public abstract class StreamReader {
    private DataInputStream dataInputStream;

    public StreamReader(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    /**
     * This method reads the file length from the DataInputStream to calculate for how long
     * it should read the actual file.
     * If the file length is < 0, the file has not been sent/Server has shut down and all
     * connections close.
     * If the file has been sent, the DataInputStream proceeds to read the file and stores it as a byte array.
     * @return - The contents of the file
     */
    public byte[] readFromStream() {
        byte[] fileBytes = null;

        try {
            int fileLength = dataInputStream.readInt();

            if (fileLength > 0) {
                fileBytes = new byte[fileLength];

                //Start reading at offset 0 and read for the entire length of file
                dataInputStream.readFully(fileBytes, 0, fileLength);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return fileBytes;
    }
}
