package Basic;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Scores.ScoreBoard;
import Users.UserManager;

/**
 * This is a FileManager class that is responsible for all save and load functionality
 */
public class FileManager {

    /**
     * Save the given object to file.
     * <p>
     * Precondition: the type is either "UM" or "SB"
     *
     * @param fileContext this.getApplicationContext()
     * @param object      the object that will be saved
     * @param type        the type of the object.
     */
    public static void saveToFile(Context fileContext, Object object, String type) {
        try {
            String fileName;
            if ("UM".equals(type)) {
                fileName = "UserManager.ser";
            } else {
                fileName = DataManager.INSTANCE.getCurrentGameName() + "_ScoreBoard.ser";
            }
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(object);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the userManager from file and return it
     *
     * @param fileContext this.getApplicationContext()
     * @return the userManager that loaded from file
     */
    public static UserManager loadUserManager(Context fileContext) {
        try {
            String fileName = "UserManager.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                UserManager userManager = (UserManager) input.readObject();
                inputStream.close();
                return userManager;
            }
            return new UserManager();
        } catch (Exception e) {
            return new UserManager();
        }
    }

    /**
     * Load the scoreBoard from file and return it
     *
     * @param fileContext this.getApplicationContext()
     * @return the scoreBoard that loaded from file
     */
    public static ScoreBoard loadScoreBoard(Context fileContext) {
        try {
            String fileName = DataManager.INSTANCE.getCurrentGameName() + "_ScoreBoard.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                ScoreBoard scoreBoard = (ScoreBoard) input.readObject();
                inputStream.close();
                return scoreBoard;
            }
            return new ScoreBoard();
        } catch (Exception e) {
            return new ScoreBoard();
        }
    }

    /**
     * Save the boardManager of the current game to file
     *
     * @param fileContext this.getApplicationContext()
     * @param operation   "Save" or "Auto"
     */
    public static void saveGame(Context fileContext, String operation) {
        try {
            String fileName = DataManager.INSTANCE.getCurrentGameName() + "_" +
                    DataManager.INSTANCE.getCurrentUserName() + "_" + operation + ".ser";
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(DataManager.INSTANCE.getBoardManager());
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load boardManager of the current game related to current user from file
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the file that will be load
     */
    static void loadGame(Context fileContext, String fileName) {
        try {
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                DataManager.INSTANCE.setBoardManager((SuperManager) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }
}
