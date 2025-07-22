import java.util.ArrayList;

/** This class represents the Potato Mine plant.
 *  It takes 15 seconds to arm itself before blowing
 *  up anything that steps on it.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class PotatoMine extends Plant{
    /** This constructor initializes the row and col
     *  coordinates of the potato mine, as well as its internal
     *  time to keep track whether the mine is ready or not.
     *  It also initializes its default stats.
     *
     * @param r the row coordinate of the mine
     * @param c the col coordinate of the mine
     * @param t the time of its creation
     */
    public PotatoMine(int r, int c, int t) {
        super(r,c,t);
        initializeStats();
    }

    
    /** This method initializes the attributes of the
     *  parent class.
     *
     */
    public void initializeStats()
    {
        setName("Potato Mine");
        setDamage(700);
        setCost(25);
        setCooldown(30);
        setSpeed(15);
        setHealth(100);
        setRange(1);
    }

    /** This method compiles all the checks of the potato mine
     *  in order to determine if it has to blow up or not. This
     *  also sets its health to 0 the moment it blows up.
     *
     * @param level the level to be checked
     * @param currentTime the current time frame of the game
     */
    @Override
    public void plantBehavior(Level level, int currentTime) {
        if (isPrimed(currentTime) && isTriggered(level.getEnemies())) {
            //System.out.println("BOOM");
            blowUp(level.getEnemies());
            setHealth(0);
        }
    }

    /** This method damages all zombies in one singular tile.
     *
     * @param z all instance of zombies
     */
    public void blowUp(ArrayList<Zombie> z) {
        int i;

        for (i = 0; i < z.size(); i++)
            if (isWithinRange(z.get(i)))
                z.get(i).takeDamage(getDamage());
    }

    /** This method checks if the potato mine is ready to be used.
     *
     * @param currentTime the current time frame
     * @return true if it is already 15 seconds, false otherwise
     */
    public boolean isPrimed(int currentTime) {
        return currentTime - getInternal_time() >= getSpeed();
    }

    /** This method checks if a zombie have stepped on the mine.
     *
     * @param z all instance of zombies to be checked
     * @return true if atleast one zombie have stepped on the mine,
     * false otherwise
     */
    public boolean isTriggered(ArrayList<Zombie> z) {
        int i = 0;
        boolean condition = false;

        while (i < z.size() && !condition) {
            if (z.get(i).getRow() == getRow() && isWithinRange(z.get(i)))
                condition = true;
            else
                i++;
        }

        return condition;
    }

    /** This method checks if a zombie is within its tile range.
     *
     * @param z the zombie to be checked
     * @return true if the zombie is within tile range, false otherwise
     */
    public boolean isWithinRange(Zombie z) {
        return z.getCol() <= getCol() + 0.99 && z.getCol() >= getCol();
    }

    // change appearance based on primed status
}
