package org.example.repository;

import java.util.Iterator;
import java.util.List;

public class ListIterator<Client> implements Iterator<Client> {
    private List<Client> clientList;
    private int index = 0;
    public ListIterator(List<Client> clientList) {
    }

    @Override
    public boolean hasNext() {
        return index < clientList.size();
    }

    @Override
    public Client next() {
        return clientList.get(index++);
    }
}
