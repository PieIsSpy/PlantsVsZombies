import java.util.Scanner;

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

    public int findName(Level l, String n) {
        int i;
        int found = -1;

        for (i = 0; i < l.getAvaliable_plants().length && found == -1; i++)
            if (l.getAvaliable_plants()[i].getName().equalsIgnoreCase(n))
                found = i;

        return found;
    }

    public void displayCooldowns(Level m, int startingTime) {
        int i;
        for (i = 0; i < m.getAvaliable_plants().length; i++) {
            if (m.getCooldown(m.getAvaliable_plants()[i].getName()).isReady(startingTime))
                System.out.println(m.getAvaliable_plants()[i].getName() + ": ready");
            else
                System.out.println(m.getAvaliable_plants()[i].getName() + ": " + m.getCooldown(m.getAvaliable_plants()[i].getName()).getRemainingTime(startingTime) + " seconds cooldown");

            System.out.println("Cost: " + m.getAvaliable_plants()[i].getCost());
        }
    }

    public void playerAction(Player c, Level m, int startingTime) {
        Scanner kb = new Scanner(System.in);
        String input;
        int row, col;
        boolean success;

        System.out.println("Sun: " + c.getSun());
        System.out.println("Unclaimed sun: " + m.getUnclaimed_suns());
        System.out.println("1. Place plant");
        System.out.println("2. Use shovel");
        System.out.println("3. Collect sun");
        System.out.println("4. Wait");
        System.out.print("Choose an action: ");
        input = kb.nextLine();

        if (input.equalsIgnoreCase("1")) {
            displayCooldowns(m,startingTime);
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
                    if (m.getCooldown(input).isReady(startingTime)) {
                        // lastly, check if the tile can be placed
                        if (m.isValidCoordinate(row, col) && m.canBePlaced(row, col)) {
                            c.placePlant(m, row, col, input, startingTime);
                            c.subtractSun(m.getAvaliable_plants()[findName(m, input)].getCost());
                            m.getCooldown(input).updateLastPlaced(startingTime);
                            System.out.println("Placed " + m.getTiles()[row][col].getName() + " at (" + (row+1) + ", " + (col+1) + ")");
                        }
                        else if (!m.isValidCoordinate(row, col))
                            System.out.println("Invalid coordinates");
                        else
                            System.out.println("There is already a plant there");
                    } else {
                        System.out.println(m.getAvaliable_plants()[findName(m, input)].getName() + " is still in cooldown (" + m.getCooldown(input).getRemainingTime(startingTime) + ")");
                    }
                } else {
                    if (!isValidName(m, input))
                        System.out.println("Invalid input");
                    else
                        System.out.println("Not enough suns");
                }
            }
            else if (!isValidName(m, input))
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
        }
        else if (input.equalsIgnoreCase("3")) {
            if (m.getUnclaimed_suns() > 0) {
                System.out.println("Collected " + m.getUnclaimed_suns() + " suns");
                c.collectSun(m.getUnclaimed_suns());
                m.setUnclaimed_suns(0);
                m.removeAllSun();
            } else
                System.out.println("There are no suns to collect");
        }
        else if (!input.equalsIgnoreCase("4"))
            System.out.println("Invalid input");

        //return (int)System.currentTimeMillis()/1000 - startingTime;
    }

    public static void main(String[] args) {
        //Plant[] p = {new Sunflower(-1, -1, 0), new Peashooter(-1,-1, 0)};
        LevelDriver util = new LevelDriver();
        long startTime = System.currentTimeMillis();
        long beforeInput;
        long afterInput;
        long totalEllapse = 0;
        int correctedTime = 0;
        boolean startFlag = false, endFlag = false;

        Level model = new Level(1, 180, 5, 9, (int)startTime/1000);
        Lawn view = new Lawn(5, 9);
        Player control = new Player(200);

        System.out.println("Level " + model.getLEVEL_NUM());
        while (!model.isGameOver() && !model.isGameWon(correctedTime)) {
            if (correctedTime >= 30 && !startFlag) {
                System.out.println("The zombies... are coming...");
                startFlag = true;
            }
            else if (correctedTime <= 170)

            model.gameCycle(correctedTime);
            System.out.println("Time: " + correctedTime + "/" + model.getTIME_LENGTH());
            view.displayLawn(model.getTiles(), model.getEnemies());
            beforeInput = System.currentTimeMillis();
            util.playerAction(control,model, correctedTime);
            afterInput = System.currentTimeMillis();
            totalEllapse += afterInput - beforeInput;
            correctedTime = (int)((System.currentTimeMillis() - startTime - totalEllapse)/10);
        }
    }
}