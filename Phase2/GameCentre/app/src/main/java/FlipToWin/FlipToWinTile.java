package FlipToWin;

import java.io.Serializable;

import Basic.MovableTile;
import fall2018.csc2017.slidingtiles.R;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class FlipToWinTile extends MovableTile implements Serializable {


    /**
     * faceUp is true iff the tile is faced up
     */
    private boolean facedUp = false;

    /**
     * faceUp is true iff the tile is paired.
     */
    private boolean paired = false;

    /**
     * The background id to find the tile background.
     */
    private int backGround;

    /**
     * the string that is shown on the front page of a tile.
     */
    private String frontPage;

    /**
     * A tile with a background id and set the id.
     *
     * @param num num + 1 is the id of tile.
     */
    FlipToWinTile(int num) {
        super(num + 1);
        this.backGround = R.drawable.back_of_tile4;

        // the front-page array is an array of the strings that is shown on the front page of tiles
        String frontPage[] = {"🐶", "🐻", "🌝", "🌚", "🍑", "🐱", "❤️", "🍭️",
                "💻", "💊", "🚗", "🗿", "🍗", "🍩", "🍺"};

        this.frontPage = frontPage[getId() - 1];
    }

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return backGround;
    }

    /**
     * Return the front page string
     *
     * @return the front page string
     */
    String getFrontPage() {
        return this.frontPage;
    }

    /**
     * flip the tile.
     */
    void setFlipped() {
        this.facedUp = !(this.facedUp);
    }

    /**
     * return true iff tile is faced up
     *
     * @return true iff tile is faced up
     */
    boolean isFacedUp() {
        return facedUp;
    }

    /**
     * set the tile to be paired.
     */
    void setPaired() {
        this.paired = true;
    }


    /**
     * return true iff the tile is paired
     *
     * @return true iff the tile is paired
     */
    boolean isPaired() {
        return (this.paired);
    }
}
