
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

    public void connect(String login, String password) {

        try {
            clientSocket = new Socket(IP, PORT);
            reader = new BufferedReader(
                    new InputStreamReader(System.in));

            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());

            login(login, password);
            consoleReader();

            executorService.execute(new ServerListener(ois));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void login(String login, String password) {
        try {
            Packet packet = new Packet(login, null, null);
            oos.writeObject(packet);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String login, String password, String name, String phone) {
        try {
            RegistrationPacket packet = new RegistrationPacket(login, password, name, phone);
            oos.writeObject(packet);
            oos.flush();

            if (ois.read() == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                        Packet packet = new Packet(login, receiver, message);
                        oos.writeObject(packet);
                        oos.flush();

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

    private void close() throws IOException{
        reader.close();
        oos.close();
        ois.close();
        clientSocket.close();
    }
}
