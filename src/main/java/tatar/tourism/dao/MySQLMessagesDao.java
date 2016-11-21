package tatar.tourism.dao;

import sun.util.calendar.Gregorian;
import tatar.tourism.pojo.Dialog;
import tatar.tourism.pojo.Message;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Andrey on 05.11.2016.
 */
public class MySQLMessagesDao extends MySQLDaoFactory implements MessagesDao{
    @Override
    public List<Message> getMessageLatestTimestamp(Timestamp timestamp, int id) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE message_date > ? and id_dialog = ?";
        Message s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setTimestamp(1, timestamp);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s = new Message();
                s.setAuthor(rs.getString("author"));
                s.setDate(rs.getDate("message_date"));
//                s.setCalendar(rs.getTime("date"));
                s.setMessage(rs.getString("message"));
                s.setId_dialog(rs.getInt("id_dialog"));
                s.setId(rs.getInt("id_message"));
                messages.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return messages;
    }

    @Override
    public List<Message> getAllMessages(int id_dialog) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE id_dialog = ?";
        Message s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, Integer.toString(id_dialog));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s = new Message();
                s.setAuthor(rs.getString("author"));
                s.setDate(rs.getDate("message_date"));
//                s.setCalendar(rs.getTime("date"));
                s.setMessage(rs.getString("message"));
                s.setId_dialog(rs.getInt("id_dialog"));
                s.setId(rs.getInt("id_message"));
                messages.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return messages;
    }

    @Override
    public void writeNewMessage(Message message) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("INSERT INTO messages "
                    + "(id_dialog, message, author, message_date)"
                    + "VALUES( ?,?,?,?)");

            stmt.setInt(1, message.getId_dialog());
            stmt.setString(2, message.getMessage());
            stmt.setString(3, message.getAuthor());
            stmt.setTimestamp(4, new Timestamp(message.getDate().getTime()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
