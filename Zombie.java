import java.util.ArrayList;

/** The class Zombie represents a basic zombie object that inherits an Entity class
 *  that can be used by other child classes that represent
 *  variant zombies.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.1
 */
public class Zombie extends Entity {
    /** This constructor initializes the default values
     *  of a basic zombie and places it to a given row.
     *  This also increments the static variable
     *  "count" by 1.
     *
     *  @param r the row grid position of the Zombie
     *  @param c the col grid position of the zombie
     */
    public Zombie(int r, int c) {
        super(70, 4,10, r, c);

        count++;
    }

    /** This method checks if the Zombie has already
     *  reached the player's house.
     *
     *  @return true if the zombie's column coordinate is less than or
     *  equal to 0, false otherwise
     */
    public boolean isAtHouse() {
        return getCol() <= -1;
    }

    /** This method moves the Zombie to the left of the
     *  lawn at speed variable seconds per grid.
     */
    public void walk() {
        float cur = getCol();
        cur -= (float) (1.0 / getSpeed());
        setCol(cur);
    }

    /** This method makes the zombie eat the plant that is
     *  in front of it, dealing damage.
     *
     *  @param p the target plant object to be damaged
     */
    public void eat(Plant p) {
        System.out.println("Eating " + p.getName() + ": " + getHealth());
        p.takeDamage(getDamage());
    }

    /** This method compiles basic action methods
     *  of a zombie and turns it into a behaviour.
     *
     *  @param plants the plant row to be checked
     */
    public void behaviour(Plant[] plants) {
        timer = (int)System.currentTimeMillis() / 1000;
        // while zombie isn't in the house and still alive
        //if ((timer - start_timer) >= rate) { // check first if the time matches the rate of action
            if (!this.isAtHouse() && this.isAlive()) {
                // if zombie is still not within attack range or there isn't any plants in front
                if (findFront(plants) == null || getCol() - findFront(plants).getCol() > 0.5) {
                    walk();
                }
                // else if a plant is in front of zombie
                else if (findFront(plants).isAlive()) {
                    //System.out.printf("plant in row %d col %d hp: %d\n", (int)findFront(plants).getRow(), (int)findFront(plants).getCol(), findFront(plants).getHealth());
                    eat(findFront(plants));
                }
            }

            //start_timer = timer;
        //}
    }

    /** This method finds the nearest plant that is facing the zombie.
     *  If there are no plants found that is in front of the zombie,
     *  then this method will return null.
     *
     *  @param plants the row of plants to be checked
     *  @return the nearest plant in front of the zombie if there is one,
     *  else return null
     */
    public Plant findFront(Plant[] plants) {
        int column = -1;
        float smallestDistance = 999;
        int i;

        for (i = 0; i < plants.length; i++) {
            // check plants that are only in front of zombie's current pos
            if (i <= (int)getCol() && plants[i] != null) {
                if (getCol() - plants[i].getCol() < smallestDistance) {
                    smallestDistance = getCol() - plants[i].getCol();
                    column = i;
                    //System.out.println("front plant " + plants[column].getCol());
                }
            }
        }

        if (column != -1)
            return plants[column];
        else
            return null;
    }

    public int getTimeBehavior() {
        int timer = (int)System.currentTimeMillis() / 1000;
        return timer - start_timer;
    }

    /** This method returns the current Zombie counts.
     *
     *  @return the number of Zombie objects created
     */
    public static int getCount() {
        return count;
    }

    /** This method decrements the count of Zombie objects by one.
     */
    public static void die() {
        count--;
    }

    /** How many zombies are created */
    private static int count = 0;
    private int rate = getSpeed();
    private int timer;
    private int start_timer = (int)System.currentTimeMillis() / 1000;
}

/*
class ZombieDriver {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

    public static void main(String[] args) {
        Lawn l = new Lawn(1,9);
        Plant[][] p = new Plant[1][9];
        ArrayList<Zombie> zz = new ArrayList<Zombie>();
        zz.add(new Zombie(0, 8));
        long start = System.currentTimeMillis();

        while (!zz.get(0).isAtHouse()) {
            if (System.currentTimeMillis() % 1000 == 0) {
                System.out.println("Time: " + (int) (System.currentTimeMillis() - start) / 1000);
                System.out.println("Zombie timer: " + zz.getFirst().getTimeBehavior());
                l.displayLawn(p, zz);

            }
            zz.get(0).behaviour(p[0]);
        }

        System.out.println("Zombie took " + (int) (System.currentTimeMillis() - start) / 1000 + " seconds to get to the end");
    }
}
*/