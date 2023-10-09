package org.example;

import org.example.client.ClientGUI;
import org.example.repository.RepositoryLog;
import org.example.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new RepositoryLog());
        new ClientGUI(server);
        new ClientGUI(server);
    }
}