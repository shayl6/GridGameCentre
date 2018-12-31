package fall2018.csc2017.slidingtiles;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Basic.SuperManager;
import Basic.Undoable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager extends SuperManager implements Serializable, Undoable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * An List that store all the previous moves for undo function.
     */
    private List<int[]> previousMoves;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board      the board
     * @param complexity the complexity of the game
     */
    public BoardManager(Board board, int complexity) {
        super(complexity);
        this.board = board;
    }

    /**
     * Manage a new shuffled board.
     */
    public BoardManager(int complexity) {
        super(complexity);
        List<Tile> tiles = new ArrayList<>();

        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum, complexity));
        }

        Collections.shuffle(tiles);
        while (!checkSolvable(tiles, complexity)) {
            Collections.shuffle(tiles);
        }
        this.board = new Board(tiles, complexity);
        this.previousMoves = new ArrayList<>();
    }

    /**
     * Return whether the tiles are solvable.
     * Adapted from: https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/.
     *
     * @param tiles      the tiles to check
     * @param complexity the complexity of tiles
     * @return whether the tiles are solvable or not.
     */
    boolean checkSolvable(List<Tile> tiles, int complexity) {
        int num = getInvNumber(tiles);
        if (tiles.size() % 2 == 1) {
            return num % 2 == 0;
        } else {
            int blank_pos = findBlank(tiles, complexity);
            if (blank_pos % 2 == 1) {
                return num % 2 == 0;
            } else {
                return num % 2 == 1;
            }
        }

    }


    /**
     * Return the number of rows counting from bottom that the blank is on.
     * Adapted from: https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/.
     *
     * @param tiles      the tiles to check
     * @param complexity the complexity of tiles
     * @return the number of rows counting from bottom that the blank is on.
     */
    private int findBlank(List<Tile> tiles, int complexity) {
        int blank = 0;
        for (int row = complexity - 1; row >= 0; row--) {
            for (int col = complexity - 1; col >= 0; col--) {
                if (tiles.get(row * complexity + col).getId() == tiles.size()) {
                    blank = complexity - row;
                }
            }
        }
        return blank;
    }


    /**
     * Return the number of inversions in tiles.
     * Adapted from: https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/.
     *
     * @param tiles the tiles to check
     * @return the number of inversions in tiles.
     */
    private int getInvNumber(List<Tile> tiles) {
        int sum = 0;
        for (int first = 0; first < tiles.size() - 1; first++) {
            for (int second = first + 1; second < tiles.size(); second++) {

                if (tiles.get(first).getId() > tiles.get(second).getId() &&
                        tiles.get(first).getId() != tiles.size() &&
                        tiles.get(second).getId() != tiles.size()) {
                    sum++;
                }
            }
        }
        return sum;
    }


    /**
     * Return the current board.
     *
     * @return the board.
     */
    public Board getBoard() {

        return board;
    }


    /**
     * Set the board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    public boolean puzzleSolved() {
        Iterator<Tile> iterator = this.board.iterator();
        Tile temp = iterator.next();
        while (iterator.hasNext()) {
            Tile next = iterator.next();
            if (temp.compareTo(next) < 0) {
                return false;
            }
            temp = next;
        }
        return true;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {
        int row = position / board.getComplexity();
        int col = position % board.getComplexity();
        int blankId = board.numGrids();
        // Are any of the 4 the blank tile?

        System.out.println("row: " + row);
        System.out.println("\n");
        System.out.println("col: " + col);
        Tile above = row == 0 ? null : board.getGrid(row - 1, col);
        Tile below = row == board.getComplexity() - 1 ? null : board.getGrid(row + 1, col);
        Tile left = col == 0 ? null : board.getGrid(row, col - 1);
        Tile right = col == board.getComplexity() - 1 ? null : board.getGrid(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    @Override
    public void makeChange(int position) {

        int row = position / board.getComplexity();
        int col = position % board.getComplexity();
        int blankId = board.numGrids();
        boolean done = false;

        if (isValidTap(position)) {
            for (int i = 0; i != board.getComplexity(); i++) {
                for (int j = 0; j != board.getComplexity(); j++) {
                    if (this.board.getGrid(i, j).getId() == blankId) {
                        int save[] = {row, col, i, j};
                        addScoreBy(1);
                        this.board.makeMove(row, col, i, j);
                        previousMoves.add(save);
                        done = true;
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }
        }
    }

    /**
     * Return true if the undo function is available, false otherwise.
     *
     * @return if the undo function is available
     */
    public boolean undoAvailable() {

        return this.previousMoves.size() >= 1;
    }


    /**
     * Undo the previous move
     */
    public void undo() {
        if (undoAvailable()) {
            int saved[] = this.previousMoves.get(this.previousMoves.size() - 1);
            this.previousMoves.remove(this.previousMoves.size() - 1);
            this.board.makeMove(saved[0], saved[1], saved[2], saved[3]);
            addScoreBy(2);  //penalty for using undo
        }
    }
}
