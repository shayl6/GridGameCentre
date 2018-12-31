package Basic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import ColorMatching.ColorMatchingGameActivity;
import FlipToWin.FlipToWinGameActivity;
import Scores.ScoreBoardActivity;
import fall2018.csc2017.slidingtiles.GameActivity;
import fall2018.csc2017.slidingtiles.R;

/**
 * This class is reasonable for the complexity select page
 */
public class ComplexityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexity);
        setupComplexityButtonListener(R.id.complexity1Button, Color.GREEN, 3);
        setupComplexityButtonListener(R.id.complexity2Button, Color.YELLOW, 4);
        setupComplexityButtonListener(R.id.complexity3Button, Color.RED, 5);

    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        TextView tv = findViewById(R.id.complexityTitle);
        tv.setText("Choose A Level!");
        tv.setTextColor(Color.BLACK);
    }


    /**
     * Activate the Level button.
     */
    private void setupComplexityButtonListener(int button, int color, int level) {
        Button complexity1Button = findViewById(button);
        complexity1Button.setBackgroundColor(color);
        complexity1Button.setTextColor(Color.BLACK);
        complexity1Button.setOnClickListener((v) -> setUpGame(level));

    }


    @Override
    public void onBackPressed() {
        Intent temp = new Intent(this, StartingActivity.class);
        finish();
        startActivity(temp);
    }

    /**
     * set up game by the complexity
     *
     * @param complexity the complexity of the game
     */
    private void setUpGame(int complexity) {

        Intent temp;

        Intent preIntent = getIntent();
        Bundle bundle = preIntent.getExtras();
        if (bundle != null) {
            temp = new Intent(this, ScoreBoardActivity.class);
            temp.putExtra("complexity", complexity);
        } else {
            DataManager.INSTANCE.startNewGame(complexity);
            FileManager.saveGame(this.getApplicationContext(), "Auto");
            String gameName = DataManager.INSTANCE.getCurrentGameName();
            if ("ST".equals(gameName)) {
                temp = new Intent(this, GameActivity.class);
            } else if ("CM".equals(gameName)) {
                temp = new Intent(this, ColorMatchingGameActivity.class);
            } else {
                temp = new Intent(this, FlipToWinGameActivity.class);
            }
        }
        startActivity(temp);
        finish();
    }

}
