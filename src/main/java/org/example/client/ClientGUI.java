package org.example.client;

import org.example.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    JTextArea log;

    JTextField fieldHost, fieldPort, fieldLogin, fieldMessage;
    JPasswordField fieldPassword;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    private Client client;

    public ClientGUI(Server server){
        this.client = new Client(this, server);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setSize(WIDTH, HEIGHT);
        createPanel();
        setVisible(true);
    }

    // Соединение с сервером. Если соединение прошло удачно, панель скроется
    private void connectToServer(){
        if(client.connectToServer(fieldLogin.getText())){
            hideHeaderPanel(false);
        }
    }

    private void hideHeaderPanel(boolean visible) {
        headerPanel.setVisible(visible);
    }

    public void sendMessage(){
        client.sendMessage(fieldMessage.getText());
        fieldMessage.setText("");
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }
    private void createPanel() {
        add(createTopPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }
    private Component createTopPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        fieldHost = new JTextField();
        fieldPort = new JTextField();
        fieldPassword = new JPasswordField();
        fieldLogin = new JTextField();
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });
        headerPanel.add(fieldHost);
        headerPanel.add(fieldPort);
        headerPanel.add(new JPanel());
        headerPanel.add(fieldLogin);
        headerPanel.add(fieldPassword);
        headerPanel.add(btnLogin);
        return headerPanel;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setEnabled(false);
        return new JScrollPane(log);
    }
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        fieldMessage = new JTextField();
        fieldMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    sendMessage();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(fieldMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }

    @Override
    public void disconnectFromServer(Client client) {
        hideHeaderPanel(true);
        client.disconnect(client);
    }

    @Override
    protected void processWindowEvent(WindowEvent e){
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectFromServer(client);
        }
    }
}