import javax.swing.ImageIcon;

/** This class represents a Sunflower entity
 *  that inherits a Plant class. This plant
 *  produces suns in a certain amount of rate.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */

public class Sunflower extends Plant{

    /**
     *  This constructor initializes the row and column coordinates of the
     *  sunflower and the time of its creation. This also assigns default
     *  stats needed to function.
     *  @param r the row coordinate of the sunflower
     *  @param c the col coordinate of the sunflower
     *  @param t the time of creation
     *  
     */
    public Sunflower(float r, float c, int t)
    {
        super(r, c, t);
        initializeStats();

    }


    /** This method initializes the default stats of the sunflower.
     *
     */
    public void initializeStats()
    {
        setName("Sunflower");
        setCost(50);
        setCooldown(7);
        setHealth(100);
        setSpeed(20);
    }

    /** This function manages the behavior of a Sunflower 
     * object. For every given interval, it produces a sun
     * or in this case it adds a sun to the main game's array 
     * list of Sun objects. 
     *
     * @param level the level to access its Sun objects
     * @param currentTime the current time of the game
     */
    @Override
   public void plantBehavior(Level level, int currentTime)
   {
    
       if (currentTime - getInternal_time() >= getSpeed()) {
           level.getSuns().add(new Sun(getRow(), getCol(), currentTime));
           level.setUnclaimed_suns(level.getUnclaimed_suns() + 25);
           setInternal_time(currentTime);
           System.out.println("Sunflower has produced sun!");
       }
   }
}
