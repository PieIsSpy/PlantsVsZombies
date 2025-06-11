
public class Sunflower extends Plant{

    public Sunflower()
    {
        super("Sunflower", 50, 5, 100);
    }

    //how to handle sun generating action
    //how to make sun generate given the interval of 24 seconds?

    @Override 
   public void plantBehavior()
   {
        System.out.println("Sunflower has generated " + SUN_AMOUNT + " sun!");
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
