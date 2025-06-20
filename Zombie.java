/** The class Zombie represents a basic zombie object
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
        super(70, 10, r, c);

        count++;
    }

    /** This method checks if the Zombie is still alive.
     *
     *  @return true if the health is above 0, else false
     */
    public boolean isAlive() {
        return getHealth() > 0;
    }

    /** This method checks if the Zombie has already
     *  reached the player's house.
     *
     *  @return true if position[1] is less than or
     *  equal to 0, false otherwise
     */
    public boolean isAtHouse() {
        return getCol() <= 0;
    }

    /** This method moves the Zombie to the left of the
     *  lawn at speed variable seconds per grid.
     */
    public void walk() {
        float cur = getCol();
        cur -= (float) (1.0 / speed);
        setCol(cur);
    }

    /** This method makes the zombie eat the plant that is
     *  in front of it, dealing damage.
     *
     *  @param p the target plant object to be damaged
     */
    public void eat(Plant p) {
        p.takeDamage(getDamage());

        // temporary until Lawn.java have death mechanics already
        if (!p.isAlive())
            p = null;
    }

    /** This method compiles basic action methods
     *  of a zombie and turns it into a behaviour.
     *
     *  @param plants the plant row to be checked
     */
    public void behaviour(Plant[] plants) {
        // while zombie isn't in the house and still alive (this will be called repeatedly by Lawn.java
        if (!this.isAtHouse() && this.isAlive()) {
            // if zombie is still not within attack range or there isn't any plants in front
            if (findFront(plants) == null || getCol() - findFront(plants).getColumn() > 0.5) {//!p.isAlive() || p == null) {
                System.out.printf("pos: row %d col %d\n", (int)getRow(), (int)getCol());
                walk();
            }
            // else if a plant is in front of zombie
            else if (findFront(plants).isAlive()){
                System.out.printf("plant hp: %d\n", findFront(plants).getHealth());
                eat(findFront(plants));
            }
        }
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
                if (getCol() - plants[i].getColumn() < smallestDistance) {
                    smallestDistance = getCol() - plants[i].getColumn();
                    column = i;
                    System.out.println("front plant " + plants[column].getColumn());
                }
            }
        }

        if (column != -1)
            return plants[column];
        else
            return null;
    }

    /** This method subtracts the health of the Zombie
     *  by d amount of damage. If the current health
     *  of a zombie underflows below 0, it automatically
     *  sets to 0.
     *
     *  @param d the damage input to a Zombie
     */
    public void takeDamage(int d) {
        int cur = getHealth();
        cur -= d;

        if (cur < 0)
            setHealth(0);
        else
            setHealth(cur);
    }

    /** This method returns the speed per grid
     *  of a Zombie.
     *
     *  @return the seconds per grid of a zombie
     */
    public int getSpeed() {
        return speed;
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

    /** How fast the zombie moves */
    private int speed;
    /** How many zombies are created */
    private static int count = 0;
}