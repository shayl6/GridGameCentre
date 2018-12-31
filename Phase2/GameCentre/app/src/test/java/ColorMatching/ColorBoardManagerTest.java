package ColorMatching;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test ColorBoardManager class.
 */
public class ColorBoardManagerTest {

    /**
     * The color board manager for testing.
     */
    private ColorBoardManager colorBoardManager;

    /**
     * The complexity of board at level 1
     */
    private int complexity1 = 3;

    /**
     * Make a random colorBoard.
     */
    @Before
    public void setUpCorrect() {
        colorBoardManager = new ColorBoardManager(complexity1);
    }

    /**
     * Make a set of colorTiles that are in order.
     *
     * @return a set of colorTiles that are in order
     */
    private List<ColorTile> makeSameColorTiles() {
        List<ColorTile> tiles = new ArrayList<>();
        int rowNum = (complexity1 - 2) * 4;
        int colNum = (complexity1 - 2) * 5;
        for (int row = 0; row != rowNum; row++) {
            for (int col = 0; col != colNum; col++) {
                ColorTile colorTile = new ColorTile(row, col);
                colorTile.setColor(-65536);
                tiles.add(colorTile);
            }
        }
        return tiles;
    }

    /**
     * Make a solved colorBoard.
     */
    @Before
    public void setUpSameColor() {
        List<ColorTile> tiles = makeSameColorTiles();
        ColorBoard colorBoard = new ColorBoard(tiles, complexity1);
        colorBoardManager = new ColorBoardManager(complexity1);
        colorBoardManager.setColorBoard(colorBoard);
    }


    /**
     * Set the colorBoardManager to null
     */
    @After
    public void tearDown() {
        colorBoardManager = null;
    }

    /**
     * Test whether makeChange does change the color of the top left grid
     */
    @Test
    public void testMakeChange() {
        setUpCorrect();
        colorBoardManager.makeChange(-65536);
        assertEquals(-65536, colorBoardManager.getBoard().getGrid(0, 0).getColor());
        colorBoardManager.makeChange(-16711936);
        assertEquals(-16711936, colorBoardManager.getBoard().getGrid(0, 0).getColor());
        colorBoardManager.makeChange(-16711936);
        assertEquals(-16711936, colorBoardManager.getBoard().getGrid(0, 0).getColor());
    }

    /**
     * Test whether makeChange is correct when the color button chosen has the same color as the
     * ColorTile at (0, 0).
     */
    @Test
    public void testMakeSameColorChange() {
        setUpSameColor();
        String previous = colorBoardManager.getBoard().toString();
        colorBoardManager.makeChange(-65536);
        assertEquals(previous, colorBoardManager.getBoard().toString());
        colorBoardManager.makeChange(-16711936);
        assertEquals(2, colorBoardManager.getScore());
    }

    /**
     * Check whether undoAvailable works after make some changes
     */
    @Test
    public void testUndoAvailable() {
        setUpCorrect();
        assertFalse(colorBoardManager.undoAvailable());
        colorBoardManager.makeChange(-65536);
        colorBoardManager.makeChange(-16711936);
        assertTrue(colorBoardManager.undoAvailable());
    }

    /**
     * Check whether undoAvailable works after make some changes
     */
    @Test
    public void testUndo() {
        setUpCorrect();
        colorBoardManager.makeChange(-65536);
        colorBoardManager.makeChange(-16711936);
        colorBoardManager.undo();
        assertEquals(-65536, colorBoardManager.getBoard().getGrid(0, 0).getColor());
    }

    /**
     * Check whether getBoard works.
     */
    @Test
    public void testGetBoard() {
        setUpCorrect();
        assertEquals(3, colorBoardManager.getBoard().getComplexity());
    }

    /**
     * Check whether the game is not solved after randomly create a colorBoard.
     */
    @Test
    public void testPuzzleUnsolved() {
        setUpCorrect();
        assertFalse(colorBoardManager.puzzleSolved());
    }

    /**
     * Check whether the game is solved after setting all the colorTiles the same color.
     */
    @Test
    public void testPuzzleSolved() {
        setUpSameColor();
        assertTrue(colorBoardManager.puzzleSolved());
    }

    /**
     * Check whether the colorBoard is Iterable
     */
    @Test
    public void testColorBoardIterable() {
        Iterator<ColorTile> iterator = colorBoardManager.getBoard().iterator();
        int i = 0;
        while (i <= colorBoardManager.getBoard().numGrids()) {
            try {
                iterator.next();
            } catch (NoSuchElementException e) {
                System.out.println("No more ColorTiles.");
            }
            i++;
        }
    }

    /**
     * Check whether the colorBoard can getRowNum correctly.
     */
    @Test
    public void testColorBoardGetRowNum() {
        setUpCorrect();
        int expectRowNum = (complexity1 - 2) * 4;
        assertEquals(expectRowNum, colorBoardManager.getBoard().getRowNum());
    }

    /**
     * Check whether the colorBoard can getColNum correctly.
     */
    @Test
    public void testColorBoardGetColNum() {
        setUpCorrect();
        int expectColNum = (complexity1 - 2) * 5;
        assertEquals(expectColNum, colorBoardManager.getBoard().getColNum());
    }


}