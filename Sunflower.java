/** This class represents a Sunflower entity
 *  that inherits a Plant class. This plant
 *  produces suns in a certain amount of rate.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Sunflower extends Plant{

    public Sunflower(float r, float c)
    {
        super(r, c);
        setName("Sunflower");
        setCost(50);
        setCooldown(5);
        setHealth(30);
    }

    //how to handle sun generating action
    //how to make sun generate given the interval of 24 seconds?

    /** This method defines the behavior of the sunflower.
     *
     */
    @Override
    public void plantBehavior()
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
    private int count = 0;
}
