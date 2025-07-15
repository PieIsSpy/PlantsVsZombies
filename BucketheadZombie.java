/** This class represents the Buckethead Zombie variant
 *  of the Zombie class. It is highly resistant to damage.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class BucketheadZombie extends Zombie{
    /** This constructor initializes the zombie's row and column position,
     *  and the item it is holding.
     *
     * @param r the row coordinate of the zombie
     * @param c the col coordinate of the zombie
     */
    public BucketheadZombie(float r, float c) {
        super(r, c, new Item("Bucket", 420));
    }

    @Override
    public void sprite_animation() {

    }
}
