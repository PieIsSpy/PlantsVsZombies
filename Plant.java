/** This class
 *
 */
public class Plant extends Entity {

    Plant(String name, int cost, int cooldown, int health, int range,
          int attackDamage, int directDamage, float speed) //attack plants (e.g., peashooter)
    {
        this.name = name;
        this.cost = cost;
        this.cooldown = cooldown;
        this.health = health;
        this.range = range;
        this.attackDamage = attackDamage;
        this.directDamage = directDamage;
        this.speed = speed;

    }

    Plant(String name, int cost, int cooldown, int health) //utility plants (e.g., sunflower, wallnut)
    {
        this(name, cost, cooldown, health, 0, 0, 0, 0);
    }

    public void setPosition(int r, int c)
    {
        row = r;
        col = c;
    }


    public boolean isAlive()
    {
        return health > 0;
    }

    public void takeDamage(int dmg)
    {
        if(health > 0)
        {
            health -= dmg;
        }
        else
        {
            health = 0;
        }
    }


    //other plant subclasses will override
    public void plantAction()
    {
        
    }


    @Override
    public String toString()
    {
        return "Type of plant: " + getName() + "\nCost: " + getCost()
        + "\nSpeed: " + getSpeed() + "\nAtack Range: " + getRange()
        + "\nAttack Damage: " + getAttackDamage() + "\nDirect Damage: " + 
        getDirectDamage() + "\n";
    }

    public String getName()
    {
        return name;
    }

    public int getCost()
    {
        return cost;
    }

    public int getCooldown()
    {
        return cooldown;
    }

    public int getRange()
    {
        return range;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public int getDirectDamage()
    {
        return directDamage;
    }


    private String name;
    private int cost;
    private int cooldown;
    private int range;
    private int attackDamage;
    private int directDamage;
}


