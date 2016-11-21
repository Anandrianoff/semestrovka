package tatar.tourism.pojo;

/**
 * Created by Ilya Evlampiev on 20.10.2015.
 */
public enum UserTypes {


    MUSICIAN("MUSICIAN"),
    USER("USER")
    ;

    private final String text;

    /**
     * @param text
     */
    private UserTypes(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
