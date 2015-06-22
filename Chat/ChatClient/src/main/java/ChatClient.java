
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {

    private static final int PORT = 8081;
    private static final String IP = "localhost";
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private Socket clientSocket;
    private BufferedReader reader;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String login;

    public void connect() {

        try {
            clientSocket = new Socket(IP, PORT);
            reader = new BufferedReader(
                    new InputStreamReader(System.in));

            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());

            executorService.execute(new ServerListener(ois));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(String login, String password) {
        try {
            connect();
            sendPacket(2, login, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String login, String password, String name, String phone) {
        try {
            connect();
            sendPacket(0, "newUser", null, "newUser");
            RegistrationPacket packet = new RegistrationPacket(login, password, name, phone);
            oos.writeObject(packet);
            oos.flush();

            if (ois.read() == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void consoleReader() {
        Thread consoleReaderThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println("Enter the name of receiver");
                        String receiver = reader.readLine();
                        System.out.println("Enter the message");
                        String message = reader.readLine();

                        sendPacket(1, login, receiver, message);

                        if ("exit".equals(message)) {
                            close();
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        consoleReaderThread.start();
    }

    private void sendPacket(int flag, String sender, String receiver, String message) throws IOException{
        Packet packet = new Packet(flag, sender, receiver, message);
        oos.writeObject(packet);
        oos.flush();
    }

    private void close() throws IOException{
        reader.close();
        oos.close();
        ois.close();
        clientSocket.close();
    }
}
