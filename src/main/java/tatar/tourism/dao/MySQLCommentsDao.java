package tatar.tourism.dao;

import tatar.tourism.pojo.Comment;
import tatar.tourism.pojo.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 11.11.2016.
 */
public class MySQLCommentsDao extends MySQLDaoFactory implements CommentsDao {
    @Override
    public List<Comment> getAll(int dialogId) {
        List<Comment> messages = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE postId = ?";
        Comment s = null;
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, dialogId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s = new Comment();
                s.setAuthor(rs.getString("author"));
                s.setText(rs.getString("text"));
                s.setPostId(rs.getInt("postId"));
                s.setCommentId(rs.getInt("commentId"));
                messages.add(s);
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

        return messages;
    }

    @Override
    public void writeNew(Comment comment) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("INSERT INTO comments "
                    + "(author, postId, text)"
                    + "VALUES( ?,?,?)");

            stmt.setString(1, comment.getAuthor());
            stmt.setInt(2, comment.getPostId());
            stmt.setString(3, comment.getText());
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
}
