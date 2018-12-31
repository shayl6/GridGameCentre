package ColorMatching;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

/**
 * This class create a ColorTile object
 */
public class ColorTile implements Serializable {

    /**
     * The x position of ColorTile.
     */
    private int x;

    /**
     * The y position of ColorTile.
     */
    private int y;

    /**
     * The color of the current Tile.
     */
    private int currColor;

    /**
     * A tile with a x position and a y position.
     *
     * @param x the x position of Tile.
     * @param y the y position of Tile.
     */
    ColorTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x position of ColorTile.
     *
     * @return x, which is the x position of ColorTile.
     */
    int getX() {
        return x;
    }

    /**
     * Get the y position of ColorTile.
     *
     * @return y, which is the y position of ColorTile.
     */
    int getY() {
        return y;
    }

    /**
     * Get the current Tile's color.
     */
    public int getColor() {
        return this.currColor;
    }

    /**
     * Set the current Tile's color color.
     *
     * @param color the color to be set to the current Tile.
     */
    public void setColor(int color) {
        this.currColor = color;
    }

    /**
     * Set the current Tile's color to a random color from 5 colors.
     */
    int randomColor() {
        Random random = new Random();
        int color = random.nextInt(5);
        switch (color) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.GRAY;
                break;
        }
        return color;
    }
}
