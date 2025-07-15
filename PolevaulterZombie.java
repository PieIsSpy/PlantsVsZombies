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
     *  and the item it is holding.
     *
     * @param r the row coordinate of the zombie
     * @param c the col coordinate of the zombie
     */
    public PolevaulterZombie(float r, float c) {
        super(r, c, new Item("Pole"));
        setSpeed(3);
    }

    /** This method compiles basic action methods
     *  of a zombie and turns it into a behaviour.
     *  It also discards the item when used.
     *
     * @param plants the plant row to be checked
     */
    @Override
    public void behaviour(Entity[] plants) {
        float currentTime = (float)(System.currentTimeMillis() / 1000.0);

        if (!this.isAtHouse() && this.isAlive()) {
            // case 1: if zombie is still not within attack range or there isn't any plants in front
            if (findFront(plants) == null || getCol() - findFront(plants).getCol() > 1) {
                // should be vulnerable
                if (!isVulnerable())
                    setVulnerability(true);

                // zombie should walk at a certain rate
                if (currentTime - getBehavior_interval() >= 1) {
                    walk();
                    setBehavior_interval(currentTime);
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
                if (!isSlowed() && currentTime - getBehavior_interval() >= 0.5) { // zombie should eat at a certain rate
                    eat(findFront(plants));
                    setBehavior_interval(currentTime);
                }
                // case 2: if slowed
                else if (isSlowed() && currentTime - getBehavior_interval() >= 3) {
                    eat(findFront(plants));
                    setBehavior_interval(currentTime);
                }
            }
        }
    }

    public void vaultOver(float currentTime) {
        // save the col where it did the action first
        colUsage = getCol();

        // should be invulnerable
        if (isVulnerable())
            setVulnerability(false);

        // zombie should move at a certain rate
        if (currentTime - getBehavior_interval() >= 1) {
            walk();
            setBehavior_interval(currentTime);
        }

        // after vaulting over, remove item and give normal speed, then be vulnerable again
        if (colUsage - getCol() >= 1) {
            setVulnerability(true);
            setHeld_item(null);
            setSpeed(4);
        }
    }

    @Override
    public void sprite_animation() {

    }

    /** This saves the column where the zombie used its item */
    private float colUsage;
}