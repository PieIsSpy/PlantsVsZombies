
import java.util.ArrayList;

/** The class Peashooter represents the behaviors of a peashooter plant.
 * It extends the Plant class and defines how it interacts with 
 * zombie objects whether by firing projectiles or detecting nearby
 * enemies. 
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Peashooter extends Plant {

    /**
     * This constructor initializes the attributes of a
     * Peashooter object and sets it to its given 
     * position. This also initializes the internal
     * clock of the peashooter to keep track of its behavior.
     * 
     * @param r row index of object
     * @param c column index of object
     * @param t the time of creation
     */
    public Peashooter(float r, float c, int t)
    {
        super(r, c, t);
        initializeStats();
        peas = new ArrayList<Projectile>();
    }

    /** This method initializes the attributes of the
     *  parent class.
     *
     */
    public void initializeStats()
    {
        setName("Peashooter");
        setCost(100);
        setCooldown(7);
        setRange(9);
        setDamage(5);
        setHealth(100);
        setDirectDamage(10);
        setDirectDamageRange(2);
        setSpeed(2);
        setProjectileSpeed(1.5f);
    }

    /**
     * This method is used to define the peashooter's 
     * behavior in response to zombie objects in the
     * level model class depending on the current
     * time reference.
     * 
     * @param level the level to check for zombies
     * @param currentTime the current time reference
     */
    @Override
    public void plantBehavior(Level level, int currentTime)
    {
        Zombie z = findFront(level.getEnemies());

        //System.out.println(z != null && isWithinRange(z.getCol()));
        if(z != null && isWithinRange(z.getCol()))
        {
            if (currentTime - getInternal_time() >= getSpeed()) {
                shoot(z, currentTime);
                setInternal_time(currentTime);
            }
        }

         projectileLogic(level.getEnemies(), currentTime);

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
    public void projectileLogic(ArrayList<Zombie> enemies, int currentTime)
    {
        int i = 0, x;
        boolean hasHit;

        //loops through the list of projectiles
        while(i < peas.size())
        {
            hasHit = false;
            //loops through the list of enemies within the same row
            for(x = 0; x < enemies.size() && !hasHit; x++)
            {
                //if zombie is alive and projectile is within range of attack 
                if(enemies.get(x).isAlive() && (enemies.get(x).getCol() - peas.get(i).getCol()) < 0.5)
                {
                    peas.get(i).hit(enemies.get(x));
                    hasHit = true;
                }
            
            }

            //if it has not hit any zombie yet, it will continue moving
            if(!hasHit)
            {
                peas.get(i).update(currentTime);
            }
            
            i++;
        }

        //removes the projectiles that have already hit the zombie
        removeInactiveProjectiles();
    }

    /**
     * This method removes all inactive projectiles
     * from the list. 
     * 
     */
    public void removeInactiveProjectiles()
    {
        int i;
        for(i = peas.size() - 1; i >= 0; i--)
        {
            if(!peas.get(i).isActive())
            {
                peas.remove(i);
            }
        }
    }

   
    /**
     * This method checks if an object is within
     * the range of peashooter to start an 
     * attack
     * 
     * @param target column index of target object
     * @return true if target is within attack range of 
     * peashooter
     */
    public boolean isWithinRange(float target)
    {
        return (target - getCol()) <= getRange() && target >= getCol();
        
    }
   
    /**
     * This method checks if the target object is within
     * a direct damage range of the peashooter. If so, 
     * peashooter deals increased damage. 
     * 
     * 
     * @param target column index of target object
     * @return true if target is within direct damage range
     * of peashooter, false otherwise. 
     */
    public boolean isWithinDirectDamage(float target)
    {
        return (target <= getCol() + getDirectDamageRange()) && target >= getCol();
    }

    /**
     * 
     * This method represents the shooting of a projectile
     * at its targeted zombie object. It adds it to the 
     * peashooter's list of projectiles. If the target is within
     * a direct damage range, the projectile will deal an increased
     * amount of damage. The current time reference
     * 
     * 
     * @param z zombie object targeted by projectile 
     */
    public void shoot(Zombie z, int currentTime)
    {
        System.out.println("Pew!");
        if(isWithinDirectDamage(z.getCol()))
        {
            System.out.println("direct");
            peas.add(new Projectile(getRow(), getCol(), currentTime, getDirectDamage(), projectileSpeed));
        }
        else
        {
            System.out.println("ranged");
            peas.add(new Projectile(getRow(), getCol(), currentTime, getDamage(), projectileSpeed));
        }
        
    }

    /**
     * 
     * This method searches through the list of zombie 
     * objects to find the nearest one positioned in the 
     * front of peashooter object. If successfull it will 
     * return the nearest zombie object, otherwise, it 
     * will return null
     * 
     * @param enemies list of zombie objects 
     * @return nearest zombie object if it is positioned in front of
     * peashooter, otherwise, null. 
     */
    public Zombie findFront(ArrayList<Zombie> enemies)
    {
    
        int i, finalCol = -1; 
        float smallestDistance = 888f, distance;
        
        for(i = 0; i < enemies.size(); i++)
        {
            //if same row
            if(enemies.get(i).getRow() == getRow())
            {
                //distance between plant and zombie
                distance = enemies.get(i).getCol() - getCol();

                if(distance >= 0 && distance < smallestDistance)
                {
                    smallestDistance = distance;
                    finalCol = i;
                }
            }
        }

        //if nearest zombie object is found
        if(finalCol != -1)
        {
            return enemies.get(finalCol);
        }

        return null;

    }

    /**
     * This method sets the projectile speed of 
     * a peashooter object. 
     * 
     * @param pSpeed projectile speed or how fast a 
     * projectile moves after it is released. 
     */
    public void setProjectileSpeed(float pSpeed)
    {
        projectileSpeed = pSpeed;
    }

    /** This method returns the arraylist of peas
     *  created by this object
     *
     * @return the arraylist of peas created
     */
    public ArrayList<Projectile> getPeas() {
        return peas;
    }


    private ArrayList<Projectile> peas;
    private float projectileSpeed;
}

class PeashooterDriver {
    public static void main(String[] args) {
        Peashooter p = new Peashooter(1,1,0);
        Zombie z = new Zombie(1,0,0);

        System.out.println("1");
        z.setCol(9);
        p.shoot(z, 1);
        p.setInternal_time(0);
        p.getPeas().clear();

        System.out.println("2");
        z.setCol(2);
        p.shoot(z, 1);
        p.setInternal_time(0);
        p.getPeas().clear();
    }
}