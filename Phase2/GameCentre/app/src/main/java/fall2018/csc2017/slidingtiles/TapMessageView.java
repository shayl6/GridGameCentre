package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import Basic.DataManager;
import Scores.ScoreBoardActivity;


class TapMessageView {

    TapMessageView() {
    }

    /**
     * Process a tapMovement, change to ScoreBoardActivity view when the game is won
     */
    void processTapMovement(Context context, int position, boolean display) {
        if (((BoardManager) DataManager.INSTANCE.getBoardManager()).isValidTap(position)) {
            DataManager.INSTANCE.getBoardManager().makeChange(position);
            if (DataManager.INSTANCE.getBoardManager().puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                Intent temp = new Intent(context, ScoreBoardActivity.class);
                temp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(temp);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
