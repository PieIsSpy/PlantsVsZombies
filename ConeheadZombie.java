/** This class represents the Conehead Zombie variant
 *  of the Zombie class. It uses a traffic cone to protect
 *  itself.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class ConeheadZombie extends Zombie{
    /** This constructor initializes the zombie's row and column position,
     *  its time of creation and the item it is holding.
     *
     * @param r the row coordinate of the zombie
     * @param c the col coordinate of the zombie
     * @param t the time of its creation
     */
    public ConeheadZombie(int r, int c, int t) {
        super(r, c, t, new Item("Traffic Cone", 140));
    }

    @Override
    public void sprite_animation() {

    }
}
