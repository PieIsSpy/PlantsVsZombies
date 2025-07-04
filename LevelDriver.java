import java.util.Scanner;

/** This class serves as a demonstration to play 1 level of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 2.0
 *
 */
class LevelDriver {
    /** This method checks if the given plant name is a valid plant,
     *  regardless of letter casing.
     *
     *  @param n the name of the plant to be checked
     *  @return true if it's a valid name, false if not
     */
    public boolean isValidName(Level l, String n) {
        int i = 0;
        boolean condition = false;

        while (i < l.getAvaliable_plants().length && !condition) {
            if (n.equalsIgnoreCase(l.getAvaliable_plants()[i].getName()))
                condition = true;
            else
                i++;
        }

        return condition;
    }

    /** This method searches the available plants of the level
     *  and returns its index when found.
     *
     * @param l the level to be checked
     * @param n the name to be found
     * @return the index of the plant found in the available plants
     * of the level
     */
    public int findName(Level l, String n) {
        int i;
        int found = -1;

        for (i = 0; i < l.getAvaliable_plants().length && found == -1; i++)
            if (l.getAvaliable_plants()[i].getName().equalsIgnoreCase(n))
                found = i;

        return found;
    }

    /** This method displays the cooldown status of the plants.
     *
     * @param m the level to check for available plants
     * @param currentTime the current time frame of the game
     */
    public void displayCooldowns(Level m, int currentTime) {
        int i;
        for (i = 0; i < m.getAvaliable_plants().length; i++) {
            if (m.getCooldown(m.getAvaliable_plants()[i].getName()).isReady(currentTime))
                System.out.println(m.getAvaliable_plants()[i].getName() + ": ready");
            else
                System.out.println(m.getAvaliable_plants()[i].getName() + ": " + m.getCooldown(m.getAvaliable_plants()[i].getName()).getRemainingTime(currentTime) + " seconds cooldown");

            System.out.println("Cost: " + m.getAvaliable_plants()[i].getCost());
        }
    }

    /** This serves as the input prompt of the Player controller class in order
     *  to edit data in the Level model class.
     *
     * @param c the player controller
     * @param m the level controller
     * @param currentTime the current time frame of the game
     */
    public void playerAction(Player c, Level m, int currentTime) {
        Scanner kb = new Scanner(System.in);
        String input;
        int row, col;

        System.out.println("Sun: " + c.getSun());
        System.out.println("Unclaimed sun: " + m.getSuns().size());
        System.out.println("1. Place plant");
        System.out.println("2. Use shovel");
        System.out.println("3. Collect sun");
        System.out.println("4. Wait");
        System.out.print("Choose an action: ");
        input = kb.nextLine();

        if (input.equalsIgnoreCase("1")) {
            displayCooldowns(m,currentTime);
            System.out.println("Type 'cancel' to cancel");
            System.out.println("Enter name of plant");
            input = kb.nextLine();

            if (!input.equalsIgnoreCase("cancel") && isValidName(m, input)) {
                System.out.println("Enter row");
                row = kb.nextInt();
                row--;
                System.out.println("Enter col");
                col = kb.nextInt();
                col--;

                // check first if its valid name and player has enough suns
                if (isValidName(m, input) && c.getSun() >= m.getAvaliable_plants()[findName(m, input)].getCost()) {
                    // then check if the plant is ready
                    if (m.getCooldown(input).isReady(currentTime)) {
                        // lastly, check if the tile can be placed
                        if (m.isValidCoordinate(row, col) && m.canBePlaced(row, col)) {
                            c.placePlant(m, row, col, input, currentTime);
                            c.subtractSun(m.getAvaliable_plants()[findName(m, input)].getCost());
                            m.getCooldown(input).updateLastPlaced(currentTime);
                            System.out.println("Placed " + m.getTiles()[row][col].getName() + " at (" + (row+1) + ", " + (col+1) + ")");
                        }
                        else if (!m.isValidCoordinate(row, col))
                            System.out.println("Invalid coordinates");
                        else
                            System.out.println("There is already a plant there");
                    } else {
                        System.out.println(m.getAvaliable_plants()[findName(m, input)].getName() + " is still in cooldown (" + m.getCooldown(input).getRemainingTime(currentTime) + ")");
                    }
                } else {
                    if (!isValidName(m, input))
                        System.out.println("Invalid input");
                    else
                        System.out.println("Not enough suns");
                }
            }
            else if (input.equalsIgnoreCase("cancel"))
                System.out.println("Canceled planting");
            else
                System.out.println("Invalid plant");
        }
        else if (input.equalsIgnoreCase("2")) {
            System.out.println("Enter row");
            row = kb.nextInt();
            row--;
            System.out.println("Enter col");
            col = kb.nextInt();
            col--;

            if (m.isValidCoordinate(row, col) && !m.canBePlaced(row, col)) {
                System.out.println("Removed " + m.getTiles()[row][col].getName() + " at (" + (row+1) + ", " + (col+1) + ")");
                c.useShovel(m, row, col);
            }
            else if (!m.isValidCoordinate(row,col))
                System.out.println("Invalid coordinates");
            else if (m.canBePlaced(row, col))
                System.out.println("There are no plants present in (" + (row+1) + "," + (col+1) + ")");
        }
        else if (input.equalsIgnoreCase("3")) {
            if (m.getUnclaimed_suns() > 0) {
                System.out.println("Collected " + m.getUnclaimed_suns() + " suns");
                c.collectSun(m.getUnclaimed_suns());
                m.removeAllSun();
            } else
                System.out.println("There are no suns to collect");
        }
        else if (!input.equalsIgnoreCase("4"))
            System.out.println("Invalid input");
    }

    // the main method of the class
    public static void main(String[] args) {
        LevelDriver util = new LevelDriver();
        long startTime = System.currentTimeMillis();
        long beforeInput;
        long afterInput;
        long totalEllapse = 0;
        int correctedTime = 0;
        boolean startFlag = false, endFlag = false;

        Level model = new Level(1, 180, 3, 9, (int)startTime/1000);
        Lawn view = new Lawn(model.getROWS(), model.getCOLUMNS());
        Player control = new Player(200);

        model.getTiles()[1][4] = new Peashooter(1,4,0);
        model.getTiles()[1][6] = new Sunflower(1,6,0);
        model.getEnemies().add(new Zombie(2, 6, 0));
        model.getEnemies().add(new PolevaulterZombie(1,9,0));
        model.getPeas().add(new Projectile(1,5,0,1000,2));

        System.out.println("Level " + model.getLEVEL_NUM());
        while (!model.isGameOver() && !model.isGameWon(correctedTime)) {
            if (correctedTime >= 30 && !startFlag) {
                System.out.println("The zombies... are coming...");
                startFlag = true;
            }

            if (correctedTime >= 170 && !endFlag) {
                System.out.println("A huge wave of zombies are coming!");
                endFlag = true;
            }

            model.gameCycle(correctedTime);
            System.out.println("Time: " + correctedTime + "/" + model.getTIME_LENGTH());
            view.displayLawn(model.getTiles(), model.getEnemies());
            beforeInput = System.currentTimeMillis();
            util.playerAction(control,model, correctedTime);
            afterInput = System.currentTimeMillis();
            totalEllapse += (int)((afterInput - beforeInput) * 0.5);
            correctedTime = (int)((System.currentTimeMillis() - startTime - totalEllapse)/1000);
        }
        if (model.isGameOver())
            System.out.println("THE ZOMBIES ATE YOUR BRAIN!");
        else
            System.out.println("You won!");
    }
}