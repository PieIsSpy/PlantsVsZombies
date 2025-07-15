/**
 * This class represents the Tombstone entity of the game.
 * This spawns a random variant zombie at the end of the wave
 * in its position.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Tombstone extends Entity {
    /** This constructor initializes the basic stats of the tombstone entity
     *  and its position.
     *
     * @param r the row position of the tombstone
     * @param c the col position of the tombstone
     */
    public Tombstone (int r, int c) {
        super(270, 0, 0, r, c, 0);
    }

    /** This method spawns a random zombie with a set probability of being a variant zombies
     *  in its occupied tile.
     */
    public Zombie spawn(int currentTime) {
        float x = (float)Math.random();

        if (x > .5f)
            return new Zombie((int)getRow(), (int)getCol(), currentTime);
        else if (x >= .3f)
            return new ConeheadZombie((int)getRow(), (int)getCol(), currentTime);
        else if (x >= .15f)
            return new BucketheadZombie((int)getRow(), (int)getCol(), currentTime);
        else
            return new PolevaulterZombie((int)getRow(), (int)getCol(), currentTime);
    }
}