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
     *  This also initializes its internal clock to keep
     *  track whether to do the action or not. This constructor
     *  can also be used by variant zombies that will not be holding
     *  any items. This also increments the static variable
     *  "count" by 1.
     *
     *  @param r the row grid position of the Zombie
     *  @param c the col grid position of the zombie
     *  @param t the time of creation
     */
    public Zombie(int r, int c, int t) {
        super(70, 4,10, r, c, t);
        held_item = null;

        count++;
    }

    /** This constructor is made for the variant zombies that will
     *  be holding items. This places them into a specific row and col
     *  and initializes their internal clock to keep track of their action
     *  timer. This also increments the static variable "count" by 1.
     *
     * @param r the row grid position of the Zombie
     * @param c the col grid position of the Zombie
     * @param t the time of creation
     * @param i the held item of the Zombie
     */
    public Zombie (int r, int c, int t, Item i) {
        super(70, 4, 10, r, c, t);
        held_item = i;

        count++;
    }

    /** This method subtracts the health of a zombie if
     *  they have no held items or the held item is not
     *  breakable. However, if they have a breakable item,
     *  it will take damage in place instead.
     *
     *  @param d the damage input to a zombie
     */
    @Override
    public void takeDamage(int d) {
        if (held_item != null && held_item.isBreakable()) {
            held_item.takeDamage(d);

            if (held_item.getDurability() == 0)
                held_item = null;
        }
        else {
            int cur = getHealth();
            cur -= d;

            if (cur < 0)
                setHealth(0);
            else
                setHealth(cur);
        }
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
        System.out.println("Eating " + p.getName() + ": " + p.getHealth());
        p.takeDamage(getDamage());
    }

    /** This method compiles basic action methods
     *  of a zombie and turns it into a behaviour.
     *
     * @param plants the plant row to be checked
     * @param currentTime the current time reference of the game
     */
    public void behaviour(Plant[] plants, int currentTime) {
        // while zombie isn't in the house and still alive
        if (!this.isAtHouse() && this.isAlive()) {
            // if zombie is still not within attack range or there isn't any plants in front
            if (findFront(plants) == null || getCol() - findFront(plants).getCol() > 0.5) {
                if (currentTime - getInternal_time() >= 1) { // zombie should walk at a certain rate
                    walk();
                    setInternal_time(currentTime);
                }
            }
            // else if a plant is in front of zombie
            else if (findFront(plants).isAlive()) {
                if (currentTime - getInternal_time() >= 0.5) { // zombie should eat at a certain rate
                    eat(findFront(plants));
                    setInternal_time(currentTime);
                    System.out.println("Damaged " + findFront(plants).getName() + " at (" + findFront(plants).getRow() + ", " + findFront(plants).getCol() + ")");
                }
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
            if (plants[i] != null && plants[i].getCol() <= (int)getCol()) {
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

    public Item getHeld_item() {
        return held_item;
    }

    public void setHeld_item(Item i) {
        held_item = i;
    }

    /** How many zombies are created */
    private static int count = 0;
    /** What items are they currently holding */
    private Item held_item;
}