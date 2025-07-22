/** This class represents the Buckethead Zombie variant
 *  of the Zombie class. It is highly resistant to damage.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class BucketheadZombie extends Zombie{
    /** This constructor initializes the zombie's row and column position,
     *  its time of creation and the item it is holding.
     *
     * @param r the row coordinate of the zombie
     * @param c the col coordinate of the zombie
     * @param t the time of its creation
     */
    public BucketheadZombie(int r, int c, int t) {
        super(r, c, t, new Item("Bucket", 420,-3,-5));
    }

    @Override
    public void sprite_animation() {

    }
}
