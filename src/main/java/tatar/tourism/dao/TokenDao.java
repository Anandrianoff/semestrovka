package tatar.tourism.dao;

import tatar.tourism.pojo.Token;
import tatar.tourism.pojo.User;

import java.util.Date;

/**
 * Created by Andrey on 27.10.2016.
 */
public interface TokenDao {
    /**
     * Создает новую запись и соответствующий ей объект
     */
    public void create(Token token);

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    public Token readToken(String uuid);

    /**
     * Удаляет запись об объекте из базы данных
     */
    public void deleteUser(Token token);

    public void deleteToken(Token token);

    public void deleteToken(Date date);

    public User readUser(String uuid);
}
