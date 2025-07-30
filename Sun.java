/** This class represents a sun, the currency of the player
 *  that allows them to plant seeds in their lawn.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Sun extends GameElement {

    /** This constructor instantiate its initial row and col positions,
     *  and its time of creation. It also determines if it came from the sky.
     *
     * @param row the row coordinate of the sun
     * @param col the col coordinate of the sun
     * @param time time of creation
     */
    public Sun(float row, float col, int time)
    {
        super(row, col, time);
        spawnTime = -888;
    }

    /** This is essentially the behavior method of the sun.
     *  It updates its current position and state.
     *
     * @param currentTime the current time reference of the game
     */
    @Override
    public void update(int currentTime)
    {
        
        if(spawnTime >= 0)
        {
            if(currentTime >= (LIFETIME + spawnTime))
            {
                deactivate();
                //System.out.println("Sun has disappeared!");
            } 
        }
        else
        {
            spawnTime = currentTime;
        }
    }

    /** This method returns the value of the sun object.
     *
     * @return the value of the sun object
     */
    public int getAmount()
    {
        return AMOUNT;
    }

    /** This method returns the time frame of the sun
     *  landing.
     *
     * @return the time frame of the sun landing
     */
    public int getSpawnTime() {
        return spawnTime;
    }

    /** This method returns the sun object count.
     *
     * @return the sun object count
     */
    public static int getCount() {
        return count;
    }

    /** This method decrements the sun object count
     *
     */
    public static void despawn() {
        count--;
    }

    /** This method sets a number to the sun object count
     *
     * @param s the number of suns present
     */
    public static void setCount(int s) {
        count = s;
    }

    /**the value of the sun*/
    private final int AMOUNT = 25;
    /**the uptime of the sun object*/
    private final int LIFETIME = 10;
    /**the time when the sun landed*/
    private int spawnTime;
    /**the amount of sun objects created*/
    private static int count = 0;
}