/** This class is responsible for initializing the game logic
 *  of a Level class. It is also responsible for keeping track
 *  of all the levels cleared by the player.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 *
 */
public class Model {
    /** This constructor initializes the thread that will be handling
     *  the game cycle of the Level class and starts it.
     *
     */
    public Model() {
        thread = new LevelThread(this);
        thread.start();
    }

    /** This method initializes the Level to be played by the player.
     *  If the progress of the player is lower than the level number
     *  of a level or if a level is already running, the new level will not run.
     *
     * @param levelNum the level number to be played
     */
    public void selectLevel(int levelNum) {
        System.out.println("Called selectLevel()");

        // check if there are no levels running
        if (thread.getLevel() == null) {
            // make a new level
            if (levelNum <= levelProgress) {
                switch (levelNum) {
                    case 1:
                        level = new Level1(0);
                        break;
                    case 2:
                        level = new Level2(0);
                        break;
                    case 3:
                        level = new Level3(0);
                        break;
                }

                // place the level into the thread
                thread.setLevel(level);
            }
            else
                System.out.println("The level has not been unlocked yet");
        } else
            System.out.println("A level is currently running");
    }

    /** This method pre terminates the current running level
     *  of the game.
     *
     */
    public void endLevel() {
        if (thread.getLevel() != null) {
            System.out.println("Level has ended");
            thread.setRunningLevel(false);
            thread.cleanUp();
        }
        else
            System.out.println("There is no level currently running");
    }

    /** This method increments the level progress of the player.
     *
     */
    public void incrementProgress() {
        levelProgress++;
    }

    /** This method prompts the thread to place a plant
     *  into a given row and col
     *
     * @param name the name of the plant to be placed
     * @param row the row of the plant
     * @param col the col of the plant
     */
    public void playerPlant(String name, int row, int col) {
        thread.playerPlant(name, row, col);
    }

    /** This method prompts the thread to shovel an
     *  occupied row and col
     *
     * @param row the row of the tile to be shoveled
     * @param col the col of the tile to be shoveled
     */
    public void playerShovel(int row, int col) {
        thread.playerShovel(row,col);
    }

    /** This method returns the running level thread of the Model.
     *
     * @return the level thread of the model
     */
    public LevelThread getLevelThread() {
        return thread;
    }

    /** This method returns the current level progression
     *  of the game.
     *
     * @return the current level progression of the game
     */
    public int getLevelProgress() {
        return levelProgress;
    }

    /** This method returns the result of the level
     *  running on the thread.
     *
     * @return the result of the level running on the thread
     */
    public int getLevelResult() {
        return levelResult;
    }

    /** This method sets the result of the level
     *  running on the thread.
     *
     * @param n the result of the level
     */
    public void setLevelResult(int n) {
        levelResult = n;
    }

    /**the latest result of the game*/
    private int levelResult;
    /**the progress of the player in the game*/
    private int levelProgress = 1;
    /**the running level thread of the model*/
    private LevelThread thread;
    /**the level being played*/
    private Level level;
}