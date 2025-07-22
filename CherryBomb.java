import java.util.ArrayList;

/**
 * This class represents a Cherry Bomb plant. 
 * Once used, it will immediately explode and cause high
 * damage to its surrounding enemies. 
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class CherryBomb extends Plant {

    /**
     * This constructor initializes all the attributes
     * of a Cherry Bomb object such as its position, internal
     * time, and initial stats. 
     * 
     * 
     * @param r row position
     * @param c column position
     * @param t time of its creation
     */
    public CherryBomb(int r, int c, int t)
    {
        super(r,c,t);
        initializeStats();
    }

    /** This method initializes the attributes of the
     *  parent class.
     *
     */
    public void initializeStats()
    {
        setName("Cherry Bomb");
        setDamage(1800);
        setCost(150);
        setCooldown(50);
        setSpeed(2);
        setHealth(100);
        setRange(1);
    }
    /**
     * This method performs the behavior of a Cherry Bomb
     * plant. Once created, it has limited time before it explodes, 
     * and sets its health to 0. 
     * 
     * @param level the level to be checked
     * @param currentTime the current time for reference
     */
    @Override
    public void plantBehavior(Level level, int currentTime)
    {
        
        if(isTime(currentTime))
        {
            explode(level.getEnemies());
            setHealth(0);
        }
    }

    /**
     * This method allows the Cherry Bomb to deal damage to 
     * the zombies within its range. 
     * 
     * 
     * @param zombies zombies to be checked
     */
    public void explode(ArrayList<Zombie> zombies)
    {
        
        int i = 0;

        //System.out.println("BOOM!");
        while(i < zombies.size())
        {
            if(isWithinRange(zombies.get(i).getRow(), zombies.get(i).getCol()))
            {
                zombies.get(i).takeDamage(getDamage());
            }
            i++;
        }
        
    }

    /**
     * This method checks if the given position is within the 
     * range of a Cherry Bomb plant object. 
     * 
     * 
     * @param row row position of zombie
     * @param col column position of zombie
     * @return true if the position is within the plant's range, 
     * false otherwise. 
     */
    public boolean isWithinRange(float row, float col)
    {
        
        boolean rangedRow = false, rangedCol = false;

        if((row >= getRow() - getRange()) && (row <= getRow() + getRange()))
        {
            rangedRow = true;
        }
        if((col >= getCol() - getRange()) && (col <= getCol() + getRange()))
        {
            rangedCol = true;
        }

        return rangedRow && rangedCol;
    } 

    /**
     * This method checks to see when the Cherry Bomb will
     * explode once it is created. 
     * 
     * @param currentTime current time of the levek being played
     * @return true if it has already reached its time limit, false otherwise
     */
    public boolean isTime(int currentTime)
    {
        return currentTime - getInternal_time() >= getSpeed();
    }


    
}
