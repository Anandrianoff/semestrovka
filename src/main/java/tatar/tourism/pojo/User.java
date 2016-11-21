package tatar.tourism.pojo;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Andrey on 27.10.2016.
 */
public class User {

    private static final long serialVersionUID = 1L;

    private int databaseId=-1;
    private String username;
    private String passwordHash;
    private String email;
    private String firstname;
    private String lastname;
    private String role;
    private boolean confirmed;
    private boolean active;
    private List<Dialog> dialogs;
    private Dialog currentDialog;

    public Dialog getCurrentDialog() {
        return currentDialog;
    }

    public void setCurrentDialog(Dialog currentDialog) {
        this.currentDialog = currentDialog;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }

    static Logger log = Logger.getLogger(User.class);

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = UserTypes.USER.toString();
        this.confirmed = false;
        this.active = false;

    }

    public User() {
        new User(null, null);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.passwordHash;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) throws  NoSuchAlgorithmException
    {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        this.passwordHash = sb.toString();
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDatabaseId()
    {
        return this.databaseId;
    }

    public void setDatabaseId(int id)
    {
        this.databaseId=id;
    }

    public boolean isYou(User sessionUser)
    {
        log.info(this.getUsername()+" is checked the session user "+sessionUser.getUsername()+" the same or not ");
        return this.getDatabaseId()==sessionUser.getDatabaseId();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isMusician(){
        return false;
    }
}
