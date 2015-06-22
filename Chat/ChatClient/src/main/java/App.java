import com.chat.client.ClientGUI;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClientGUI clientGUI = new ClientGUI();
            }
        });
    }
}
