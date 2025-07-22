/** This class represents the Snow Pea plant, which is a child class
 *  of Peashooter class. This defines how snow pea plants behave towards
 *  zombie enemies and slows them down when its projectiles hit them.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class SnowPea extends Peashooter{
    /** This constructor initializes the attributes of a snow pea
     *  plant and places it into a given row and col position.
     *  This also initializes the time of its creation.
     *
     * @param r the row coordinates of the plant
     * @param c the col coordinates of the plant
     * @param currentTime the time of its creation
     */
    public SnowPea(int r, int c, int currentTime) {
        super(r,c,currentTime);
        initializeStats();
    }

    /** This method initializes the attributes of the
     *  parent class.
     *
     */
    public void initializeStats()
    {
        setName("Snow Pea");
        setCost(175);
    }

    /**
     *
     * This method represents the shooting of a projectile
     * at its targeted zombie object. It adds it to the
     * peashooter's list of projectiles. If the target is within
     * a direct damage range, the projectile will deal an increased
     * amount of damage. The current time reference
     *
     * @param z the zombie to be targeted
     * @param currentTime the current time reference of the game
     * @param level the level to add peas projectile
     */
    @Override
    public void shoot(Zombie z, int currentTime, Level level)
    {
        //System.out.println("Pew!");
        if(isWithinDirectDamage(z.getCol()))
        {
            level.getPeas().add(new SlownessProjectile(getRow(), getCol(), currentTime, getDirectDamage(), 1.5f));
        }
        else if (isWithinRange(z.getCol()))
        {
            level.getPeas().add(new SlownessProjectile(getRow(), getCol(), currentTime, getDamage(), 1.5f));
        }
    }
}
