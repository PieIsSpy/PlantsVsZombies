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
                // run the level's game cycle
                do {
                    if (!level.isGameOver() && !level.isGameWon(levelTimer) && runningLevel)
                        levelTimer = (int)((System.currentTimeMillis() - levelStart)/1000);

                    if (level != null)
                        level.gameCycle(levelTimer);
                } while (!level.isGameOver() && !level.isGameWon(levelTimer) && runningLevel);
                System.out.println("Level done");

                // check if the game is still running or not
                checkGameStatus();
                cleanUp();
            }
        }
    }

    /** This method checks if the game has ended or not.
     *  This method also cleans up any variables related to the previous level's timer
     *  if the level has already ended.
     *
     */
    public void checkGameStatus() {
        try {
            if (level.isGameWon(levelTimer)) { // if game is won
                parent.incrementProgress();
                parent.setLevelResult(level.getLEVEL_NUM());
                System.out.println("Level won");
            }
            else if (level.isGameOver()) {
                parent.setLevelResult(0);
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
        player = null;
        runningLevel = false;
    }

    /** This method initializes the level that will be running on this thread
     *
     * @param l the level to be ran in the thread
     */
    public void setLevel(Level l) {
        levelStart = System.currentTimeMillis();
        levelTimer = 0;
        level = l;
        player = new Player(100);
        runningLevel = true;
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

    /** This method returns the player that is currently playing the level.
     *
     * @return the player that is playing the level
     */
    public Player getPlayer() {
        return player;
    }

    /** This method places a specified plant into a
     *  row and col position and subtracts its
     *  current sun collection.
     *
     * @param name the name of the plant to be placed
     * @param row the row of the plant
     * @param col the col of the plant
     */
    public void playerPlant(String name, int row, int col) {
        player.placePlant(level, row, col, name, levelTimer);
        player.subtractSun(((Plant)(level.getTiles()[row][col])).getCost());
        level.getCooldown(name).updateLastPlaced(levelTimer);
    }

    /** This method prompts the player to shovel an
     *  occupied row and col
     *
     * @param row the row of the tile to be shoveled
     * @param col the col of the tile to be shoveled
     */
    public void playerShovel(int row, int col) {
        player.useShovel(level,row,col);
    }

    /** This method checks if the plant is ready to be placed.
     *
     * @param name the name of the plant to be checked
     * @return true if the plant is ready to be placed,
     * false otherwise
     */
    public boolean isPlantReady(String name) {
        return level.getCooldown(name).isReady(levelTimer);
    }

    /** This method checks if the player has enough suns
     *  to place a specific plant.
     *
     * @param name the name of the plant to check the cost
     * @return true if the player has enough suns, false
     * otherwise
     */
    public boolean hasEnoughSuns(String name) {
        int i;
        Plant p = null;

        for (i = 0; i < level.getAvaliable_plants().length && p == null; i++)
            if (name.equalsIgnoreCase(level.getAvaliable_plants()[i].getName()))
                p = level.getAvaliable_plants()[i];

        if (p != null)
            return player.getSun() >= p.getCost();
        else
            return false;
    }

    /**the model parent communicating with this thread*/
    private Model parent;
    /**the current level being ran on the thread*/
    private Level level;
    /**the player handling the running level*/
    private Player player;
    /**the running status of the level*/
    private boolean runningLevel;
    /**the time frame where the level has started*/
    private long levelStart;
    /**the current time frame of the level*/
    private int levelTimer;
}