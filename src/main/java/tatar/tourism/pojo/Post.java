package tatar.tourism.pojo;


import java.util.Date;

/**
 * Created by Andrey on 09.11.2016.
 */
public class Post implements Comparable<Post>{
    private int postId;
    private String author;
    private Date date;
    private String text;
    private String tags;
    private String header;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setTags(String tags) {

        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Post o) {
        return this.date.compareTo(o.getDate());
    }
}
