/** The class Projectile represents a projectile fired by
 * a plant object. It is responsible for handling its 
 * movement and dealing damage to a zombie object.  
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Projectile extends GameElement{

    /**
     * This constructor initializes the attributes
     * of a projectile such as its positions, damage, 
     * and speed. It also sets the projectile as active. 
     * 
     * 
     * @param row row position of  a projectile
     * @param col column position of a projectile
     * @param time the time of creation of the projectile
     * @param dmg damage it deals to another object
     * @param s speed of projectile 
     */
    Projectile(float row, float col, int time, int dmg, float s)
    {
        super(row, col, time);
        speed = s;
        damage = dmg;
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
    public void hit(Zombie z)
    {
        z.takeDamage(damage);
        deactivate();

        System.out.println("Projectile has hit zombie at (" + z.getRow() + ", " + z.getCol() + ")");
    }

    /**
     * This method is updates the movement
     * of a projectile after it is released. 
     * 
     */
    public void update(int currentTime)
    {
        float pos = getCol();

        if (currentTime - getInternal_Time() >= 1) {
            pos += (float) (1.0 / speed);
            setCol(pos);
            setInternal_time(currentTime);
            //System.out.println("Projectile at (" + (int)getRow() + "," + (int)getCol() + ")");
        }
    }


    /**value of damage dealt by projectile */
    private int damage;
    /**how fast the projectile moves once released */
    private float speed;
}