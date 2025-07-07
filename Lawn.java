import java.util.ArrayList;

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
    public void displayContent(Entity[] p, ArrayList<Zombie> z, int r, int t) {
        int i;
        int j;
        boolean found;
        String output = String.valueOf(r);
        output = output.concat("\t");
        PotatoMine m;
        //System.out.print(r + "\t");

        // display plants and zombies
        for (i = 0; i < COLUMNS; i++) {
            if (p[i] != null) {
                if (p[i] instanceof Sunflower)
                    output = output.concat("   S");
                else if (p[i] instanceof Peashooter)
                    output = output.concat("   P");
                else if (p[i] instanceof PotatoMine) {
                    m = (PotatoMine)p[i];

                    if (m.isPrimed(t))
                        output = output.concat("   M");
                    else
                        output = output.concat("   m");
                }
                else if (p[i] instanceof Tombstone)
                    output = output.concat("   t");
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
    public void displayLawn(Entity[][] p, ArrayList<Zombie> z, int t) {
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
            displayContent(p[i], z, i + 1, t);
            displayLine();
            System.out.println();
        }
    }

    /** the rows of the lawn */
    private final int ROWS;
    /** the columns of the lawn */
    private final int COLUMNS;
}