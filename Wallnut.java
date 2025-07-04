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
        setCooldown(15);
        setHealth(200);
    }

    /**
     * This method checks if a Wallnut object has reached half
     * of its health. 
     * 
     * @return true if a Wallnut object has reached half of its health, 
     * false otherwise. 
     */
    public boolean isHalfHealth()
    {
        return getHealth() <= (getHealth()/2);
    }

    //wallnut changes appearance once it reaches half health
}
