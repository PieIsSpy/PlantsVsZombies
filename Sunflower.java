
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
        initializeStats();
        counter = 0;
        
    }

    public void initializeStats()
    {
        setName("Sunflower");
        setCost(50);
        setCooldown(5);
        setHealth(30);
        setSpeed(20);
    }


    @Override 
   public void plantBehavior(Level level) 
   {
        //same logic as peashooter

        //when does it generate sun?
        //generates sun every 20 seconds
        if(counter > 0)
        {
            //20 - counter
            counter--;
            System.out.println("Sunflower has " + counter + " remaining seconds to produce another sun");
        }

        if(counter == 0)
        {
            System.out.println("Sunflower has produced a sun!");
            level.addSun(this);
            counter = getSpeed();
        }


   }

   private int counter;
   

    
    
}
