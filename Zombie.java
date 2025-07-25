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
     *  any items. By default, a zombie is vulnerable, meaning it can be
     *  damaged. This also increments the static variable
     *  "count" by 1.
     *
     *  @param r the row grid position of the Zombie
     *  @param c the col grid position of the zombie
     *  @param t the time of creation
     */
    public Zombie(int r, int c, int t) {
        super(70, 4,10, r, c, t);
        held_item = null;
        vulnerability = true;
        slowed = false;
        isEating = false;

        count++;
    }

    /** This constructor is made for the variant zombies that will
     *  be holding items. This places them into a specific row and col
     *  and initializes their internal clock to keep track of their action
     *  timer. By default, the zombie is vulnerable, meaning it can be
     *  damaged. This also increments the static variable "count" by 1.
     *
     * @param r the row grid position of the Zombie
     * @param c the col grid position of the Zombie
     * @param t the time of creation
     * @param i the held item of the Zombie
     */
    public Zombie (int r, int c, int t, Item i) {
        super(70, 4, 10, r, c, t);
        held_item = i;
        vulnerability = true;
        slowed = false;
        isEating = false;

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
        // check if it has a breakable item
        if (held_item != null && held_item.isBreakable()) {
            held_item.takeDamage(d);

            // if the held item's durability is less than or equal to 0, subtract the underflow to zombie's current health and delete it
            if (held_item.getDurability() <= 0) {
                int underflow = -held_item.getDurability();
                int cur = getHealth();
                cur -= underflow;
                setHealth(cur);

                held_item = null;
            }
        }
        // otherwise, just damage it directly
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
        int speedChange = 0;

        if (held_item != null)
            speedChange = held_item.getSpeedChange();

        if (!slowed)
            cur -= (float) (1.0 / (getSpeed() - speedChange));
        else
            cur -= (float) (1.0 / ((getSpeed() - speedChange) * 2));

        setCol(cur);
        isEating = false;
    }

    /** This method makes the zombie eat the plant that is
     *  in front of it, dealing damage.
     *
     *  @param p the target plant object to be damaged
     */
    public void eat(Entity p) {
        //System.out.println("Eating " + p.getName() + ": " + p.getHealth());
        int damageChange = 0;

        if (held_item != null)
            damageChange = held_item.getDamageChange();

        p.takeDamage(getDamage() + damageChange);
        isEating = true;
    }

    /** This method compiles basic action methods
     *  of a zombie and turns it into a behaviour.
     *
     * @param plants the plant row to be checked
     * @param currentTime the current time reference of the game
     */
    public void behaviour(Entity[] plants, int currentTime) {
        // check if the zombie is slowed or not
        defrost(currentTime);

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
                // case 1: if not slowed
                if (!slowed && currentTime - getInternal_time() >= 0.5) { // zombie should eat at a certain rate
                    eat(findFront(plants));
                    setInternal_time(currentTime);
                    //System.out.println("Damaged " + findFront(plants).getName() + " at (" + findFront(plants).getRow() + ", " + findFront(plants).getCol() + ")");
                }
                // case 2: if slowed
                else if (slowed && currentTime - getInternal_time() >= 3) {
                    eat(findFront(plants));
                    setInternal_time(currentTime);
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
    public Entity findFront(Entity[] plants) {
        int column = -1;
        float smallestDistance = 999;
        int i;

        for (i = 0; i < plants.length; i++) {
            // check plants that are only in front of zombie's current pos
            if (plants[i] != null && plants[i] instanceof Plant && plants[i].getCol() <= (int)getCol()) {
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

    public void defrost(int t) {
        if (slowed && t - slowedStart >= 10)
            slowed = false;
    }

    public void sprite_animation() 
    {
        
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

    /** This method returns the held item of the zombie.
     *
     * @return the held item of the zombie
     */
    public Item getHeld_item() {
        return held_item;
    }

    /** This method sets the held item of the zombie.
     *
     * @param i the item to be held by a zombie
     */
    public void setHeld_item(Item i) {
        held_item = i;
    }

    /** This method checks if the zombie is vulnerable
     *  or not
     *
     * @return true if the zombie is vulnerable, false otherwise
     */
    public boolean isVulnerable() {
        return vulnerability;
    }

    /** This method sets the vulnerability state of
     *  the zombie
     *
     * @param v the vulnerability state of the zombie
     */
    public void setVulnerability(boolean v) {
        vulnerability = v;
    }

    public boolean isSlowed() {
        return slowed;
    }

    public void setSlowed(boolean s) {
        slowed = s;
    }

    public void setSlowedStart(int t) {
        slowedStart = t;
    }

    public void setIsEating(boolean b)
    {
        isEating = b;
    }

    public boolean getIsEating()
    {
        return isEating;
    }


    /** How many zombies are created */
    private static int count = 0;
    /** What items are they currently holding */
    private Item held_item;
    /** Checks if the zombie can be hit or not */
    private boolean vulnerability;
    /** Checks if the zombie is slow or not */
    private boolean slowed;
    /** Time reference where the zombie started going slow */
    private int slowedStart;
    private boolean isEating;
}