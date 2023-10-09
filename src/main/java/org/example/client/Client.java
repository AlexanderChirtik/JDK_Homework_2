package org.example.client;

import org.example.server.Server;

public class Client {

    private String name;
    private final ClientView clientView;
    private final Server server;
    private boolean connected;


    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean connectToServer(String name){
        this.name = name;
        if(server.connectUser(this)){
            printText(name + "Соединение установлено!\n");
            connected = true;
            String log = server.getHistory();
            if(log != null){
                printText(log);
            }
            return true;
        } else {
            printText("Ошибка соединения!\n");
            return false;
        }
    }

    // отключение пользователя и вывод поля ввода
    public void disconnect(Client client){
        if(connected){
            connected = false;
            clientView.disconnectFromServer(this);
            printText("Соединение с сервером завершено");
        }
    }

    // отправка сообщения на сервер
    public void sendMessage(String message){
        if(connected){
            if (!message.isEmpty()){
                server.sendMessage(name + ":" + message);
            }
        }else {
            printText("нет подключения к серверу!!!");
        }
    }

    // получение ответа от сервера
    public void serverAnswer(String answer){
        printText(answer);
    }

    public void printText(String text){
        clientView.showMessage(text);
    }

    public String getName() {
        return name;
    }
}
