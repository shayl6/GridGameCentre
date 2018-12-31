package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;

import Basic.MovableTile;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile extends MovableTile implements Comparable<Tile>, Serializable {

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background id
     * @param complexity   the complexity of the game
     */
    Tile(int backgroundId, int complexity) {
        super(backgroundId + 1);
        switch (backgroundId + 1) {
            case 1:
                super.setBackground(R.drawable.tile_1);
                break;
            case 2:
                super.setBackground(R.drawable.tile_2);
                break;
            case 3:
                super.setBackground(R.drawable.tile_3);
                break;
            case 4:
                super.setBackground(R.drawable.tile_4);
                break;
            case 5:
                super.setBackground(R.drawable.tile_5);
                break;
            case 6:
                super.setBackground(R.drawable.tile_6);
                break;
            case 7:
                super.setBackground(R.drawable.tile_7);
                break;
            case 8:
                super.setBackground(R.drawable.tile_8);
                break;
            case 9:
                if (complexity == 3) {
                    super.setBackground(R.drawable.tile_0);
                } else {
                    super.setBackground(R.drawable.tile_9);
                }
                break;
            case 10:
                super.setBackground(R.drawable.tile_10);
                break;
            case 11:
                super.setBackground(R.drawable.tile_11);
                break;
            case 12:
                super.setBackground(R.drawable.tile_12);
                break;
            case 13:
                super.setBackground(R.drawable.tile_13);
                break;
            case 14:
                super.setBackground(R.drawable.tile_14);
                break;
            case 15:
                super.setBackground(R.drawable.tile_15);
                break;
            case 16:
                if (complexity == 4) {
                    super.setBackground(R.drawable.tile_0);
                } else {
                    super.setBackground(R.drawable.tile_16);
                }
                break;
            case 17:
                super.setBackground(R.drawable.tile_17);
                break;
            case 18:
                super.setBackground(R.drawable.tile_18);
                break;
            case 19:
                super.setBackground(R.drawable.tile_19);
                break;
            case 20:
                super.setBackground(R.drawable.tile_20);
                break;
            case 21:
                super.setBackground(R.drawable.tile_21);
                break;
            case 22:
                super.setBackground(R.drawable.tile_22);
                break;
            case 23:
                super.setBackground(R.drawable.tile_23);
                break;
            case 24:
                super.setBackground(R.drawable.tile_24);
                break;
            case 25:
                super.setBackground(R.drawable.tile_0);
                break;
        }
    }

    @Override
    public int compareTo(@NonNull Tile o) {
        return o.getId() - this.getId();
    }
}
