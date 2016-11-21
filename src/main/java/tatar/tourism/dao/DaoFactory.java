package tatar.tourism.dao;

import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andrey on 27.10.2016.
 */
public abstract class DaoFactory  {

    public static final int MYSQL = 1;

    public static DaoFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case 1:
                return new MySQLDaoFactory();
            default           :
                return null;
        }
    }
    /**
     * Возвращает подключение к базе данных
     */
    public abstract Connection getConnection() throws SQLException;

    public abstract UserDao getUserDao();
    public abstract TokenDao getTokenDao();
    public abstract DialogsDao getDialogsDao();
    public abstract MessagesDao getMessagesDao();
    public abstract PostsDao getPostsDao();
    public abstract CommentsDao getCommentsDao();

}
