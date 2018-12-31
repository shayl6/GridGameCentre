package ColorMatching;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import Basic.DataManager;
import Basic.FileManager;
import Scores.ScoreBoardActivity;
import Basic.StartingActivity;
import fall2018.csc2017.slidingtiles.R;

/**
 * This class is responsible for the game page of ColorMatching
 */
public class ColorMatchingGameActivity extends AppCompatActivity {

    /**
     * The width and height of the game board.
     */
    private int width, height;

    /**
     * The view of this ColorView.
     */
    @SuppressLint("StaticFieldLeak")
    private ColorView colorView;

    /**
     * Total number of Button.
     */
    private static final int numButton = 5;

    /**
     * The row ratio of this ColorBoard.
     */
    private static final int rowRatio = 4;

    /**
     * The column ratio of this ColorBoard.
     */
    private static final int colRatio = 5;

    /**
     * The color buttons to display.
     */
    private int[] color_button = {R.id.red, R.id.yellow, R.id.blue, R.id.green, R.id.grey};

    /**
     * The colors to display.
     */
    private int[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY};

    /**
     * Return the view of this ColorView.
     *
     * @return the view of this ColorView.
     */
    public ColorView getColorView() {
        return colorView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching_game);
        initData();
        initView();
        int position = 0;
        while (position < numButton) {
            addColorButtonListener(position);
            position++;
        }
        addUndoButtonListener();
        addSaveGameButtonListener();
    }

    /**
     * Check if the game is win, this game will win when all the tiles has the same color on the
     * ColorBoard.
     */
    private void checkWin() {
        if (DataManager.INSTANCE.getBoardManager().puzzleSolved()) {
            Toast.makeText(this, "YOU WIN!", Toast.LENGTH_SHORT).show();
            Intent temp = new Intent(this, ScoreBoardActivity.class);
            startActivity(temp);
        }
    }

    /**
     * Set the view for this game, and draw lines and color tiles on the canvas.
     */
    private void draw() {
        colorView.setView(new View(this) {
            protected void onDraw(Canvas canvas) {
                setUpTiles(canvas);
                colorView.drawLine(canvas);
            }
        });
        colorView.getView().setLayoutParams(new FrameLayout.LayoutParams(width, height));
        colorView.getView().setBackgroundColor(0x10000000);
        addScoreTextViewListener();
    }

    /**
     * Set tiles' features for this game on the ColorBoard, and draw this tiles on canvas.
     *
     * @param canvas The canvas for this game.
     */
    void setUpTiles(Canvas canvas) {
        ColorBoard board = (ColorBoard) DataManager.INSTANCE.getBoardManager().getBoard();
        for (int row = 0; row < board.getRowNum(); row++) {
            for (int col = 0; col < board.getColNum(); col++) {
                int color = board.getGrid(row, col).getColor();
                getColorView().drawBox(canvas, color, row, col);
            }
        }
    }

    /**
     * Initialize some data features of this game, including the width and height for the layout,
     * complexity and tiles' size on canvas.
     */
    private void initData() {
        int width = getScreenWidth(this);
        this.width = width;
        height = width * colRatio / rowRatio;
        colorView = new ColorView();
        colorView.setBoxSize(this.width / ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard().getTiles().length);
    }

    /**
     * Initialize view for this game, draw lines and color tiles, and add them to this layout.
     */
    private void initView() {
        FrameLayout layoutGame = findViewById(R.id.layoutGame);
        draw();
        layoutGame.addView(colorView.getView());
    }

    /**
     * Adapted from:
     * https://stackoverflow.com/questions/4743116/get-screen-width-and-height
     * This method is used to get the screen's width.
     */
    private int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    /**
     * Activate Undo button.
     */
    private void addUndoButtonListener() {
        Button undo = findViewById(R.id.undo);
        undo.setOnClickListener((v) -> {
            ColorBoardManager boardManager = (ColorBoardManager) DataManager.INSTANCE.getBoardManager();
            if (boardManager.undoAvailable()) {
                boardManager.undo();
                colorView.getView().invalidate();

                makeToastUndoSuccessText();
            } else {
                makeToastUndoFailText();
            }
            FileManager.saveGame(this.getApplicationContext(), "Auto");
            addScoreTextViewListener();
        });
    }

    /**
     * Display that a game was undo successfully.
     */
    private void makeToastUndoSuccessText() {
        Toast.makeText(this, "Undo successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game was failed to undo.
     */
    private void makeToastUndoFailText() {
        Toast.makeText(this, "Undo failed: Cannot undo in this state.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate SaveGame button.
     */
    private void addSaveGameButtonListener() {
        Button save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            FileManager.saveGame(this.getApplicationContext(), "Save");
            FileManager.saveGame(this.getApplicationContext(), "Auto");
            makeToastSavedText();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * GetScore of the state.
     */
    void addScoreTextViewListener() {
        TextView currScoreTextView = findViewById(R.id.color_match_currScoreText);
        String score = "          " + "Score:   " + Integer.toString(DataManager.INSTANCE.getBoardManager().getScore());
        currScoreTextView.setText(score);
    }

    /**
     * Activate Color buttons.
     */
    private void addColorButtonListener(int color) {
        Button colorButton = findViewById(color_button[color]);
        colorButton.setOnClickListener((v) -> {
            DataManager.INSTANCE.getBoardManager().makeChange(colors[color]);
            colorView.getView().invalidate();
            addScoreTextViewListener();
            checkWin();
        });
    }

    @Override
    public void onBackPressed() {
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        FileManager.saveGame(this.getApplicationContext(), "Auto");
    }

    /**
     * Dispatch onStop() to fragments.
     */
    @Override
    protected void onStop() {
        super.onStop();
        FileManager.saveGame(this.getApplicationContext(), "Auto");
    }

}
