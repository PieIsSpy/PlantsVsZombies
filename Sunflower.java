/** This class represents a Sunflower entity
 *  that inherits a Plant class. This plant
 *  produces suns in a certain amount of rate.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Sunflower extends Plant{
    /** This constructor initializes the name, cost, cooldown and health of a sunflower
     *
     */
    public Sunflower()
    {
        super("Sunflower", 50, 7, 300);
    }

    //how to handle sun generating action
    //how to make sun generate given the interval of 24 seconds?

    @Override 
   public void plantAction()
   {
        System.out.println("Sunflower has generated" + SUN_AMOUNT + " sun!");
   }

    public int getSUN_AMOUNT()
    {
        return SUN_AMOUNT;
    }

    public int getSUN_INTERVAL()
    {
        return SUN_INTERVAL;
    }

    private final int SUN_AMOUNT = 25; //how much sun it produces
    private final int SUN_INTERVAL = 24; //how fast it produces sun (seconds)
    
}
