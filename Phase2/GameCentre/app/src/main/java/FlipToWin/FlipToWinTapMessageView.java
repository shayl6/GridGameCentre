package FlipToWin;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import Basic.DataManager;
import Scores.ScoreBoardActivity;


class FlipToWinTapMessageView {

    FlipToWinTapMessageView() {
    }

    /**
     * Process a tapMovement, change to ScoreBoardActivity view when the game is won
     */
    void processTapMovement(Context context, int position, boolean display) {

        FlipToWinBoardManager boardManager = (FlipToWinBoardManager) DataManager.INSTANCE.getBoardManager();
        int row = position / ((FlipToWinBoard) boardManager.getBoard()).getColNum();
        int col = position % ((FlipToWinBoard) boardManager.getBoard()).getColNum();


        // if position is a valid tap
        if (boardManager.isValidTap(position)) {
            boardManager.makeChange(position);

            // if the curr tile is paired
            if (((FlipToWinTile) boardManager.getBoard().getGrid(row, col)).isPaired()) {
                Toast.makeText(context, "Correct Decision !", Toast.LENGTH_SHORT).show();
            }

            // if game is over
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                Intent temp = new Intent(context, ScoreBoardActivity.class);
                temp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(temp);
            }
        }
        // if not valid tap and the tile on the position is paired.
        else if (((FlipToWinBoard) boardManager.getBoard()).getGrid(row, col).isPaired()) {
            Toast.makeText(context, "Already Solved", Toast.LENGTH_SHORT).show();
        }
        // if not valid tap and the tile is flipping.
        else if (boardManager.isFlippingTiles()) {
            Toast.makeText(context, "Flipping cards Right Now", Toast.LENGTH_SHORT).show();
        }
        // if not valid tap and the tile on the position is faced up.
        else {
            Toast.makeText(context, "Already FaceUp", Toast.LENGTH_SHORT).show();
        }
    }

}