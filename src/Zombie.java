/** The class Zombie represents a basic zombie object
 *  that can be used by other child classes that represent
 *  variant zombies.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Zombie {
    /** This constructor initializes the default values
     *  of a basic zombie. This also increments the static variable
     *  "count" by 1.
     */
    public Zombie() {
        speed = 4;
        damage = 10;
        health = 70;
        count++;
    }

    public void walk() {

    }

    public void eat() {

    }

    public void die() {
        count--;
    }

    /** How fast the zombie moves */
    private int speed;
    /** How much damage it deals to the plant it is attacking */
    private int damage;
    /** How much damage it can sustain */
    private int health;
    /** How many zombies are created */
    private static int count = 0;
}
