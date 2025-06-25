
/** This class represents a Sunflower entity
 *  that inherits a Plant class. This plant
 *  produces suns in a certain amount of rate.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */

public class Sunflower extends Plant{

    public Sunflower(float r, float c, int t)
    {
        super(r, c, t);
        initializeStats();
    }

    public void initializeStats()
    {
        setName("Sunflower");
        setCost(50);
        setCooldown(5);
        setHealth(100);
        setSpeed(20);
    }

    @Override
   public void plantBehavior(Level level, int currentTime)
   {
        //same logic as peashooter

        //generates sun every 20 seconds
        if (currentTime - getInternal_Time() < getSpeed()) {
            System.out.println("Sunflower has " + (getSpeed() - (currentTime - getInternal_Time())) + " remaining seconds to produce another sun");
        }
        else {
            System.out.println("Sunflower has produced a sun!");
            level.addSun(this, currentTime);
            setInternal_time(currentTime);
        }
   }
}
