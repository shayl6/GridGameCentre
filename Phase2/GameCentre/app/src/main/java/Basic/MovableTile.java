package Basic;

import java.io.Serializable;

/**
 * This is a super class for all Tile class
 */
public abstract class MovableTile implements Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * the id of a tile
     */
    private int id;

    /**
     * construct a new tile
     *
     * @param num the num is the id.
     */
    public MovableTile(int num) {
        this.id = num;
    }

    /**
     * return the id of the tile
     *
     * @return the id of the tile
     */
    public int getId() {
        return this.id;
    }

    /**
     * return the background of the tile
     *
     * @return the background of the tile
     */
    public int getBackground() {
        return background;
    }

    /**
     * set the background of a tile as background
     *
     * @param background the background
     */
    public void setBackground(int background) {
        this.background = background;
    }
}
