package tatar.tourism.dao;

import tatar.tourism.pojo.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 10.11.2016.
 */
public class MySQLPostsDao extends MySQLDaoFactory implements PostsDao {
    @Override
    public void add(Post post) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("INSERT INTO posts "
                    + "(text, author, date, tags, header)"
                    + "VALUES( ?,?,?,?,?)");

            stmt.setString(1, post.getText());
            stmt.setString(2, post.getAuthor());
            stmt.setTimestamp(3, new Timestamp(post.getDate().getTime()));
            stmt.setString(4, post.getTags());
            stmt.setString(5, post.getHeader());
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
    public List<Post> getAll() {
        List<Post> dialogs = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        Post s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s = new Post();
                s.setHeader(rs.getString("header"));
                s.setTags(rs.getString("tags"));
                s.setPostId(rs.getInt("post_id"));
                s.setAuthor(rs.getString("author"));
                s.setText(rs.getString("text"));
                s.setDate(rs.getTimestamp("date"));
                dialogs.add(s);
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

        return dialogs;
    }

    @Override
    public List<Post> getPostByTag(String tag) {
        return null;
    }
}
