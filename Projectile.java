/** The class Projectile represents a projectile fired by
 * a plant object. It is responsible for handling its 
 * movement and dealing damage to a zombie object.  
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */

public class Projectile {

    /**
     * This constructor initializes the attributes
     * of a projectile such as its positions, damage, 
     * and speed. It also sets the projectile as active. 
     * 
     * 
     * @param r row position of  a projectile 
     * @param c column position of a projectile
     * @param dmg damage it deals to another object
     * @param s speed of projectile 
     */
    Projectile(float r, float c, int dmg, float s)
    {
        col = c;
        speed = s;
        damage = dmg;
        isActive = true;
    }

    /**
     * This method is responsible for dealing damage
     * to its targeted zombie object. Once it hits its 
     * target, it deactivates the projectile, allowing it
     * to be removed. 
     * 
     * 
     * @param z target Zombie object
     */
    public void hitEnemy(Zombie z)
    {
        z.takeDamage(damage);
        deactivateProjectile();

        System.out.println("Projectile has hit zombie at ( " + z.getRow() + ", " + z.getCol() + ")");
    }

    /**
     * This method is responsible for the movement
     * of a projectile after it is released. 
     * 
     */
    public void moveProjectile()
    {
        float pos = getColumn();

        pos += (float)(1.0 / speed);
        setColumn(pos);
    }

    /**
     * This method deactivates a projectile, allowing it to
     * be removed from the game. It is usually used when 
     * it has already hit a zombie object or has gone
     * out of the game grid. 
     * 
     */
    public void deactivateProjectile()
    {
        isActive = false;
    }

    /**
     * 
     * This method returns the active status of 
     * a projectile. 
     * 
     * @return true if projectile is still active, 
     * false otherwise. 
     */
    public boolean isActive()
    {
        return isActive;
    }

    /**
     * This method sets the columns position
     * of a projectile object
     * 
     * 
     * @param c column position of projectile
     */
    public void setColumn(float c)
    {
        col = c;
    }

    /**
     * 
     * This method returns the current column
     * position of a projectile. 
     * 
     * @return column position of projectile
     */
    public float getColumn()
    {
        return col;
    }

    /**
     * This method returns the current row position
     * of a projectile. 
     * 
     * @return row position of projectile
     */
    public float getRow()
    {
        return row;
    }

    /**row position of projectile */
    private float row;
    /**column position of projectile */
    private float col;
    /**value of damage dealt by projectile */
    private int damage;
    /**how fast the projectile moves once released */
    private float speed;
    /**flag to check if projectile is still active or not*/
    private boolean isActive;
}
