/**
 * This is the Server class which listens for incoming Socket connections and
 * creates one new Client object per connection.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileserver;

import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private FileList fileList;
    private ClientList clientList;
    private boolean alive;
    private ServerSocket serverSocket;
    public Server(FileList fileList) {
        this.fileList = fileList;
        clientList = new ClientList();
        alive = true;
    }

    /**
     * This method continuously listens for new incoming Socket connections.
     * When a new Socket connects, the connection is accepted by the SocketServer.
     * The Socket object is sent to a new Client object along with the files
     * available for downloading.
     */
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(2000);

            while(alive) {

                Socket socket = serverSocket.accept();

                Client client = new Client(socket, fileList.getFiles());
                new Thread(client).start();

                clientList.addClient(client);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method stops the run method and closes the ServerSocket,
     * effectively terminating the Server.
     */
    public void stopAll() {
        alive = false;

        try {
            serverSocket.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        clientList.stopClients();
    }
}
