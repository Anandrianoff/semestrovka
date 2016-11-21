package tatar.tourism.pojo;

/**
 * Created by Andrey on 27.10.2016.
 */
public class Musician extends User {

    public Musician() {
        super(null, null);
    }

    public Musician (String firstname, String lastname){
        super(firstname, lastname);
        setRole(UserTypes.MUSICIAN.toString());
    }

    @Override
    public boolean isMusician() {
        return true;
    }
}
