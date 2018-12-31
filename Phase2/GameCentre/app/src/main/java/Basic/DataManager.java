package Basic;

/**
 * This is a DataManager that will manage the BoardManager for all there games
 */
public enum DataManager {
    INSTANCE;

    /**
     * The String that represent the current game name.
     * <p>
     * Precondition: currentGameName should be one of the "ST", "CM" and "FTW"
     */
    private String currentGameName;

    /**
     * The String that represent the current user name.
     */
    private String currentUserName;

    /**
     * The boardManager of the game
     */
    private SuperManager boardManager;


    /**
     * Return the current game name
     *
     * @return the current game name
     */
    public String getCurrentGameName() {
        return currentGameName;
    }

    /**
     * Set the this.currentGameName to the given game name
     *
     * @param currentGameName the given current game name
     */
    public void setCurrentGameName(String currentGameName) {
        this.currentGameName = currentGameName;
    }

    /**
     * Return the name of the current user
     *
     * @return current user name
     */
    public String getCurrentUserName() {
        return currentUserName;
    }

    /**
     * Set this.currentUserName to the given game name
     *
     * @param currentUserName the given game name
     */
    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    /**
     * Return the boardManager of the game
     *
     * @return the boardManager of the game
     */
    public SuperManager getBoardManager() {
        return boardManager;
    }

    /**
     * Set this.boardManager to the given boardManager
     *
     * @param boardManager the given boardManager
     */
    public void setBoardManager(SuperManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * This will create a new boardManager according to the currentGameName
     */
    public void startNewGame(int complexity) {
        ManagerFactory managerFactory = new ManagerFactory();
        this.boardManager = managerFactory.getManager(currentGameName, complexity);
    }
}
