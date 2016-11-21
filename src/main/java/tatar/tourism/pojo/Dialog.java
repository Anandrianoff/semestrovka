package tatar.tourism.pojo;

import java.util.List;

/**
 * Created by Andrey on 03.11.2016.
 */
public class Dialog {


    private List<Message> messages;

    private int id;

    private String user1;
    private String user2;
    public Dialog(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public Dialog() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getUser1() {

        return user1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }
}
