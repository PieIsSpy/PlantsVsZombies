import java.util.ArrayList;

/** The class SlownessProjectile represents a projectile fired by
 * a snow pea object. It is responsible for handling its
 * movement and dealing damage and slowness debuff to a zombie object.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.1
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
     * @param currentTime the timeframe where the zombie gets hit
     */
    public void hit(Zombie z, int currentTime)
    {
        z.takeDamage(getDamage());
        z.setSlowed(true);
        z.setSlowedStart(currentTime);
        deactivate();
    }

    /**
     * This method handles the logic of the projectile
     * after a peashooter releases/shoots it. If it hits
     * the zombie object, it will be removed from the
     * list of projectiles. Otherwise, it will continue
     * moving. The current time reference is used to
     * know if the projectile should be updating or not.
     *
     * @param enemies list of zombie objects
     * @param currentTime the current time reference
     */
    @Override
    public void update(ArrayList<Zombie> enemies, int currentTime)
    {
        int x;

        //loops through the list of enemies within the same row
        for(x = 0; x < enemies.size() && !getHitStatus(); x++)
        {
            //if zombie is alive and vulnerable and projectile is within range of attack
            if(enemies.get(x).isAlive() && (enemies.get(x).getCol() - getCol()) < 0.5 && getRow() == enemies.get(x).getRow())
            {
                hit(enemies.get(x), currentTime);
                setHitStatus(true);
            }

        }

        //if it has not hit any zombie yet, it will continue moving
        if(!getHitStatus())
        {
            move(currentTime);
        }
    }
}
