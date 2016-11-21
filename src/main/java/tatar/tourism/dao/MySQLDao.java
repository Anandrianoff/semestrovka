package tatar.tourism.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andrey on 27.10.2016.
 */
public class MySQLDao {
    Connection getConnection()
    {
        try {
            return DaoFactory.getDAOFactory(1).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
