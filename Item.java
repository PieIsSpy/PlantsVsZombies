/** This class represents an Item that can be held by
 *  variant zombies.
 *
 * @author Karl Deejay Q. Omandac
 * @author Rachel Angeline Alba
 * @version 1.0
 */
public class Item {
    /** This constructor initializes the name and durability of an item.
     *  This also makes the item breakable.
     *
     * @param n the name of the item
     * @param d the durability of the item
     */
    public Item (String n, int d) {
        NAME = n;
        durability = d;
        breakableStatus = true;
    }

    /** This constructor initializes the name of an item.
     *  Since the durability of the item is not initialized,
     *  the item is made to be unbreakable.
     *
     * @param n the name of the item
     */
    public Item (String n) {
        NAME = n;
        breakableStatus = false;
    }

    /** This method returns the name of the item.
     *
     * @return the name of the item
     */
    public String getNAME() {
        return NAME;
    }

    /** This method returns the durability of the item.
     *
     * @return the durability of the item
     */
    public int getDurability() {
        return durability;
    }

    /** This method checks if the item is breakable or not
     *
     * @return true if the item is breakable, otherwise false
     */
    public boolean isBreakable() {
        return breakableStatus;
    }

    /** This method sets the durability of the item.
     *
     * @param d the current durability of the item
     */
    public void setDurability(int d) {
        durability = d;
    }

    /** This method damages the durability of the item.
     *
     * @param d the amount of damage to the item
     */
    public void takeDamage(int d) {
        if (breakableStatus)
            durability -= d;

        if (durability < 0)
            durability = 0;
    }

    /** The name of the item */
    private final String NAME;
    /** The durability of the item */
    private int durability;
    /** Determines if the item can be broken down */
    private boolean breakableStatus;
}
