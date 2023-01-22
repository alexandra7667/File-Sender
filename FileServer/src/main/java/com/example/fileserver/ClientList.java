/**
 * This class holds a list of all connected clients.
 * It is used when the program exits. (Shutting down the server requires all client connections to close.)
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileserver;

import java.util.ArrayList;

public class ClientList {
    private ArrayList<Client> clients;

    public ClientList() {
        clients = new ArrayList<>();
    }

    /**
     * This method adds a new client to the list
     * @param client - The new client
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * This method stops all client connections from running as threads
     * by terminating their run method and closing their sockets
     */
    public void stopClients() {
        for(Client client : clients) {
            client.stop();
        }
    }
}
