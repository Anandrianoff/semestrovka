package tatar.tourism.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andrey on 27.10.2016.
 */
public class MySQLDaoFactory extends DaoFactory {

    public static final String JNDI_MYSQL_RESOURCE = "java:comp/env/jdbc/tourismDS";

    public Connection createConnection() {

        Context ctx = null;
        try {
            ctx = new InitialContext();
            return ((DataSource) ctx.lookup(JNDI_MYSQL_RESOURCE)).getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        // Использовать DRIVER и DBURL для создания соединения
        // Рекомендовать реализацию/использование пула соединений
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return createConnection();
    }

    @Override
    public UserDao getUserDao() {
        return new MySQLUserDao();
    }

    @Override
    public TokenDao getTokenDao() {
        return new MySQLTokenDao();
    }

    @Override
    public DialogsDao getDialogsDao() {
        return new MySQLDialogsDao();
    }

    @Override
    public MessagesDao getMessagesDao() {
        return new MySQLMessagesDao();
    }

    @Override
    public PostsDao getPostsDao() {
        return new MySQLPostsDao();
    }

    @Override
    public CommentsDao getCommentsDao() {
        return new MySQLCommentsDao();
    }
}
