package Basic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import Users.LoginActivity;
import fall2018.csc2017.slidingtiles.R;


/**
 * The activity for selecting game.
 */
public class SelectGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        addGameListener(R.id.enter_sliding_button, Color.MAGENTA, Color.YELLOW, "ST");
        addGameListener(R.id.enter_colorMatching_button, Color.YELLOW, Color.BLACK, "CM");
        addGameListener(R.id.enter_flipToTile_button, Color.CYAN, Color.RED, "FTW");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        TextView tv = findViewById(R.id.gameCentre);
        tv.setText("  Game Centre");
        tv.setTextColor(Color.WHITE);
    }


    /**
     * Activate the Game button.
     */
    private void addGameListener(int button, int backgroundColor, int textColor, String game) {
        Button mSliding = findViewById(button);
        mSliding.setBackgroundColor(backgroundColor);
        mSliding.setTextColor(textColor);
        mSliding.setOnClickListener(v -> {
            DataManager.INSTANCE.setCurrentGameName(game);
            Intent slide = new Intent(this, StartingActivity.class);
            startActivity(slide);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, LoginActivity.class);
        startActivity(temp);
    }
}
