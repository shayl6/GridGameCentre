package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Basic.CustomAdapter;
import Basic.DataManager;
import Basic.FileManager;
import Basic.StartingActivity;

/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The buttons to display.
     */
    private List<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTileButtons(this);
        setContentView(R.layout.activity_main);
        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(DataManager.INSTANCE.getBoardManager().getComplexity());
        DataManager.INSTANCE.getBoardManager().getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / DataManager.INSTANCE.getBoardManager().getComplexity();
                        columnHeight = displayHeight / DataManager.INSTANCE.getBoardManager().getComplexity();

                        display();
                    }
                });
        addSaveGameButtonListener();
        addUndoButtonListener();
        addScoreTextViewListener();
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = (Board) DataManager.INSTANCE.getBoardManager().getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row < DataManager.INSTANCE.getBoardManager().getComplexity(); row++) {
            for (int col = 0; col < DataManager.INSTANCE.getBoardManager().getComplexity(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getGrid(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / DataManager.INSTANCE.getBoardManager().getComplexity();
            int col = nextPos % DataManager.INSTANCE.getBoardManager().getComplexity();
            b.setBackgroundResource(((Board) DataManager.INSTANCE.getBoardManager().getBoard()).getGrid(row, col).getBackground());
            nextPos++;
        }
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

    @Override
    public void update(Observable o, Object arg) {
        display();
        addScoreTextViewListener();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
    }

    /**
     * GetScore of the state.
     */
    void addScoreTextViewListener() {
        TextView currScoreTextView = findViewById(R.id.currScoreText);
        String score = Integer.toString(DataManager.INSTANCE.getBoardManager().getScore());
        currScoreTextView.setText(score);
    }

    /**
     * Activate SaveGame button.
     */
    private void addSaveGameButtonListener() {
        Button save = findViewById(R.id.saveGameButton);
        save.setOnClickListener(v -> {
            FileManager.saveGame(GameActivity.this.getApplicationContext(), "Save");
            FileManager.saveGame(GameActivity.this.getApplicationContext(), "Auto");
            makeToastSavedText();
        });
    }

    /**
     * Activate Undo button.
     */
    private void addUndoButtonListener() {
        Button undo = findViewById(R.id.undoButton);
        undo.setOnClickListener((v) -> {
            if (((BoardManager) DataManager.INSTANCE.getBoardManager()).undoAvailable()) {
                ((BoardManager) DataManager.INSTANCE.getBoardManager()).undo();
                makeToastUndoSuccessText();
            } else {
                makeToastUndoFailText();
            }
            FileManager.saveGame(this.getApplicationContext(), "Auto");
            addScoreTextViewListener();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
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

}
