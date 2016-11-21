package tatar.tourism.dao;

import tatar.tourism.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrey on 27.10.2016.
 */
public interface UserDao {

    /**
     * Создает новую запись и соответствующий ей объект
     */
    public void create(User user) throws SQLException;

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    public User read(int key);

    /**
     * Сохраняет состояние объекта group в базе данных
     */
    public void update(User user);

    /**
     * Удаляет запись об объекте из базы данных
     */
    public void delete(User user);

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     */
    public List<User> getAll();

    public User read(String login, String password);

    public User read(String login);

}
