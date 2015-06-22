import java.io.Serializable;

/**
 * flag can be: 0 - registration packet; 1 - normal packet;
 */

public class Packet implements Serializable {

    private static final long serialVersionUID = 1234567890;
    private String sender;
    private String receiver;
    private String message;
    private int flag;

    public Packet(int flag, String sender, String receiver, String message) {
        this.flag = flag;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
