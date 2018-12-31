package FlipToWin;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import Basic.SuperBoard;


/**
 * The sliding tiles board.
 */

public class FlipToWinBoard extends SuperBoard implements Iterable<FlipToWinTile>, Serializable {

    /**
     * The number of row
     */
    private int rowNum;

    /**
     * The number of col
     */
    private int colNum;


    /**
     * The tiles on the board in row-major order.
     */
    private FlipToWinTile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == rowNum * colNum
     *
     * @param tiles the tiles for the board
     */
    FlipToWinBoard(List<FlipToWinTile> tiles, int complexity) {
        super(complexity);
        Iterator<FlipToWinTile> iterator = tiles.iterator();
        this.rowNum = getComplexity();
        this.colNum = getComplexity() + 1;
        this.tiles = new FlipToWinTile[this.rowNum][this.colNum];

        for (int row = 0; row != this.rowNum; row++) {
            for (int col = 0; col != this.colNum; col++) {
                this.tiles[row][col] = iterator.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numGrids() {

        return this.rowNum * this.colNum;
    }

    /**
     * return the number of the col number of the board.
     *
     * @return the number of the col number of the board.
     */
    int getColNum() {
        return this.colNum;
    }

    /**
     * return the number of the row number of the board.
     *
     * @return the number of the row number of the board.
     */
    int getRowNum() {
        return this.rowNum;
    }


    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public FlipToWinTile getGrid(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Flip the tile
     */
    void makeMove(int row, int col) {
        tiles[row][col].setFlipped();
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        List<Integer> ids = new ArrayList<>();
        for (FlipToWinTile[] tileList : tiles) {
            for (FlipToWinTile tile : tileList) {
                ids.add(tile.getId());
            }
        }
        return "FlipToWinBoard{" +
                "tiles=" + ids +
                '}';
    }

    /**
     * Return a new FlipToWinBoardIterator.
     *
     * @return a new FlipToWinBoardIterator
     */
    @NonNull
    @Override
    public Iterator<FlipToWinTile> iterator() {

        return new FlipToWinBoardIterator();
    }

    /**
     * Iterate over tiles in a range of total number of tiles.
     */
    private class FlipToWinBoardIterator implements Iterator<FlipToWinTile> {

        /**
         * The row number of the tile.
         */
        private int row;

        /**
         * The column number of the tile.
         */
        private int col;

        @Override
        public boolean hasNext() {
            return row < FlipToWinBoard.this.rowNum &&
                    col < FlipToWinBoard.this.colNum;
        }

        @Override
        public FlipToWinTile next() {
            if (hasNext()) {
                if (FlipToWinBoard.this.colNum - 1 == col) {
                    FlipToWinTile temp = getGrid(row, col);
                    row++;
                    col = 0;
                    return temp;
                }
                return getGrid(row, col++);
            }
            throw new NoSuchElementException();
        }
    }
}
