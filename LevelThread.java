/** This class is responsible for allowing the game cycle
 *  of the level to be looped without interrupting the main thread of the program.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 *
 */
public class LevelThread extends Thread {
    /** This constructor initializes the runningLevel status
     *  and the parent model that this class will be communicating with.
     *
     * @param m the parent model class that will communicate with this thread
     */
    public LevelThread(Model m) {
        parent = m;
        runningLevel = false;
    }

    /** This method handles the game cycle of the level.
     *
     */
    public void run() {
        while (this.isAlive()) {
            if (runningLevel) {
                levelTimer = 0;
                // run the level's game cycle
                do {
                    levelTimer = (int)((System.currentTimeMillis() - levelStart)/1000);
                    level.gameCycle(levelTimer);
                } while (!level.isGameOver() && !level.isGameWon(levelTimer) && runningLevel);
                System.out.println("Level done");

                // check if the game is still running or not
                checkGameStatus(levelTimer);
                cleanUp();
            }
        }
    }

    /** This method checks if the game has ended or not.
     *  This method also cleans up any variables related to the previous level's timer
     *  if the level has already ended.
     *
     * @param levelTimer the current time of the level
     */
    public void checkGameStatus(int levelTimer) {
        try {
            if (level.isGameWon(levelTimer)) { // if game is won
                parent.incrementProgress();
                System.out.println("Level won");
            }
            else if (level.isGameOver()) {
                System.out.println("Level lost");
            }
        }
        catch (NullPointerException e) {
            System.out.println("Level has been preterminated");
        }

        if (!runningLevel) { // if its not running anymore
            cleanUp();
        }
    }

    /** This method cleans up the timers and game status of the thread
     *  and removes the level that has already ended to make way for other
     *  levels to be played.
     *
     */
    public void cleanUp() {
        levelTimer = 0;
        level = null;
        runningLevel = false;
    }

    /** This method initializes the level that will be running on this thread
     *
     * @param l the level to be ran in the thread
     */
    public void setLevel(Level l) {
        level = l;
        runningLevel = true;
        levelStart = System.currentTimeMillis();
    }

    /** This method changes the running status of the level.
     *
     * @param b the current running status of the level
     */
    public void setRunningLevel(boolean b) {
        runningLevel = b;
    }

    /** This method returns the current level that is being ran on the thread.
     *
     * @return the current level running in the thread
     */
    public Level getLevel() {
        return level;
    }

    /**the model parent communicating with this thread*/
    private Model parent;
    /**the current level being ran on the thread*/
    private Level level;
    /**the running status of the level*/
    private boolean runningLevel;
    /**the time frame where the level has started*/
    private long levelStart;
    /**the current time frame of the level*/
    private int levelTimer;
    private int offSet;
}