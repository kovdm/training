package com.chat.server;

import com.chat.packets.Packet;
import com.chat.packets.RegistrationPacket;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private final Socket clientSocket;
    private final ChatServer server;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String login;

    public Client(Socket clientSocket) throws IOException {
        server = ChatServer.getInstance();
        this.clientSocket = clientSocket;

        ois = new ObjectInputStream(this.clientSocket.getInputStream());
        oos = new ObjectOutputStream(this.clientSocket.getOutputStream());
    }

    public void run() {
        try {
            /*Object obj;
            if ((obj = ois.readObject()) instanceof RegistrationPacket) {
                System.out.println("Somebody wants to register!");
                RegistrationPacket p = (RegistrationPacket) obj;
                server.registerClient(p);
            } else {
                Packet p = (Packet) obj;
            }*/
            Object obj = ois.readObject();
            Packet p = (Packet) obj;
            if (p.getFlag() == 0) {
                System.out.println("Somebody wants to register!");
                RegistrationPacket regPacket = (RegistrationPacket) ois.readObject();
                server.registerClient(regPacket);
            } else if (p.getFlag() == 2) {
                if (!server.authorizeClient(p)) {
                    sendPacket(new Packet(11, null, null, null));
                    server.removeClient(this);
                    close();
                    Thread.currentThread().interrupt();
                }
            }

            while (true) {

                final Packet packet = (Packet) ois.readObject();
                System.out.println("Message received for " + login);

                if (packet != null) {
                    System.out.println(packet.getMessage());

                    if ("exit".equals(packet.getMessage())) {
                        server.removeClient(this);
                        close();
                        Thread.currentThread().interrupt();
                        break;
                    }

                    if ("all".equals(packet.getReceiver())) {
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    server.notifyAllClients(packet);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();

                    } else {
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                            try {
                                server.sendPrivateMessage(packet.getReceiver(), packet);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        });
                        t.start();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("Undefined behavior");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPacket(Packet packet) throws IOException{
        oos.writeObject(packet);
        oos.flush();
    }

    public String getLogin() {
        return login;
    }

    private void close() {
        try {
            ois.close();
            oos.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();
    }

}
