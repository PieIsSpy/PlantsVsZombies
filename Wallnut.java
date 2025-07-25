/**
 * This class represents the behaviors of a Wallnut plant. It 
 * extends the Plant class and is known for its high health status, 
 * valuable in defending other Plant objects from nearby Zombie objects. 
 * 
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */

public class Wallnut extends Plant{

    /**
     * This constructor initializes the attributes of 
     * a Wallnut object. 
     * 
     * 
     * @param r row position of object
     * @param c column position of object
     * @param t the time of creation
     */
    public Wallnut(int r, int c, int t)
    {
        super(r, c, t);
        initializeStats();
    }

    /** 
     * This method initializes the attributes of the
     *  parent class.
     *
     */
    public void initializeStats()
    {
        setName("Wallnut");
        setCost(50);
        setCooldown(30);
        setHealth(200);
    }

    /** This method checks how much health does the wallnut
     *  approximately have.
     *
     * @return 0 if it is more than 2/3 of its health, 1
     * if it is more than 1/3 of its health, or 2 if it is less than 1/3
     * of its health
     */
    public int checkHealthState() {
        if (getHealth() > (200 * 2/3))
            return 0;
        else if (getHealth() > (200/3))
            return 1;
        else
            return 2;
    }
}
