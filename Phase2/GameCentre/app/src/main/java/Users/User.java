package Users;

import java.io.Serializable;

/**
 * This class creates User object
 */
class User implements Serializable {

    /**
     * The user's name for current user.
     */
    private String userName;
    /**
     * The user's password for current user.
     */
    private String password;

    /**
     * The constructor of the class User.
     */
    public User(String username) {
        this.userName = username;
        this.password = null;
    }

    /**
     * Return if the given password matched the current password.
     *
     * @param password the given checkPassword
     * @return if the given password matched this.password
     */
    boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Set the password of the current user.
     *
     * @param password the password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the user's name of the current user.
     *
     * @return the userName of the current user.
     */
    String getUserName() {
        return this.userName;
    }


}
