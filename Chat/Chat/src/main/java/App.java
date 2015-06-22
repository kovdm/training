import com.chat.server.ChatServer;

class App {
    public static void main(String[] args) {
        ChatServer chatServer = ChatServer.getInstance();
        chatServer.acceptClients();
    }
}
