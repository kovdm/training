import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {

    private ChatClient chatClient;
    private JTextArea messageWindow;
    private JTextField inputMessage;

    public ClientGUI() {
        super("Client");
        chatClient = new ChatClient();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;

        buildTextArea();
        gbc = makeGbc(0, 0, 1, 1, 0, 0, new Insets(1, 1, 1, 1), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        mainPanel.add(new JScrollPane(messageWindow), gbc);

        buildTextField();
        gbc = makeGbc(0, 1, 1, 1, 0, 0, new Insets(1, 1, 1, 1), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        mainPanel.add(inputMessage, gbc);

        gbc = makeGbc(0, 2, 1, 1, 0, 0, new Insets(1, 1, 1, 1), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        mainPanel.add(new ButtonPane(), gbc);

        mainPanel.setVisible(true);
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        centredFrame();
        setVisible(true);
    }

    private void buildTextArea() {
        messageWindow = new JTextArea(10, 30);
        messageWindow.setLineWrap(true);
        messageWindow.setWrapStyleWord(true);
        messageWindow.setEditable(false);
    }

    class ButtonPane extends JPanel {

        private JButton send;
        private JButton connect;

        public ButtonPane() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            send = new JButton("Send");
            send.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });

            connect = new JButton("Connect");
            connect.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JComponent[] inputs = new JComponent[1];
                    JPanel loginPane = new JPanel(new SpringLayout());

                    JLabel loginLbl = new JLabel("login", JLabel.TRAILING);
                    JLabel passwordLbl = new JLabel("password", JLabel.TRAILING);
                    JTextField loginField = new JTextField(10);
                    JTextField passwordField = new JTextField(10);

                    loginLbl.setLabelFor(loginField);
                    passwordLbl.setLabelFor(passwordField);

                    loginPane.add(loginLbl);
                    loginPane.add(loginField);
                    loginPane.add(passwordLbl);
                    loginPane.add(passwordField);

                    SpringUtilities.makeCompactGrid(loginPane,
                            2, 2,
                            6, 6,
                            6, 6);

                    inputs[0] = loginPane;
                    int reply = JOptionPane.showConfirmDialog(null, inputs, "Log in", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (reply == 0) {
                        chatClient.login(loginField.getText(), passwordField.getText());
                        messageWindow.append("Connected to server");
                    } else {
                        System.out.println(loginField.getText());
                        System.out.println(passwordField.getText());
                    }
                }
            });

            JButton registerBtn = new JButton("Register");
            registerBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JComponent[] inputs = new JComponent[1];
                    JPanel loginPane = new JPanel(new SpringLayout());

                    JLabel loginLbl = new JLabel("login", JLabel.TRAILING);
                    JLabel passwordLbl = new JLabel("password", JLabel.TRAILING);
                    JLabel nameLbl = new JLabel("name", JLabel.TRAILING);
                    JLabel phoneLbl = new JLabel("phone", JLabel.TRAILING);

                    JTextField loginField = new JTextField(10);
                    JTextField passwordField = new JTextField(10);
                    JTextField nameField = new JTextField(10);
                    JTextField phoneField = new JTextField(10);

                    loginLbl.setLabelFor(loginField);
                    passwordLbl.setLabelFor(passwordField);
                    nameLbl.setLabelFor(loginField);
                    phoneLbl.setLabelFor(passwordField);

                    loginPane.add(loginLbl);
                    loginPane.add(loginField);
                    loginPane.add(passwordLbl);
                    loginPane.add(passwordField);
                    loginPane.add(nameLbl);
                    loginPane.add(nameField);
                    loginPane.add(phoneLbl);
                    loginPane.add(phoneField);

                    SpringUtilities.makeCompactGrid(loginPane,
                            4, 2,
                            6, 6,
                            6, 6);

                    inputs[0] = loginPane;
                    int reply = JOptionPane.showConfirmDialog(null, inputs, "Registration form", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (reply == 0) {
                        boolean isRegister = chatClient.register(loginField.getText(), passwordField.getText(), nameField.getText(), phoneField.getText());
                        if (isRegister) {
                            messageWindow.append("You are successfully register!");
                        } else {
                            messageWindow.append("Error!");
                        }
                    }
                }
            });

            add(send);
            add(connect);
            add(registerBtn);
        }

    }

    private void buildTextField() {
        inputMessage = new JTextField(30);
    }

    private GridBagConstraints makeGbc(int gridx, int gridy,
                                       int gridwidth, int gridheight,
                                       int weightx, int weighty,
                                       Insets insets,
                                       int anchor, int fill) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = insets;
        gbc.anchor = anchor;
        gbc.fill = fill;

        return gbc;
    }

    public void centredFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
}
