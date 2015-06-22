package com.chat.server;

import com.chat.db.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    private static ChatServer instance;
    private static final int PORT = 8081;
    private ArrayList<Client> clients;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private ChatServer() {

    }

    public static ChatServer getInstance() {
        if (instance == null) {
            instance = new ChatServer();
        }
        return instance;
    }

    public void acceptClients() {
        clients = new ArrayList<Client>();
        try {
            final ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client just connected!");

                Client client = new Client(clientSocket);
                clients.add(client);

                executorService.execute(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notifyAllClients(Packet packet) throws IOException {
        System.out.println("Notify all clients with message: " + packet.getMessage());
        String login = packet.getSender();
        for (Client client : clients) {
            if (login.equals(client.getLogin())) {
                client.sendPacket(packet);
            }
        }
    }

    public void sendPrivateMessage(String receiver, Packet packet) throws IOException{
        for (Client client : clients) {
            if (receiver.equals(client.getLogin())) {
                System.out.println("Sending private message for " + client.getLogin());
                client.sendPacket(packet);
            }
        }
    }

    public void removeClient(Client client) {
        clients.remove(client);
        System.out.println(client.getLogin() + " just disconnected.");
    }

    public void registerClient(RegistrationPacket p) {
        System.out.println("Try to add user to database");
        Session session = HibernateUtil.createSessionFactory().openSession();

        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
    }
}
