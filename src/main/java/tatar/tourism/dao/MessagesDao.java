package tatar.tourism.dao;

import tatar.tourism.pojo.Message;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrey on 05.11.2016.
 */
public interface MessagesDao {

    public List<Message> getMessageLatestTimestamp(Timestamp timestamp, int id);

    public List<Message> getAllMessages(int id_dialog);

    public void writeNewMessage(Message message) throws SQLException;
}
