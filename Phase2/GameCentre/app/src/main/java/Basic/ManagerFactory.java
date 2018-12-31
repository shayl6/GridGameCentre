package Basic;

import ColorMatching.ColorBoardManager;
import FlipToWin.FlipToWinBoardManager;
import fall2018.csc2017.slidingtiles.BoardManager;


/**
 * A Factory to generate Manager based on the given information.
 */
class ManagerFactory {


    /**
     * Return the Manager that is required.
     *
     * @return the the Manager that is required.
     */
    SuperManager getManager(String managerType, int complexity) {
        switch (managerType) {
            case "ST":
                return new BoardManager(complexity);
            case "CM":
                return new ColorBoardManager(complexity);
            case "FTW":
                return new FlipToWinBoardManager(complexity);
        }
        return null;
    }
}
