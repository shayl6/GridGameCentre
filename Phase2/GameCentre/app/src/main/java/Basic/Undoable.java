package Basic;

/**
 * Implementing this interface allows the game to have undo feature.
 */
public interface Undoable {


    /**
     * Undo the previous move
     */
    void undo();


    /**
     * Return true if the undo function is available, false otherwise.
     *
     * @return true if the undo function is available
     */
    boolean undoAvailable();
}
