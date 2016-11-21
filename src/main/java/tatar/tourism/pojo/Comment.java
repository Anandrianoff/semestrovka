package tatar.tourism.pojo;

/**
 * Created by Andrey on 11.11.2016.
 */
public class Comment implements Comparable<Comment>{
    private int commentId;
    private String author;
    private String text;
    private int postId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Comment o) {
        return this.commentId - o.getCommentId();
    }
}
