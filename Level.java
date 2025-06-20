
import java.util.ArrayList;
/**
 * This class represents the core game logic
 * and mechanics of Plants vs Zombies. It manages
 * the game grid, cooldowns, and tick based 
 * progression of the game. 
 * 
 * @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 * 
 */
public class Level {

    /**
     * This constructor intitializes the gameplay settings
     * for the level such as its number, grid dimensions, 
     * duration, and starting time. 
     * 
     * @param l level number, indicating the 
     * @param r number of rows of the game grid
     * @param c number of columns of the game grid
     * @param t total duration of the game
     */

    public Level (int l, int r, int c, int t) {

        LEVEL_NUM = l;
        ROWS = r;
        COLUMNS = c;
        TIME_LENGTH = t;
        time_current = 0;
        enemies = new ArrayList<Zombie>();
        tiles = new Plant[r][c];

        initialize_AvailablePlants();
        initializeCooldown();
    }

    /**
     * This method initializes all the available plant types
     * of the game. 
     */
    public void initialize_AvailablePlants()
    {

        availablePlants = new Plant[] {new Sunflower(-1, -1), new Peashooter(-1, -1)};
    }

    /**
     * This method initializes the cooldown logic for
     * each plant type.It ensures that each plant type can
     * track its individual cooldown timer during gameplay. 
     */
    public void initializeCooldown()
    {   
        int i;

        cooldowns = new Cooldown[availablePlants.length];
        for(i = 0; i < availablePlants.length; i++)
        {
            cooldowns[i] = new Cooldown(availablePlants[i].getName(), availablePlants[i].getCooldown());
        }
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

        while (i < enemies.size() && !condition) {
            if (enemies.get(i).isAtHouse())
                condition = true;

            i++;
        }

        return condition;
    }

    public boolean isGameWon() {
        return enemies.isEmpty() && time_current >= TIME_LENGTH;
    }

    public boolean canBePlaced(int row, int col) {
        return tiles[row][col] == null;
    }

    public boolean placePlant(int row, int col)
    {
        int i;
        boolean isFound = false;

        if(!canBePlaced(row, col))
        {
            System.out.println("Occupied!");
            return false;
        }

        i = 0;

        while(i < tiles.length && !isFound)
        {
            if(availablePlants[i].getName().equals(tiles[row][col].getName()))
            {
                if(cooldowns[i].isReady(time_current))
                {
                    tiles[row][col] = new Plant(row, col);
                    cooldowns[i].updateLastPlaced(time_current);
                    System.out.println(tiles[row][col].getName() + "successfully planted at (" + tiles[row][col].getRow() + ", " + tiles[row][col].getCol() + ")");
                }
                else
                {
                    System.out.println(tiles[row][col].getName() + " is still cooling down, with " + cooldowns[i].getRemainingTime(time_current) + " seconds left");
                }
                isFound = true;
                
            }
            
            i++;
        }

        return true;
    }

    public void spawnZombie() {
        enemies.add(new Zombie((int)(Math.floor(Math.random() * (ROWS))), COLUMNS + 1));
        //System.out.println("Time: " + time_current);
        System.out.printf("Spawned zombie at row %d, col %d\n", (int)enemies.getLast().getRow(), (int)enemies.getLast().getCol());
        System.out.println();
        //enemies.clear();
    }

    public void playerAction() {

    }

    /** This method searches and despawns dead entities.
     *
     */
    public void despawn() {
        int i;
        // zombies
        for (i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getHealth() == 0)
                enemies.remove(i);
    }

    public void gameCycle() {
        int interval = 0;
        int cout = 0;
        int i;
        boolean waveFlag = false;

        System.out.println("Level " + LEVEL_NUM);

        while (!isGameWon() && !isGameOver()) {
            System.out.println("time: " + time_current);
            // check time
            if (time_current >= 30 && time_current <= 80) {
                if (time_current == 30)
                    System.out.println("The zombies are coming...");
                interval = 10;
            }
            else if (time_current >= 81 && time_current <= 140)
                interval = 5;
            else if (time_current >= 141 && time_current <= 170)
                interval = 3;

            // check once if its time for a huge wave of zombies
            if (time_current >= 170 & !waveFlag) {
                System.out.println("A huge wave of zombies are coming!");
                for (i = 0; i < 5; i++)
                    spawnZombie();

                waveFlag = true;
            }

            // check if its time to spawn one
            if (interval != 0 & cout >= interval && time_current < TIME_LENGTH) {
                spawnZombie();
                cout = 0;
            }

            //behavior call
            for (i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).isAlive())
                    enemies.get(i).behaviour(tiles[(int)enemies.get(i).getRow()]);
            }

            // despawn dead entities
            despawn();

            // check current game status
            if (!isGameWon() && !isGameOver()) {
                cout++;
                time_current++;
            }
            else if (isGameOver())
                System.out.println("THE ZOMBIES ATE YOUR BRAIN!");
            else if (isGameWon())
                System.out.println("You won!");
        }

        
    }

    public int getCurrentTime()
    {
        return time_current;
    }

    private final int LEVEL_NUM;
    private final int TIME_LENGTH;
    private int time_current;
    private final int ROWS;
    private final int COLUMNS;
    private Plant[][] tiles;
    private ArrayList<Zombie> enemies;
    private Plant[] availablePlants;
    private Cooldown[] cooldowns;
}
