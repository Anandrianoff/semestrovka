package tatar.tourism.dao;

import tatar.tourism.pojo.Comment;
import tatar.tourism.pojo.Message;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrey on 11.11.2016.
 */
public interface CommentsDao {
    public List<Comment> getAll(int dialogId);
    public void writeNew(Comment comment) throws SQLException;
}
