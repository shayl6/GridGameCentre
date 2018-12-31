package Scores;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Basic.DataManager;
import fall2018.csc2017.slidingtiles.BoardManager;

import static org.junit.Assert.*;

public class ScoreBoardTest {

    /**
     * An instance of ScoreBoard for testing
     */
    private ScoreBoard scoreBoard;

    /**
     * set up the environment for testing
     */
    @Before
    public void setUp() {
        this.scoreBoard = new ScoreBoard();


        // Add some record with complexity 3
        DataManager.INSTANCE.setCurrentUserName("@u1");
        DataManager.INSTANCE.setCurrentGameName("CM");
        DataManager.INSTANCE.startNewGame(5);
        DataManager.INSTANCE.setCurrentGameName("ST");
        DataManager.INSTANCE.setBoardManager(new BoardManager(3));
        DataManager.INSTANCE.getBoardManager().addScoreBy(1);
        this.scoreBoard.addNewRecords(new Record());

        this.scoreBoard.addNewRecords(new Record(3, 10, "@u2", "ST"));
        this.scoreBoard.addNewRecords(new Record(3, 25, "@u3", "ST"));
        this.scoreBoard.addNewRecords(new Record(4, 5, "@u1", "ST"));
        this.scoreBoard.addNewRecords(new Record(3, 15, "@u3", "ST"));
    }

    /**
     * clean up the environment after testing
     */
    @After
    public void tearDown() {
        this.scoreBoard = null;
    }

    /**
     * This is a test for SoreBoard.topFiveToString()
     */
    @Test
    public void testTopFiveToString() {
        // Complexity 5 -> no record
        this.scoreBoard.setComplexity(5);
        List result = this.scoreBoard.TopFiveToString();
        int count = 0;
        for (Object message : result) {
            assertEquals("Not enough users to get a full rank", message);
            count++;
        }
        assertEquals(5, count);


        // Complexity 3 -> should have 3 records
        this.scoreBoard.setComplexity(3);
        List result3 = this.scoreBoard.TopFiveToString();
        List<String> expected = new ArrayList<>();
        expected.add("User: @u1, got 1 steps in game: ST, in level: 1");
        expected.add("User: @u2, got 10 steps in game: ST, in level: 1");
        expected.add("User: @u3, got 15 steps in game: ST, in level: 1");
        expected.add("Not enough users to get a full rank");
        expected.add("Not enough users to get a full rank");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result3.get(i));
        }
    }

    /**
     * This is a test for SoreBoard.getMyBestRank()
     */
    @Test
    public void testGetMyBestRank() {
        int result1 = this.scoreBoard.getMyBestRank(new Record(4, 5, "@u1", "ST"));
        assertEquals(1, result1);
        int result2 = this.scoreBoard.getMyBestRank(new Record(3, 15, "@u3", "ST"));
        assertEquals(3, result2);

    }
}