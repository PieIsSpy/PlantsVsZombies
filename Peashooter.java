
import java.util.ArrayList;

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
        setDamage(15);
        setHealth(30);
        setDirectDamage(25);
        setDirectDamageRange(2);
        projectileSpeed = 1.5f;

        peas = new ArrayList<>();

    }

    /**
     * This method is used to define the peashooter's 
     * behavior in response to zombie objects.
     * 
     * 
     * @param enemies list of zombie objects
     */
    public void plantBehavior(ArrayList<Zombie> enemies)
    {
        Zombie z = findNearestEnemy(enemies);

        int i;
        if(z != null && isWithinRange(z.getCol()))
        {
            shoot(z); 
            System.out.println("Peashooter firing...");

            i = 0; 

            while(i < peas.size())
            {
                //
                //distance between pea and zombie is less than 0.5
                if(z.isAlive() && (z.getCol() - peas.get(i).getColumn()) < 0.5)
                {
                    peas.get(i).hitEnemy(z);
                }
                else
                {
                    peas.get(i).moveProjectile();
                }
                i++;
            }

            //removes the peas that have already hit the zombie
            removeInactiveProjectiles();
        }

        //projectileLogic(enemies)?? 
    }

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
     * 
     * for(i = 0; i < ROW; i++)
     * {
     *      for(x = 0; x < COL; x++)
     *      {
     *          tiles[i][x].plantBehavior
     *      }
     *  
     * }
     * 
     * attack range of projectile = <0.5
     * 
     *
     */

    
   
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
        //plant range = 7
        //plantcol index = 2
        //zombie col index = 5
        //checks if 
        //(5 - 2) <= 7 : checks if it is within the range of peashooter
        // && 5 >= 2 : ensures that it is in front of peashooter
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
     * This method
     * 
     * 
     * @param z
     */
    public void shoot(Zombie z)
    {
        //so it deals more damage when it is closer
        if(isWithinDirectDamage(z.getCol()))
        {
            peas.add(new Projectile(getCol(), getDirectDamage(), projectileSpeed));
        }
        else
        {
            peas.add(new Projectile(getCol(), getDamage(), projectileSpeed));
        }
    
        
    }

    

    public Zombie findNearestEnemy(ArrayList<Zombie> enemies)
    {
        //loop through all the zombies in the list
        //find the one with equal row and nearest col

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

        if(finalCol != -1)
        {
            return enemies.get(finalCol);
        }

        return null;

    }

    /* 
    public static void main(String[] args) {
        
        int i, x;
        Peashooter p1 = new Peashooter(2, 1);
        boolean isAlive = true;
        

        
        Zombie z = new Zombie(2, 4); 
        ArrayList<Zombie> zombies = new ArrayList<>();
        zombies.add(z);

        
        for (i = 0; i < 10 && isAlive; i++) {
            System.out.println("Time: " + i);

            p1.plantBehavior(zombies); 

            
           for (x = 0; x < p1.peas.size(); x++) 
           {
                Projectile p = p1.peas.get(x);
                System.out.printf("projectile at col %.2f\n", p.getColumn());
            }

            System.out.printf("zombie at col %.2f\n health: %d \n alive?: %b\n", z.getCol(), z.getHealth(), z.isAlive());

            if (!z.isAlive()) 
            {
                System.out.println("zmbie defeated!");
                isAlive = false;
            }

            System.out.println();
        }
    }

    */


    private ArrayList<Projectile> peas;
    private float projectileSpeed;
   
    
}


