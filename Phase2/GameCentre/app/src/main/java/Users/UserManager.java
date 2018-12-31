package Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class manages all the user of the game.
 */
public class UserManager implements Serializable {

    /**
     * An List that store all the users.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Return the user's name of the current user.
     *
     * @param username the username of the current user.
     * @param password the password of the current user.
     * @return the name of user who log in.
     */
    public String login(String username, String password) {
        for (User u : users) {
            if (u.getUserName().equals(username) && u.checkPassword(password)) {
                return u.getUserName();
            }
        }
        return "";
    }

    /**
     * Return if the user with username is in the users's Array List.
     *
     * @param username the username of the current user.
     * @return if the user with username is in the users's Array List.
     */
    boolean exist(String username) {
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Let the user with username and password sign up.
     *
     * @param username the username of the current user.
     * @param password the password of the current user.
     */
    void signUp(String username, String password) {
        User tempUser = new User(username);
        tempUser.setPassword(password);
        users.add(tempUser);
    }

    /**
     * Return the user with username in the users's Array List.
     *
     * @param username the username of the current user.
     * @return the user with username in the users's Array List.
     */
    User getUser(String username) {
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return null;
    }


    /**
     * Return the validity of given name during Registration
     *
     * @param name the given user name
     * @return the validity of given name
     */
    String checkUserNameValidity(String name) {
        if (name == null || !name.contains("@")) {
            return "Please enter a valid e-mail";
        } else if (exist(name)) {
            return "E-mail exist";
        } else {
            return "OK!";
        }
    }

    /**
     * return true iff the password is valid.
     *
     * @param password the password
     * @return true iff the password is valid.
     */
    boolean isValidPassword(String password) {
        return (password.length() >= 6);
    }
}