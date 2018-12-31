package ColorMatching;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.io.Serializable;

/**
 * This is a ColorView class that is responsible for drawing view
 */
class ColorView implements Serializable {

    /**
     * The view of this game.
     */
    private View view;

    /**
     * The size of each color tile on canvas.
     */
    private int boxSize;

    /**
     * The ColorBoardManager colorBoardManager.
     */
    private ColorBoardManager colorBoardManager;

    /**
     * The constructor of ColorBoardManager with a non-set complexity 5.
     */
    ColorView() {
        colorBoardManager = new ColorBoardManager(5);
    }

    /**
     * Get the view of this game.
     *
     * @return view of the game
     */
    public View getView() {
        return view;
    }

    /**
     * Set the view of the game
     *
     * @param view the new view of the game.
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Draw color tiles at (x, y) with color on canvas.
     *
     * @param canvas the canvas of this game
     * @param color  the color of the color tile going to be draw
     * @param x      the x position of the color tile going to be draw
     * @param y      the y position of the color tile going to be draw
     */
    void drawBox(Canvas canvas, int color, int x, int y) {
        Paint boxPaint = new Paint();
        boxPaint.setAntiAlias(true);

        //draw box
        boxPaint.setColor(color);
        canvas.drawRect(x * boxSize, y * boxSize, x * boxSize + boxSize, y * boxSize + boxSize, boxPaint);
    }

    /**
     * Draw lines on the canvas.
     *
     * @param canvas the canvas of this game
     */
    void drawLine(Canvas canvas) {
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);

        for (int x = 0; x < colorBoardManager.getBoard().getTiles().length; x++) {
            canvas.drawLine(x * boxSize, 0, x * boxSize, view.getHeight(), linePaint);
        }
        for (int y = 0; y < colorBoardManager.getBoard().getTiles()[0].length; y++) {
            canvas.drawLine(0, y * boxSize, view.getWidth(), y * boxSize, linePaint);
        }
    }

    /**
     * Set the size of each color tile.
     *
     * @param size the size of each color tile.
     */
    void setBoxSize(int size) {
        boxSize = size;
    }
}
