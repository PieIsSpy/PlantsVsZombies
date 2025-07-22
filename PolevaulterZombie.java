/** This class represents the Pole Vaulter Zombie variant
 *  of the Zombie class. It uses a long pole in order to
 *  vault over the first plant it is in range with.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class PolevaulterZombie extends Zombie{
    /** This constructor initializes the zombie's row and column position,
     *  its time of creation and the item it is holding.
     *
     * @param r the row coordinate of the zombie
     * @param c the col coordinate of the zombie
     * @param t the time of its creation
     */
    public PolevaulterZombie(int r, int c, int t) {
        super(r, c, t, new Item("Pole",1,0));
    }

    /** This method compiles basic action methods
     *  of a zombie and turns it into a behaviour.
     *  It also discards the item when used.
     *
     * @param plants the plant row to be checked
     * @param currentTime the current time reference of the game
     */
    @Override
    public void behaviour(Entity[] plants, int currentTime) {
        System.out.println("isVulnerable:" + isVulnerable());
        if (!this.isAtHouse() && this.isAlive()) {
            // case 1: if zombie is still not within attack range or there isn't any plants in front
            if (findFront(plants) == null || getCol() - findFront(plants).getCol() > 1) {
                // should be vulnerable
                if (!isVulnerable())
                    setVulnerability(true);

                // zombie should walk at a certain rate
                if (currentTime - getInternal_time() >= 1) {
                    walk();
                    setInternal_time(currentTime);
                }
            }
            // case 2: if a plant is in front of zombie and the zombie is still holding the item
            else if (findFront(plants).isAlive() && getHeld_item() != null) {
                vaultOver(currentTime);
            }
            //case 3: if a plant is in front of zombie but does not have an item
            else if (findFront(plants).isAlive()) {
                if (!isVulnerable())
                    setVulnerability(true);

                // case 1: if not slowed
                if (!isSlowed() && currentTime - getInternal_time() >= 0.5) { // zombie should eat at a certain rate
                    eat(findFront(plants));
                    setInternal_time(currentTime);
                }
                // case 2: if slowed
                else if (isSlowed() && currentTime - getInternal_time() >= 3) {
                    eat(findFront(plants));
                    setInternal_time(currentTime);
                }
            }
        }
    }

    /** This method makes the zombie invulnerable for a few seconds, skipping over a tile containing a plant.
     *
     * @param currentTime the current time frame of the game
     */
    public void vaultOver(int currentTime) {
        // save the col where it did the action first
        colUsage = getCol();

        // should be invulnerable
        if (isVulnerable())
            setVulnerability(false);

        // zombie should move at a certain rate
        if (currentTime - getInternal_time() >= 1) {
            walk();
            setInternal_time(currentTime);
        }

        // after vaulting over, remove item and give normal speed, then be vulnerable again
        if (colUsage - getCol() >= 1) {
            setVulnerability(true);
            setHeld_item(null);
        }
    }

    @Override
    public void sprite_animation() {

    }

    /** This saves the column where the zombie used its item */
    private float colUsage;
}