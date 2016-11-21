package tatar.tourism.dao;

import tatar.tourism.pojo.Dialog;
import tatar.tourism.pojo.Musician;
import tatar.tourism.pojo.User;
import tatar.tourism.pojo.UserTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 03.11.2016.
 */
public class MySQLDialogsDao extends MySQLDaoFactory implements DialogsDao{
    @Override
    public void create(String user1, String user2) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try{
            stmt = con.prepareStatement("INSERT INTO dialogs "
                    + "(user1, user2)"
                    + "VALUES( ?,?)");
            stmt.setString(1, user1);
            stmt.setString(2, user2);

            stmt.execute();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Dialog> getDialogs(String userName) {
        List<Dialog> dialogs = new ArrayList<>();
        String sql = "SELECT * FROM dialogs WHERE user1 = ? or user2 = ?";
        Dialog s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, userName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s = new Dialog();
                s.setUser1(rs.getString("user1"));
                s.setUser2(rs.getString("user2"));
                s.setId(rs.getInt("id_dialog"));
                dialogs.add(s);
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

        return dialogs;

    }

    @Override
    public Dialog getDialog(int id) {
        String sql = "SELECT * FROM dialogs WHERE id_dialog = ?";
        Dialog s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s = new Dialog();
                s.setUser1(rs.getString("user1"));
                s.setUser2(rs.getString("user2"));
                s.setId(rs.getInt("id_dialog"));
            } else {
                s = null;
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

        return s;
    }

    @Override
    public Dialog getDialog(String user1, String user2) {
        String sql = "SELECT * FROM dialogs WHERE (user1 = ? and user2 = ?) or " +
                "(user1 = ? and user2 = ?)";
        Dialog s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, user1);
            stm.setString(2, user2);
            stm.setString(3, user2);
            stm.setString(4, user1);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s = new Dialog();
                s.setUser1(rs.getString("user1"));
                s.setUser2(rs.getString("user2"));
                s.setId(rs.getInt("id_dialog"));
            } else {
                s = null;
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

        return s;
    }
}
