
public class Plant {

    Plant(String name, int cost, int cooldown, int health, int range, 
    int attackDamage, int directDamage, int speed) //attack plants (e.g., peashooter)
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
        if(isValidPosition(r, c))
        {
            row = r;
            col = c;
        }
    }

    public boolean isValidPosition(int r, int c)
    {
        return r >= 0 && c >= 0; //will update in future
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
    public void plantBehavior()
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

    public int getHealth()
    {
        return health;
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

    private int getSpeed()
    {
        return speed;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return col;
    }

    private String name;
    private int cost;
    private int cooldown;
    private int health;
    private int range;
    private int attackDamage;
    private int directDamage;
    private int speed;
    private int row;
    private int col;
}


