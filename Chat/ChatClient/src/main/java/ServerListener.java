import java.io.*;

public class ServerListener implements Runnable {

    private final ObjectInputStream ois;

    public ServerListener(ObjectInputStream ois){
        this.ois = ois;
    }

    public void run() {
        try {
            while (true) {
                if (!receiveMessage()) { break; }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean receiveMessage() throws IOException, ClassNotFoundException {
        Packet packet = (Packet) ois.readObject();
        if (packet != null) {
            if ("exit".equals(packet.getMessage())) { return false; }
            System.out.println(packet.getSender() +
                    " to " + packet.getReceiver() +
                    " Message: " + packet.getMessage());
        }
        return true;
    }

}
