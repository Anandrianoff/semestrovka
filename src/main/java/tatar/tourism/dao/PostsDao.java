package tatar.tourism.dao;

import tatar.tourism.pojo.Post;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrey on 10.11.2016.
 */
public interface PostsDao {
    public void add(Post post) throws SQLException;

    public List<Post> getAll();

    public List<Post> getPostByTag(String tag);
}
