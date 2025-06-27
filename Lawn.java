import java.util.ArrayList;
import java.util.Scanner;

/** This class represents a visual display
 *  of the player's Lawn.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Lawn {
    /** This constructor initializes the rows and columns of the
     *  Lawn.
     *
     *  @param r the rows of the lawn
     *  @param c the columns of the lawn
     */
    public Lawn(int r, int c) {
        ROWS = r;
        COLUMNS = c;
    }

    /** This method displays a char in a certain amount of times
     *
     *  @param n the amount of times to be displayed
     *  @param c the char to be displayed
     */
    public void displayChar(int n, char c) {
        int i;
        for (i = 0; i < n; i++)
            System.out.print(c);
    }

    /** This method displays a line that cannot be affected by
     *  plants or zombies.
     *
     */
    public void displayLine() {
        int i, j;
        System.out.print("\t");
        displayChar(1, '+');
        for (i = 0; i < COLUMNS; i++) {
            displayChar(4, ' ');
            displayChar(1, '+');
        }
    }

    /** This method displays the contents of the Lawn.
     *
     *  @param p the plant tiles of the lawn
     *  @param z the zombies in the lawn
     *  @param r the row to be displayed
     */
    public void displayContent(Plant[] p, ArrayList<Zombie> z, int r) {
        int i;
        int j;
        boolean found;
        String output = String.valueOf(r);
        output = output.concat("\t");
        //System.out.print(r + "\t");

        // display plants and zombies
        for (i = 0; i < COLUMNS; i++) {
            if (p[i] != null) {
                if (p[i].getName().equalsIgnoreCase("sunflower"))
                    output = output.concat("   S");
                else if (p[i].getName().equalsIgnoreCase("peashooter"))
                    output = output.concat("   P");
            }
            else {
                j = 0;
                found = false;

                // find a zombie in that col
                while (j < z.size() && !found) {
                    if (i == (int)z.get(j).getCol() && z.get(j).getRow() == r-1) {
                        output = output.concat("    Z");
                        found = true;
                    }
                    j++;
                }

                if (!found)
                    output = output.concat("     ");
            }
        }

        System.out.print(output);
        System.out.println();
    }

    /** This method displays the whole lawn of the player.
     *
     *  @param p the tiles of the lawn which contains plants
     *  @param z the zombies present in the lawn
     */
    public void displayLawn(Plant[][] p, ArrayList<Zombie> z) {
        int i;
        // print number of cols
        System.out.print("\t");
        for (i = 1; i <= COLUMNS; i++) {
            if (i <= 10)
                System.out.printf("  %d  ", i);
            else
                System.out.printf(" %d  ", i);
        }
        System.out.println();
        displayLine();

        // print out spaces in each grid
        System.out.println();
        for (i = 0; i < ROWS; i++) {
            displayContent(p[i], z, i + 1);
            displayLine();
            System.out.println();
        }
    }

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
                m.setUnclaimed_suns(0);
                m.removeAllSun();
                Sun.setCount(0);
            } else
                System.out.println("There are no suns to collect");
        }
        else if (!input.equalsIgnoreCase("4"))
            System.out.println("Invalid input");
    }

    /** the rows of the lawn */
    private final int ROWS;
    /** the columns of the lawn */
    private final int COLUMNS;
}