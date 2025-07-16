/** The class SlownessProjectile represents a projectile fired by
 * a snow pea object. It is responsible for handling its
 * movement and dealing damage and slowness debuff to a zombie object.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class SlownessProjectile extends Projectile{
    /** This constructor initializes the attributes of a slowness projectile
     * such as its positions, damage and speed. It also sets the slowness projectile
     * active.
     *
     * @param row the row position of the slowness projectile
     * @param col the col position of the slowness projectile
     * @param time the time of creation of the slowness projectile
     * @param dmg the damage it deals to zombie objects
     * @param s the speed of the slowless projectile
     */
    public SlownessProjectile(float row, float col, int time, int dmg, float s) {
        super(row,col,time,dmg,s);
    }

    /**
     * This method is responsible for dealing damage
     * to its targeted zombie object. Once it hits its
     * target, it gives the zombie with a slowness debuff and
     * deactivates the projectile, allowing it
     * to be removed.
     *
     *
     * @param z target Zombie object
     */
    @Override
    public void hit(Zombie z)
    {
        z.takeDamage(getDamage());
        z.setSlowed(true);
        z.setSlowedStart((int)((System.currentTimeMillis())/1000) - getInternal_Time());
        deactivate();
    }
}
