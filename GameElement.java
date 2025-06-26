/** This class is to be inherited by objects that exist in 
 * the game but do not carry entity related attributes such as
 * health or attack values. These shared attributes and 
 * methods are to be used by temporary game elements such
 * as projectile and sun objects. 
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */

public class GameElement {

    /**
     * This constructor initializes the position of 
     * the game element. It also sets it as an active
     * objective. 
     * 
     * @param r row position
     * @param c column position
     * @param t internal time of a gameElement
     */
    public GameElement(float r, float c, int t)
    {
        row = r;
        col = c;
        internal_time = t;
        isActive = true;
    }
 
    /**
     * This method updates the behavior or actions
     * of the game element for every given time. 
     * 
     */
    public void update()
    {
        
    }
    /**
     * This method deactivates a game element, allowing it to
     * be removed from the game. 
     * 
     */
    public void deactivate()
    {
        isActive = false;
    }

    /**
     * 
     * This method returns the active status of 
     * a game element. 
     * 
     * @return true if game element is still active, 
     * false otherwise. 
     */
    public boolean isActive()
    {
        return isActive;
    }

    /**
     * This method returns the current row position
     * of a game element. 
     * 
     * @return row position of game element. 
     */
    public float getRow()
    {
        return row;
    }

    /**
     * 
     * This method returns the current column
     * position of a game element. 
     * 
     * @return column position of projectile
     */
    public float getCol()
    {
        return col;
    }

    /**
     * This method returns the internal time of
     * a game element
     * 
     * @return internal time of game element
     */
    public int getInternal_Time() {
        return internal_time;
    }

    /**
     * This method sets the columns position
     * of a game element object
     * 
     * 
     * @param c column position of game element
     */
    public void setCol(float c)
    {
        col = c;
    }

    /**
     * This method sets the row position
     * of a game element object
     * 
     * 
     * @param r row position of game element
     */
    public void setRow(float r)
    {
        row = r;
    }

    /**
     * This method updates the internal time of
     * a game element in order to track the last time
     * it performed an action. 
     * 
     * @param t current game time to be set as the 
     * game element's internal time
     */
    public void setInternal_Time(int t) {
        internal_time = t;
    }

    /**row position of game element */
    private float row;
    /**column position of game element */
    private float col;
    /**active status of game element */
    private boolean isActive;
    /**internal time of the game element*/
    private int internal_time;

}
