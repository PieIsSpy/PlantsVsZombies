/** This class represents an Item that can be held by
 *  variant zombies.
 *
 * @author Karl Deejay Q. Omandac
 * @author Rachel Angeline Alba
 * @version 1.0
 */
public class Item {
    /** This constructor initializes the name, durability of an item,
     *  and its stat changes to a zombie. This also makes the item breakable.
     *
     * @param n the name of the item
     * @param h the durability of the item
     * @param s the speed stat change to a zombie holding this item
     * @param d the damage stat change to a zombie holding this item
     */
    public Item (String n, int h, int s, int d) {
        NAME = n;
        durability = h;
        breakableStatus = true;
        speedChange = s;
        damageChange = d;
    }

    /** This constructor initializes the name of an item and
     *  its stat changes to a zombie. Since the durability of
     *  the item is not initialized, the item is made to be unbreakable.
     *
     * @param n the name of the item
     * @param s the speed stat change to a zombie holding this item
     * @param d the damage stat change to a zombie holding this item
     */
    public Item (String n, int s, int d) {
        NAME = n;
        breakableStatus = false;
        speedChange = s;
        damageChange = d;
    }

    /** This method returns the name of the item.
     *
     * @return the name of the item
     */
    public String getNAME() {
        return NAME;
    }

    /** This method returns the speed stat change
     *  of a zombie. If the int is positive, the speed
     *  increases. Otherwise, the speed decreases.
     *
     * @return the speed stat change of a zombie
     */
    public int getSpeedChange() {
        return speedChange;
    }

    /** This method returns the damage stat change
     *  of a zombie. If the int is positive, the damage
     *  increases. Otherwise, the damage decreases.
     *
     * @return the damage stat change of a zombie
     */
    public int getDamageChange() {
        return damageChange;
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
    /** The change in speed when equipped*/
    private int speedChange;
    /** The change in damage when equipped*/
    private int damageChange;
}
