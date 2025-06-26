
/** This class represents a Sun object which serves
 * as a player's way of buying Plant objects. There are 
 * two ways to obtain these kinds of objects. One is through a 
 * random spawn falling from the sky, and another is produced by 
 * a Sunflower object. 
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */

public class Sun extends GameElement {

    /**
     * This constructor initializes the attributes of 
     * a Sun object such as its initial posiitons, a 
     * 
     * 
     * @param row
     * @param c
     * @param fromSky
     * @param targetRow
     * @param time
     */
    public Sun(float row, float c, boolean fromSky, float targetRow, int time)
    {
        super(row, c, time);
        this.fromSky = fromSky;
        this.targetRow = targetRow;
        landedTime = -888;

    }

    public Sun(float row, float c, boolean fromSky, int time)
    {
        super(row, c, time);
        targetRow = row; //row: 1, col: 2, targetRow = row
        this.fromSky = fromSky;
        landedTime = -888;
    }

    public void update(int currentTime)
    {
        if(fromSky) //if sun is from the sky
        {
            fallFromSky(currentTime);
        }

        landAtTargetRow(currentTime);
        disappear(currentTime);
    }


    public void fallFromSky(int currentTime)
    {
        
        float currentRow = getRow();

        if(fromSky && !hasLanded() && (currentTime - getInternal_Time() >= 1))
        {
            currentRow += 1f / FALLING_SPEED;
            setRow(currentRow);
            System.out.println("Sun is falling at (" + (getRow() + 1) + ", " + (getCol() + 1) + ")");
        }
    }

    //true if sun has landed, false if not

    
    public void landAtTargetRow(int currentTime) //where it will land or its final position
    {
        if(hasLanded() && landedTime < 0)
        {
            setRow(targetRow);
            landedTime = currentTime;
            fromSky = false;
            System.out.println("Sun is now in (" + (getRow() + 1) + ", " + (getCol() + 1) + ")");
        }
        
    }

    //after 10 seconds, sun should deactivate/disappear

    public void disappear(int currentTime)
    {

        //landedTime 

        if(landedTime >= 0)
        {
            //if its time for sun to disappear
            if(currentTime >= (LIFETIME + landedTime))
            {
                deactivate();
                System.out.println("Sun has disappeared!");
            }
            else
            {
            
                System.out.println("Sun has " + ((landedTime + LIFETIME) - currentTime) + " remaining seconds beofre it disappears.");
            }

            
        }
        
    }

    public boolean hasLanded()
    {
        return getRow() >= targetRow;
    }

    public int getAmount()
    {
        return AMOUNT;
    }


    private final int AMOUNT = 25;
    private final float FALLING_SPEED = 2f;
    private final int LIFETIME = 10;
    private boolean fromSky;
    private float targetRow;
    private int landedTime;



}
