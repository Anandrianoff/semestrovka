package tatar.tourism.dao;

import tatar.tourism.pojo.Dialog;
import tatar.tourism.pojo.User;

import java.sql.SQLException;
import java.sql.Struct;
import java.util.List;

/**
 * Created by Andrey on 03.11.2016.
 */
public interface DialogsDao {
    public void create(String user1, String user2) throws SQLException;

    public List<Dialog> getDialogs(String username);

    public Dialog getDialog(int id);

    public Dialog getDialog(String user1, String user2);
}
