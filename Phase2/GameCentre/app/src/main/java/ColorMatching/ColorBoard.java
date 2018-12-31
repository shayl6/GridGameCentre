package ColorMatching;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import Basic.SuperBoard;

/**
 * This is a ColorBoard that will create a ColorBoard object
 */
public class ColorBoard extends SuperBoard implements Iterable<ColorTile>, Serializable {

    /**
     * The color tiles on the board in row-major order.
     */
    private ColorTile[][] tiles;

    /**
     * The total number of rows of this ColorBoard.
     */
    private int rowNum;

    /**
     * The total number of columns of this ColorBoard.
     */
    private int colNum;

    /**
     * The row ratio of this ColorBoard.
     */
    private static final int rowRatio = 4;

    /**
     * The column ratio of this ColorBoard.
     */
    private static final int colRatio = 5;

    /**
     * A new ColorBoard of tiles in row-major order.
     * Precondition: rowNum * 5 = colNum * 4
     *
     * @param tiles      the tiles for the ColorBoard
     * @param complexity the complexity for the ColorBoard
     */
    ColorBoard(List<ColorTile> tiles, int complexity) {
        super(complexity);
        Iterator<ColorTile> iterator = tiles.iterator();
        this.rowNum = (getComplexity() - 2) * rowRatio;
        this.colNum = (getComplexity() - 2) * colRatio;

        this.tiles = new ColorTile[this.rowNum][this.colNum];
        for (int row = 0; row != this.rowNum; row++) {
            for (int col = 0; col != this.colNum; col++) {
                this.tiles[row][col] = iterator.next();
            }
        }
    }

    /**
     * Return the color tiles of this ColorBoard.
     */
    public ColorTile[][] getTiles() {
        return tiles;
    }

    /**
     * Return the number of tiles on the ColorBoard.
     *
     * @return the number of tiles on the ColorBoard.
     */
    @Override
    public int numGrids() {
        return this.rowNum * this.colNum;
    }

    /**
     * Return the color tile at (row, col)
     *
     * @param row the color tile row
     * @param col the color tile column
     * @return the color tile at (row, col)
     */
    public ColorTile getGrid(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Return the neighbour of a ColorTile tile.
     *
     * @param tile      a color tile
     * @param direction the direction of the ColorTile tile's neighbour.
     * @return a ColorTile if there exist a ColorTile on the direction of the ColorTile tile, else return null.
     */
    ColorTile getNeighbour(ColorTile tile, String direction) {
        switch (direction) {
            case "top":
                if (tile.getY() - 1 >= 0) {
                    return getGrid(tile.getX(), tile.getY() - 1);
                }
                return null;
            case "bottom":
                if (tile.getY() + 1 < tiles.length * 5 / 4) {
                    return getGrid(tile.getX(), tile.getY() + 1);
                }
                return null;
            case "right":
                if (tile.getX() + 1 < tiles.length) {
                    return getGrid(tile.getX() + 1, tile.getY());
                }
                return null;
            case "left":
                if (tile.getX() - 1 >= 0) {
                    return getGrid(tile.getX() - 1, tile.getY());
                }
                return null;

        }
        return null;
    }

    @Override
    public String toString() {
        return "ColorBoard{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Return a new ColorBoardIterator.
     *
     * @return a new ColorBoardIterator
     */
    @NonNull
    @Override
    public Iterator<ColorTile> iterator() {

        return new ColorBoardIterator();
    }

    /**
     * Iterate over color tiles in a range of total number of tiles.
     */
    private class ColorBoardIterator implements Iterator<ColorTile> {

        /**
         * The row number of the color tile.
         */
        private int row;
        /**
         * The column number of the color tile.
         */
        private int col;

        @Override
        public boolean hasNext() {
            return row < ColorBoard.this.rowNum &&
                    col < ColorBoard.this.colNum;
        }

        @Override
        public ColorTile next() {
            if (hasNext()) {
                if (ColorBoard.this.colNum - 1 == col) {
                    ColorTile temp = getGrid(row, col);
                    row++;
                    col = 0;
                    return temp;
                }
                return getGrid(row, col++);
            }
            throw new NoSuchElementException();
        }
    }

    /**
     * Return the total number of columns of this ColorBoard.
     *
     * @return the total number of columns of this ColorBoard.
     */
    int getColNum() {
        return this.colNum;
    }

    /**
     * Return the total number of rows of this ColorBoard.
     *
     * @return the total number of rows of this ColorBoard.
     */
    int getRowNum() {
        return this.rowNum;
    }
}
