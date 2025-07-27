/** This class represents the Flag Zombie variant of the
 *  parent class Zombie. It has a flag that increases its speed by +1.
 *
 */
public class FlagZombie extends Zombie{
    /** This constructor initializes the row and column of the zombie.
     *
     * @param r the row coordinate
     * @param c the col coordinate
     * @param t the current time frame of the game
     */
    public FlagZombie (int r, int c, int t) {
        super(r, c, t, new Item("Flag", 1, 0));
    }

}
