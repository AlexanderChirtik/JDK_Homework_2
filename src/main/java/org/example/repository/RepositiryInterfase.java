package org.example.repository;

import org.example.client.Client;

public interface RepositiryInterfase {
    void disconnect();
    void addList(Client client);
    void answerAll(String text);
    void saveInLog(String text);
    String getHistory();
}
