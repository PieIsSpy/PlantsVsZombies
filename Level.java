import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

/**
 * This class represents the core game logic
 * and mechanics of Plants vs Zombies. It manages
 * the game grid, cooldowns, and tick based
 * progression of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 2.0
 *
 */
public class Level {
    /** This constructor
     *
     * @param n
     * @param t
     * @param r
     * @param c
     * @param curTime
     */
    public Level(int n, int t, int r, int c, int curTime) {
        int i;

        internal_start = curTime;
        sun_interval = curTime;

        LEVEL_NUM = n;
        TIME_LENGTH = t;
        ROWS = r;
        COLUMNS = c;

        tiles = new Plant[r][c];
        enemies = new ArrayList<Zombie>();
        suns = new ArrayList<>();

        avaliable_plants = new Plant[]{new Sunflower(-1, -1, 0), new Peashooter(-1, -1, 0)};
        cooldowns = new Cooldown[avaliable_plants.length];

        for (i = 0; i < avaliable_plants.length; i++)
            cooldowns[i] = new Cooldown(avaliable_plants[i].getName(), avaliable_plants[i].getCooldown(), curTime);
    }

    public int getLEVEL_NUM() {
        return LEVEL_NUM;
    }

    public int getTIME_LENGTH() {
        return TIME_LENGTH;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getUnclaimed_suns() {
        return unclaimed_suns;
    }

    public Plant[][] getTiles() {
        return tiles;
    }

    public ArrayList<Zombie> getEnemies() {
        return enemies;
    }

    public Plant[] getAvaliable_plants() {
        return avaliable_plants;
    }

    public Cooldown getCooldown(String n) {
        int i = 0;
        int found = -1;

        if (cooldowns[i].getPlantType().equalsIgnoreCase(n))
            found = i;
        else
            i++;

        return cooldowns[i];
    }

    public ArrayList<Sun> getSuns()
    {
        return suns;
    }

    public void setUnclaimed_suns(int n) {
        unclaimed_suns = n;
    }

    /** This method checks if the given row and col coordinates are valid or not.
     *
     *  @param r the row coordinate to be checked
     *  @param c the col coordinate to be checked
     *  @return true if the coordinates are valid, false if not
     */
    public boolean isValidCoordinate(int r, int c) {
        return r >= 0 && r < ROWS && c >= 0 && c < COLUMNS;
    }

    /**
     * This method checks to see if the tile
     * where plant is to be planted is unoccupied.
     *
     * @param row number of rows in game grid
     * @param col number of columns in game grid
     *
     * @return true if tile at the given position
     * is null/unoccupied, false otherwise.
     *
     */
    public boolean canBePlaced(int row, int col) {
        return tiles[row][col] == null;
    }

    /**
     * This method checks if player has won the game
     * by determining if all zombies are killed (enemies
     * array list is empty) or if a certain amount of
     * time has passed.
     *
     * @param time the current time
     * @return true if there are no enemies left
     * or if the current time has reached the time
     * limit, false otherwise.
     */
    public boolean isGameWon(int time) {
        return (time >= (int)Math.ceil(TIME_LENGTH * 0.945) && enemies.isEmpty()) || time >= TIME_LENGTH;
    }

    /**
     * This method checks if the game has ended by
     * determining if at least one of the zombies
     * have reached the player's house.
     *
     *
     * @return true if at least one zombie reached the house,
     * false otherwise.
     */
    public boolean isGameOver() {
        int i = 0;
        boolean condition = false;

        if (!enemies.isEmpty()) {
            while (i < enemies.size() && !condition) {
                if (enemies.get(i).isAtHouse())
                    condition = true;

                i++;
            }
        }

        return condition;
    }

    /**
     * This method spawns a new zombie at a random row
     * and in the rightmost column. It is then added
     * to the enemies array list.
     */
    public void spawnZombies(int currentTime) {
        enemies.add(new Zombie((int)(Math.floor(Math.random() * ROWS)), COLUMNS + 1, currentTime));
    }

    /** This method searches for entities
     * that have a health of 0, and removes
     * them from the game.
     *
     */
    public void despawn() {
        int i, j;
        // zombies
        for (i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getHealth() == 0) {
                enemies.remove(i);
                Zombie.die();
            }

        // plants
        for (i = 0; i < ROWS; i++)
            for (j = 0; j < COLUMNS; j++)
                if (tiles[i][j] != null && tiles[i][j].getHealth() == 0)
                    tiles[i][j] = null;
    }

    public void behaviors(int currentTime) {
        int i, j;

        for (i = 0; i < enemies.size(); i++)
            enemies.get(i).behaviour(tiles[(int)enemies.get(i).getRow()], currentTime);

        for (i = 0; i < ROWS; i++)
            for (j = 0; j < COLUMNS; j++)
                if (tiles[i][j] != null)
                    tiles[i][j].plantBehavior(this, currentTime);

        for (i = 0; i < suns.size(); i++)
            suns.get(i).update(currentTime);
    }

    public void gameCycle(int currentTime) {
        int interval = 0;

        behaviors(currentTime);

        if (currentTime >= (int)Math.floor(TIME_LENGTH * 0.17) && currentTime <= (int)Math.floor(TIME_LENGTH * 0.445))
            interval = 10;
        else if (currentTime >= (int)Math.floor(TIME_LENGTH * 0.45) && currentTime <= (int)Math.floor(TIME_LENGTH * 0.78))
            interval = 5;
        else if (currentTime >= (int)Math.floor(TIME_LENGTH * 0.785) && currentTime <= (int)Math.floor(TIME_LENGTH * 0.945))
            interval = 3;

        if (interval != 0 && currentTime - internal_start >= interval) {
            spawnZombies(currentTime);
            System.out.println("Spawned Zombie at (" + (enemies.getLast().getRow() + 1) + ", " + (enemies.getLast().getCol() + 1) + ")");
            internal_start = currentTime;
        }

        if (currentTime - sun_interval >= 20) {
            addSun(currentTime);
            sun_interval = currentTime;
        }
    }

        //used for sunflower
    public void addSun(Plant p, int currentTime)
    {
        suns.add(new Sun(p.getRow(), p.getCol(), false, currentTime));
        unclaimed_suns += suns.getLast().getAmount();
    }

    //used for sun falling from the sky
    //i still need to update the random spawn since theres a better way than whatever this is
    public void addSun(int currentTime)
    {
        //random spawn
        Random random = new Random();

        //generates a random number for where it will spawn/fall
        float columnSpawn = random.nextInt(COLUMNS) + random.nextFloat();
        float targetSpawn = random.nextInt(ROWS) + random.nextFloat();

        suns.add(new Sun(0, columnSpawn, true, Math.max(targetSpawn, 1.5f), currentTime));
        unclaimed_suns += suns.getLast().getAmount();
    }

    public void removeInactiveSun()
    {
        int i;

        //removes the sun that have disappeared
        for(i = suns.size() - 1; i >= 0; i--)
        {
            if(!suns.get(i).isActive())
            {
                suns.remove(i);
            }

        }
    }

    public void removeAllSun() {
        suns.clear();
    }

    /**number of levels to track game progress */
    private final int LEVEL_NUM;
    /**time limit of game*/
    private final int TIME_LENGTH;
    /**maximum number of rows of game grid */
    private final int ROWS;
    /**maximum number of columns of game grid */
    private final int COLUMNS;
    /**2d array of plant objects to be used as game grid*/
    private Plant[][] tiles;
    /**array list of zombie objects */
    private ArrayList<Zombie> enemies;
    /**current unclaimed suns*/
    private int unclaimed_suns;
    private Plant[] avaliable_plants;
    private Cooldown[] cooldowns;
    private ArrayList<Sun> suns;
    private int internal_start;
    private int sun_interval;
}
