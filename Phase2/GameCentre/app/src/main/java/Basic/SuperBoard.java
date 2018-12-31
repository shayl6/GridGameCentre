package Basic;

import java.io.Serializable;
import java.util.Observable;

/**
 * This is a super class for all board
 *
 * @param <T> it could be the Tile class for one of the there game
 */
public abstract class SuperBoard<T> extends Observable implements Serializable {

    /**
     * the complexity of the board.
     */
    private int complexity;

    /**
     * A new Super board is created.
     *
     * @param complexity the complexity of the game.
     */
    public SuperBoard(int complexity) {
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
     * an abstract method, return the num of the grids in board.
     *
     * @return the num of the grids in board.
     */
    public abstract int numGrids();

    /**
     * an abstract method, return the grid at position (ror, col) in row-col major.
     *
     * @param row the row fo the grid.
     * @param col the col of the grid.
     * @return the grid at position (ror, col) in row-col major.
     */
    public abstract T getGrid(int row, int col);
}
