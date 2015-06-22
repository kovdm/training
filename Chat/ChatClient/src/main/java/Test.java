import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        jPanel.add(scrollPane);
        jFrame.add(jPanel);
        jFrame.setSize(400, 300);
        jFrame.setVisible(true);
    }

}
