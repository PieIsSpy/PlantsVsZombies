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
     * position. 
     * 
     * @param r row index of object
     * @param c column index of object
     */
    public Peashooter(float r, float c)
    {
        super(r, c);
        setName("Peashooter");
        setCost(100);
        setCooldown(7);
        setRange(7);
        setDamage(5);
        setHealth(30);
        setDirectDamage(10);
        setDirectDamageRange(2);
        setSpeed(2);
        setProjectileSpeed(1.5f);
        shootCooldown = 0;
        peas = new ArrayList<>();

    }

    /**
     * This method is used to define the peashooter's 
     * behavior in response to zombie objects.
     * 
     * @param enemies list of zombie objects
     * 
     */
    @Override
    public void plantBehavior(ArrayList<Zombie> enemies)
    {
        Zombie z = findNearestEnemy(enemies);

        if(z != null && isWithinRange(z.getCol()))
        {

            if(shootCooldown > 0)
            {
                shootCooldown--;
            }

            if(shootCooldown == 0)
            {
                shoot(z); 
                System.out.println("Peashooter firing...");
                shootCooldown = getSpeed();
            }
            
        }

         projectileLogic(enemies);

    }

    /**
     * This method handles the logic of the projectile
     * after a peashooter releases/shoots it. If it hits
     * the zombie object, it will be removed from the 
     * list of projectiles. Otherwise, it will continue 
     * moving. 
     * 
     * @param enemies list of zombie objects 
     */
    public void projectileLogic(ArrayList<Zombie> enemies)
    {
        int i = 0, x;
        boolean hasHit = false;

        //loops through the list of projectiles
        while(i < peas.size())
        {
            //loops through the list of enemies within the same row
            for(x = 0; x < enemies.size() && !hasHit; x++)
            {
                //if zombie is alive and projectile is within range of attack 
                if(enemies.get(x).isAlive() && (enemies.get(x).getCol() - peas.get(i).getColumn()) < 0.5)
                {
                    peas.get(i).hitEnemy(enemies.get(x));
                    hasHit = true;
                }
            
            }

            //if it has not hit any zombie yet, it will continue moving
            if(!hasHit)
            {
                peas.get(i).moveProjectile();
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
        return target <= getCol() + getDirectDamageRange();
    }

    /**
     * 
     * This method represents the shooting of a projectile
     * at its targeted zombie object. It adds it to the 
     * peashooter's list of projectiles. If the target is within
     * a direct damage range, the projectile will deal an increased
     * amount of damage. 
     * 
     * 
     * @param z zombie object targeted by projectile 
     */
    public void shoot(Zombie z)
    {
        if(isWithinDirectDamage(z.getCol()))
        {
            peas.add(new Projectile(getRow(), getCol(), getDirectDamage(), projectileSpeed));
        }
        else
        {
            peas.add(new Projectile(getRow(), getCol(), getDamage(), projectileSpeed));
        }
        
    }

    /**
     * 
     * This method searches through the list of zombie 
     * objects to find the nearest one positioned in the 
     * front of peashooter object. If successful, it will
     * return the nearest zombie object, otherwise, it 
     * will return null
     * 
     * @param enemies list of zombie objects 
     * @return nearest zombie object if it is positioned in front of
     * peashooter, otherwise, null. 
     */
    public Zombie findNearestEnemy(ArrayList<Zombie> enemies)
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
                    smallestDistance = enemies.get(i).getCol();
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

     /* 
    public static void main(String[] args) {
        
        int i, x;
        Peashooter p1 = new Peashooter(2, 1);

        ArrayList<Zombie> zombies = new ArrayList<>();

        zombies.add(new Zombie(2, 4));
        zombies.add(new Zombie(2, 3));

        
        for (i = 0; i < 30; i++) {
            System.out.println("Time: " + i);

            p1.plantBehavior(zombies); 

            
           for (x = 0; x < p1.peas.size(); x++) 
           {
                Projectile p = p1.peas.get(x);
                System.out.printf("projectile %d at col %.2f\n", x, p.getColumn());
            }

            for(x = 0; x < zombies.size(); x++)
            {
                System.out.printf("zombie at col %.2f\n health: %d \n alive?: %b\n", zombies.get(x).getCol(), zombies.get(x).getHealth(), zombies.get(x).isAlive());
                if(!zombies.get(x).isAlive())
                {
                    System.out.println("Zombie " + x + " is defeated!");
                }
            }
            

            System.out.println();
        }
    }

    */

    private ArrayList<Projectile> peas;
    private float projectileSpeed;
    private int shootCooldown;
   
    
}


