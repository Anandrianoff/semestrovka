package tatar.tourism.pojo;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Created by Andrey on 05.11.2016.
 */
public class Message implements Comparable<Message>{
    private int id;
    private int id_dialog;
    private String message;
    private String author;
    private Date date;
    private GregorianCalendar calendar;

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dialog() {
        return id_dialog;
    }

    public void setId_dialog(int id_dialog) {
        this.id_dialog = id_dialog;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    @Override
    public int compareTo(Message o) {
        return this.date.compareTo(o.getDate());
    }
}
