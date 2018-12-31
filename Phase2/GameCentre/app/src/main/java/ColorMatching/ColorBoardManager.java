package ColorMatching;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Basic.SuperManager;
import Basic.Undoable;

/**
 * This is a ColorBoardManager that will manage the ColorBoard
 */
public class ColorBoardManager extends SuperManager implements Serializable, Undoable {

    /**
     * The ColorBoard being managed.
     */
    private ColorBoard colorBoard;

    /**
     * The List of List of all color tiles being changed.
     */
    private List<List<ColorTile>> allMove;

    /**
     * The List of all colors being set before.
     */
    private List<Integer> colors;

    /**
     * A temporary List used for record which color tiles has been set colors for each time doing color change.
     */
    private List<ColorTile> allState;

    /**
     * The row ratio of this ColorBoard.
     */
    private static final int ROW_RATIO = 4;

    /**
     * The column ratio of this ColorBoard.
     */
    private static final int COL_RATIO = 5;

    /**
     * A new ColorBoardManager with complexity.
     *
     * @param complexity the complexity for the ColorMatching Game.
     */
    public ColorBoardManager(int complexity) {
        super(complexity);
        List<ColorTile> tiles = new ArrayList<>();
        int rowNum = (getComplexity() - 2) * ROW_RATIO;
        int colNum = (getComplexity() - 2) * COL_RATIO;

        for (int row = 0; row != rowNum; row++) {
            for (int col = 0; col != colNum; col++) {
                ColorTile colorTiles = new ColorTile(row, col);
                colorTiles.setColor(colorTiles.randomColor());
                tiles.add(colorTiles);
            }
        }
        this.colorBoard = new ColorBoard(tiles, complexity);
        allMove = new ArrayList<>();
        colors = new ArrayList<>();
        allState = new ArrayList<>();
    }

    /**
     * Add color tiles which are the neighbour at specific direction of the specific ColorTile
     * tile and have color initColor.
     *
     * @param tile      the ColorTile whose neighbours are being checked.
     * @param initColor the Color which are the same as the color of ColorTile(0, 0)
     * @param direction the direction of neighbour being checked.
     */
    private void helpFindNeighbour(List arr, ColorTile tile, int initColor, String direction) {
        if (colorBoard.getNeighbour(tile, direction) != null
                && Objects.requireNonNull(colorBoard.getNeighbour(tile, direction)).getColor() ==
                initColor && !allState.contains(colorBoard.getNeighbour(tile, direction)) &&
                !arr.contains(colorBoard.getNeighbour(tile, direction))) {
            allState.add(colorBoard.getNeighbour(tile, direction));
        }
    }

    /**
     * Add color tiles which are the neighbour at all directions of the specific ColorTile
     * tile and have color initColor.
     *
     * @param tile      the ColorTile whose neighbours are being checked.
     * @param initColor the Color which are the same as the color of ColorTile(0, 0)
     */
    private void neighbour(List arr, ColorTile tile, int initColor) {
        helpFindNeighbour(arr, tile, initColor, "top");
        helpFindNeighbour(arr, tile, initColor, "bottom");
        helpFindNeighbour(arr, tile, initColor, "right");
        helpFindNeighbour(arr, tile, initColor, "left");
    }


    /**
     * Change the ColorTiles lined with the ColorTile at (0, 0) with the same color to the new color
     * newColor.
     *
     * @param newColor the newColor to be set to eligible ColorTiles.
     */
    @Override
    public void makeChange(int newColor) {
        allState = new ArrayList<>();
        List<ColorTile> arr = new ArrayList<>();
        ColorTile tile = colorBoard.getGrid(0, 0);
        int initColor = tile.getColor();
        // if newColor is the different as the color of (0,0) colorTile
        if (newColor != initColor) {
            tile.setColor(newColor);
            arr.add(tile);
            neighbour(arr, tile, initColor);
            //if any of tile's neighbours has the same color, then continue on checking the neighbours
            //of the current neighbours.
            while (allState.size() != 0) {
                tile = allState.get(0);
                neighbour(arr, tile, initColor);
                tile.setColor(newColor);
                arr.add(tile);
                allState.remove(0);
            }
            allMove.add(arr);
            colors.add(initColor);
        }
        // if newColor is the same as the color of (0,0) colorTile
        else {
            arr.add(tile);
            neighbour(arr, tile, initColor);
            allMove.add(arr);
            colors.add(initColor);
        }
        addScoreBy(1);
    }

    /**
     * Return true if the undo function is available, false otherwise.
     *
     * @return true if the undo function is available
     */
    public boolean undoAvailable() {
        return (this.allMove.size() >= 1);
    }

    /**
     * Undo the previous move
     */
    public void undo() {
        if (undoAvailable()) {
            List<ColorTile> whatever = allMove.remove(allMove.size() - 1);
            int color = colors.remove(colors.size() - 1);
            for (ColorTile cur : whatever) {
                cur.setColor(color);
            }
        }
        addScoreBy(2);
    }

    /**
     * Return the current ColorBoard colorBoard.
     *
     * @return the current ColorBoard.
     */
    public ColorBoard getBoard() {
        return colorBoard;
    }

    /**
     * Set colorBoard to colorBoard.
     *
     * @param colorBoard the colorBoard of this colorBoardManager.
     */
    void setColorBoard(ColorBoard colorBoard) {
        this.colorBoard = colorBoard;
    }

    /**
     * Return true if all the color tiles in this ColorBoard have the same color, else return False.
     *
     * @return if all the color tiles in this ColorBoard have the same color.
     */
    @Override
    public boolean puzzleSolved() {
        boolean result = true;
        for (int x = 0; x < colorBoard.getTiles().length; x++) {
            for (int y = 0; y < colorBoard.getTiles()[x].length; y++) {
                if (colorBoard.getGrid(x, y).getColor() != colorBoard.getGrid(0, 0).getColor()) {
                    result = false;
                }
            }
        }
        return result;
    }
}
