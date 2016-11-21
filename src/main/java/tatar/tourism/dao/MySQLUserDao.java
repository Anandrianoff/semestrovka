package tatar.tourism.dao;

import tatar.tourism.pojo.Musician;
import tatar.tourism.pojo.User;
import tatar.tourism.pojo.UserTypes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrey on 27.10.2016.
 */
public class MySQLUserDao extends MySQLDaoFactory implements UserDao {
    @Override
    public void create(User user) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("INSERT INTO users "
                    + "(username, password, email, role, firstname, lastname,active,confirmed)"
                    + "VALUES( ?,?,?,?,?,?,?,?)");

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getFirstname());
            stmt.setString(6, user.getLastname());
            stmt.setBoolean(7, user.isActive());
            stmt.setBoolean(8, user.isConfirmed());

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

    @Override
    public User read(int key) {
        return null;
    }

    @Override
    public void update(User user) {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            stmt = con.prepareStatement("UPDATE users SET username =  ?, password=?, email=?, role=?, firstname=?, lastname=?, active=?, confirmed=? " +
                    "WHERE id =  ?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getFirstname());
            stmt.setString(6, user.getLastname());
            stmt.setBoolean(7, user.isActive());
            stmt.setBoolean(8, user.isConfirmed());
            stmt.setInt(9, user.getDatabaseId());

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
    public void delete(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User read(String login, String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        User s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, login);
            stm.setString(2, sb.toString());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserTypes e = UserTypes.valueOf(rs.getString("role"));
                switch (e) {
                    case MUSICIAN:
                        s = new Musician();
                        break;
                    case USER:
                        s = new User();
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
    public User read(String login) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserTypes e = UserTypes.valueOf(rs.getString("role"));
                switch (e) {
                    case MUSICIAN:
                        s = new Musician();
                        break;
                    case USER:
                        s = new User();
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
    public TokenDao getTokenDao() {
        return null;
    }
}
