package tatar.tourism.dao;

import tatar.tourism.pojo.Musician;
import tatar.tourism.pojo.Token;
import tatar.tourism.pojo.User;
import tatar.tourism.pojo.UserTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Andrey on 27.10.2016.
 */
public class MySQLTokenDao extends MySQLDao implements TokenDao {
    @Override
    public void create(Token token) {
        PreparedStatement stmt = null;
        Connection con = getConnection();

        try {
            stmt = con.prepareStatement("UPDATE users SET uuid =  ?, deletedate=? " +
                    "WHERE id =  ?");
            stmt.setString(1, token.getUuid());
            stmt.setDate(2, new java.sql.Date(token.getDeleteDate().getTime()));
            stmt.setInt(3, token.getUser().getDatabaseId());
            stmt.execute();
            //log.trace("Addition to notes by user " + note.getUser().getUsername());
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //log.error("Addition of new comment failed " + e.getLocalizedMessage());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Token readToken(String uuid) {
        String sql = "SELECT * FROM users WHERE uuid = ?";
        User s = null;
        Token t=new Token();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, uuid);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                UserTypes e = UserTypes.valueOf(rs.getString("role"));
                switch (e) {
                    case USER:
                        s = new User();
                        break;
                    case MUSICIAN:
                        s = new Musician();
                        break;
                }
                ;
                s.setDatabaseId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setPasswordHash(rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setFirstname(rs.getString("firstname"));
                s.setLastname(rs.getString("lastname"));
                s.setRole(rs.getString("role"));
                s.setActive(rs.getBoolean("active"));
                s.setConfirmed(rs.getBoolean("confirmed"));
                t.setUuid(uuid);
                t.setDeleteDate(rs.getDate("deletedate"));
                t.setUser(s);
            }
            else {t=null;}
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

        return t;
    }

    @Override
    public void deleteUser(Token token) {

    }

    @Override
    public void deleteToken(Token token) {

    }

    @Override
    public void deleteToken(Date date) {

    }

    @Override
    public User readUser(String uuid) {
        return null;
    }
}
