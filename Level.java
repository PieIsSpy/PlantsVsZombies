
import java.util.ArrayList;
import java.util.Scanner;

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
     * This constructor initializes the gameplay settings
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
        time_current = 30;
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

    public void displayChar(int n, char c) {
        int i;
        for (i = 0; i < n; i++)
            System.out.print(c);
    }

    public void displayLine() {
        int i, j;
        displayChar(1, '+');

        for (i = 0; i < COLUMNS; i++) {
            displayChar(4, '-');
            displayChar(1, '+');
        }
    }

    public void displayLawn() {

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

    /**
     * This method checks if player has won the game
     * by determining if all zombies are killed (enemies
     * array list is empty) or if a certain amount of 
     * time has passed. 
     *
     * 
     * @return true if there are no enemies left
     * or if the current time has reached the time 
     * limit, false otherwise. 
     */
    public boolean isGameWon() {
        return (time_current > 170 && enemies.isEmpty()) || time_current >= TIME_LENGTH;
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

    /** This method checks if the given plant name is in cooldown or not.
     *
     *  @param name the name of the plant to be checked
     *  @return true if the plant is still in cooldown, false if not
     */
    public boolean isInCooldown(String name) {
        int i;
        boolean condition = true;
        for (i = 0; i < availablePlants.length; i++)
            if (name.equalsIgnoreCase(availablePlants[i].getName())) {
                condition = !cooldowns[i].isReady(time_current);
            }

        return condition;
    }

    /** This method checks if the given plant name is a valid plant,
     *  regardless of letter casing.
     *
     *  @param n the name of the plant to be checked
     *  @return true if it's a valid name, false if not
     */
    public boolean isValidName(String n) {
        int i = 0;
        boolean condition = false;

        while (i < availablePlants.length && !condition) {
            if (n.equalsIgnoreCase(availablePlants[i].getName()))
                condition = true;
            else
                i++;
        }

        return condition;
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
     *
     *  @param name the name of the plant to be placed
     *  @param r the row of the grid
     *  @param c the column of the grid
     *  @return
     */
    public Plant createPlant(String name, int r, int c) {
        int i = 0;
        int found = -1;
        while (i < availablePlants.length && found == -1) {
            if (name.equalsIgnoreCase(availablePlants[i].getName()))
                found = i;
            else
                i++;
        }

        // update cooldown
        if (found != -1)
            cooldowns[found].updateLastPlaced(time_current);

        // return instantiation
        if (name.equalsIgnoreCase("peashooter"))
            return new Peashooter(r, c);
        else if (name.equalsIgnoreCase("sunflower"))
            return new Sunflower(r, c);

        // it is assumed that the name is already valid
        // in some cases, if the given name is still invalid
        // this method does nothing
        return null;
    }

    /** This method is an action that lets the player place a plant
     *  in their lawn, provided that the given tile is unoccupied and
     *  the given name of the plant is not in cooldown.
     *
     *  @param kb the scanner to be used for inputs
     */
    public void placePlant(Scanner kb) {
        int row, col;
        String name;
        System.out.println("Enter name of plant: ");
        name = kb.nextLine();
        System.out.println("Enter row coordinate: ");
        row = kb.nextInt();
        System.out.println("Enter col coordinate: ");
        col = kb.nextInt();

        if (isValidName(name) && isValidCoordinate(row, col)) {
            if (canBePlaced(row, col)) {
                if (!isInCooldown(name)) {
                    tiles[row - 1][col - 1] = createPlant(name, row - 1, col - 1);
                    System.out.println("Placed a " + tiles[row-1][col-1].getName() + " at row:" + row + ", col:" + col);
                }
                else
                    System.out.println("Plant is still in cooldown");
            }
            else
                System.out.println("Tile is occupied");
        }
        else
            System.out.println("Invalid Input");
    }

    /**
     * This method spawns a new zombie at a random row 
     * and in the rightmost column. It is then added
     * to the enemies array list. 
     */
    public void spawnZombie() {
        enemies.add(new Zombie((int)(Math.floor(Math.random() * (ROWS))), COLUMNS + 1));
        //System.out.println("Time: " + time_current);
        System.out.printf("Spawned zombie at row %d, col %d\n", (int)enemies.getLast().getRow(), (int)enemies.getLast().getCol());
        System.out.println();
        //enemies.clear();
    }

    public void playerAction(Scanner kb) {
        int input;
        int row, col;
        System.out.println("1. Place plant");
        System.out.println("2. Collect sun");
        System.out.println("3. Wait");
        System.out.print("Input: ");
        input = kb.nextInt();

        if (input == 1)
            kb.nextLine();

        if (input == 1) {
            placePlant(kb);
        }
    }

    /** This method searches for entities 
     * that have a health of 0, and removes
     * them from the game. 
     *
     */
    public void despawn() {
        int i;
        // zombies
        for (i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getHealth() == 0)
                enemies.remove(i);
    }

    /**
     * will finish tom
     * 
     * 
     */
    public void gameCycle() {
        Scanner kb = new Scanner(System.in);
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

            // player action
            playerAction(kb);

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

        kb.close();
    }

    /**
     * This method returns the current time of 
     * the game. 
     * 
     * @return current time of game. 
     */
    public int getCurrentTime()
    {
        return time_current;
    }

    /**number of levels to track game progress */
    private final int LEVEL_NUM; 
    /**time limit of game*/
    private final int TIME_LENGTH; 
    /**current time of game*/
    private int time_current; 
    /**maximum number of rows of game grid */
    private final int ROWS; 
    /**maximum number of columns of game grid */
    private final int COLUMNS; 
    /**2d array of plant objects to be used as game grid*/
    private Plant[][] tiles; 
    /**array list of zombie objects */
    private ArrayList<Zombie> enemies; 
    /**contains all available plant types(e.g., Sunflower, Peashooter, etc) */
    private Plant[] availablePlants; 
    /**array of cooldown objects per plant type */
    private Cooldown[] cooldowns;
    /**current amount of sun*/
    private int current_sun = 1000;
    private int collectable_sun = 0;
}
