/** This class represents a sun, the currency of the player
 *  that allows them to plant seeds in their lawn.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Sun extends GameElement {
    /** This constructor instantiate its initial row and col positions,
     *  the target row to land on and its creation time. This also
     *  determines if it came from the sky or not.
     *
     * @param row the row coordinate of the sun
     * @param col the col coordinate of the sun
     * @param fromSky determines if it came from the sky
     * @param targetRow the target row to land on
     * @param time time of creation
     */
    public Sun(float row, float col, boolean fromSky, float targetRow, int time)
    {
        super(row, col, time);
        this.fromSky = fromSky;
        this.targetRow = targetRow;
        landedTime = -888;
    }

    /** This constructor instantiate its initial row and col positions,
     *  and its time of creation. It also determines if it came from the sky.
     *
     * @param row the row coordinate of the sun
     * @param col the col coordinate of the sun
     * @param fromSky determines if it came from the sky
     * @param time time of creation
     */
    public Sun(float row, float col, boolean fromSky, int time)
    {
        super(row, col, time);
        targetRow = row; //row: 1, col: 2, targetRow = row
        this.fromSky = fromSky;
        landedTime = -888;
    }

    /** This is essentially the behavior method of the sun.
     *  It updates its current position and state.
     *
     * @param currentTime the current time reference of the game
     */
    public void update(int currentTime)
    {
        if(fromSky) //if sun is from the sky
        {
            fallFromSky(currentTime);
        }

        landAtTargetRow(currentTime);
        disappear(currentTime);
    }

    /** This updates the position of the sun going down.
     *
     * @param currentTime the current time reference of the game
     */
    public void fallFromSky(int currentTime)
    {
        
        float currentRow = getRow();

        if(fromSky && !hasLanded() && (currentTime - getInternal_Time() >= 1))
        {
            currentRow += 1f / FALLING_SPEED;
            setRow(currentRow);
            System.out.println("Sun is falling at (" + (getRow()+1) + ", " + (getCol()+1) + ")");
        }
    }

    /** This initializes the position state of the sun if it has
     *  landed or not. This also initializes the landTime of the sun.
     *  and changes it's row to the targetRow.
     *
     * @param currentTime the current time reference of the game
     */
    public void landAtTargetRow(int currentTime) //where it will land or its final position
    {
        if(hasLanded() && landedTime < 0)
        {
            setRow(targetRow);
            landedTime = currentTime;
            fromSky = false;
            System.out.println("Sun is has landed in (" + (getRow()+1) + ", " + (getCol()+1) + ")");
        }
    }

    /** This method deactivates the active state of the sun object
     *  after 10 seconds when landed.
     *
     * @param currentTime the current time reference of the game
     */
    public void disappear(int currentTime)
    {
        //after 10 seconds, sun should deactivate/disappear
        if(landedTime >= 0)
        {
            //if it's time for sun to disappear
            if(currentTime >= (LIFETIME + landedTime))
            {
                deactivate();
                System.out.println("Sun has disappeared!");
            }
            else
            {
                System.out.println("Sun has " + ((landedTime + LIFETIME) - currentTime) + " remaining seconds before it disappears.");
            }
        }
    }

    /** This method determines if the sun object have landed or not.
     *
     * @return true if the sun object have landed, else false
     */
    public boolean hasLanded()
    {
        return getRow() >= targetRow;
    }

    /** This method returns the value of the sun object.
     *
     * @return the value of the sun object
     */
    public int getAmount()
    {
        return AMOUNT;
    }

    /**the value of the sun*/
    private final int AMOUNT = 25;
    /**the speed of the sun falling down*/
    private final float FALLING_SPEED = 2f;
    /**the uptime of the sun object*/
    private final int LIFETIME = 10;
    /**determines if it came from the sky*/
    private boolean fromSky;
    /**the target row of the sun when falling down*/
    private float targetRow;
    /**the time when the sun landed*/
    private int landedTime;
}