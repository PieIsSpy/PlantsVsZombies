
public class Sun extends GameElement {

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
            System.out.println("Sun is falling at (" + getRow() + ", " + getCol() + ")");
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
            System.out.println("Sun is has landed in (" + getRow() + ", " + getCol() + ")");
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
            
                System.out.println("Sun has " + ((landedTime + LIFETIME) - currentTime) + " remaining seconds before it disappears.");
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

   /*  public static void main(String[] args)
    {
        Sun sun = new Sun(2, 3, false);
        int i;

        for(i = 0; i < 20 && sun.isActive(); i++)
        {
            sun.update(i);

        }
    }
    */

    private final int AMOUNT = 25;
    private final float FALLING_SPEED = 2f;
    private final int LIFETIME = 10;
    private boolean fromSky;
    private float targetRow;
    private int landedTime;



}