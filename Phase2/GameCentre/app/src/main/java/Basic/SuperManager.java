package Basic;

import java.io.Serializable;

/**
 * This is a super class for the boardManager of all three games
 */
public abstract class SuperManager implements Serializable {

    /**
     * the complexity of the game.
     */
    private int complexity;

    /**
     * the score of the game.
     */
    private int score;

    /**
     * construct a SuperManager with given complexity.
     *
     * @param complexity the complexity of the game.
     */
    public SuperManager(int complexity) {
        this.complexity = complexity;
    }

    /**
     * return the complexity of the game.
     *
     * @return the complexity of the game.
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * an abstract method, make change at num.
     *
     * @param num the num
     */
    public abstract void makeChange(int num);

    /**
     * an abstract method, return true iff the game is solved.
     *
     * @return true iff the game is solved.
     */
    public abstract boolean puzzleSolved();

    /**
     * return the score of the score.
     *
     * @return the score of the score.
     */
    public int getScore() {

        return this.score;
    }

    /**
     * add score by num.
     *
     * @param num the num
     */
    public void addScoreBy(int num) {
        this.score += num;
    }

    /**
     * an abstract method, return the board.
     *
     * @return return the board.
     */
    public abstract SuperBoard getBoard();
}
