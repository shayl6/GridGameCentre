package Scores;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.Serializable;

import Basic.DataManager;

public class Record implements Comparable<Record>, Serializable {


    /**
     * The complexity of the game.
     */
    private int complexity;


    /**
     * The current score of the game.
     */
    private int finalScore;


    /**
     * The current username of the game.
     */
    private String userName;

    /**
     * The current name of the game.
     */
    private String gameName;

    /**
     * Create an instance of Record
     */
    Record() {
        this.complexity = DataManager.INSTANCE.getBoardManager().getComplexity();
        this.finalScore = DataManager.INSTANCE.getBoardManager().getScore();
        this.userName = DataManager.INSTANCE.getCurrentUserName();
        this.gameName = DataManager.INSTANCE.getCurrentGameName();
    }

    /**
     * The constructor for testing
     *
     * @param complexity the complexity of the finished game
     * @param finalScore the finalScore that user got
     * @param userName   the name of the user
     * @param gameName   the current game name
     */
    Record(int complexity, int finalScore, String userName, String gameName) {
        this.complexity = complexity;
        this.finalScore = finalScore;
        this.userName = userName;
        this.gameName = gameName;
    }


    /**
     * Return a string type of record.
     *
     * @return a string type of record.
     */
    @SuppressLint("DefaultLocale")
    String recordToString() {
        String temp = "User: %s, got %d steps in game: %s, in level: %d";
        return String.format(temp, userName, finalScore, gameName, complexity - 2);
    }


    /**
     * Return username
     *
     * @return username
     */
    String getUserName() {
        return userName;
    }


    @Override
    public int compareTo(@NonNull Record anotherRecord) {
        return (this.finalScore - anotherRecord.finalScore);
    }


    /**
     * Return complexity
     *
     * @return complexity
     */
    int getComplexity() {
        return this.complexity;
    }


    /**
     * Return true if r has lower final score
     *
     * @param r is a record of the game.
     * @return true if r has lower final score
     */
    boolean checkLowerScore(Record r) {
        return this.finalScore >= r.finalScore;
    }


}