/** This class
 *
 */
public class Plant extends Entity {

    Plant(float r, float c) //attack plants (e.g., peashooter)
    {
        super(0, 0, 0, r, c);
        name = "Default";
        cost = 0;
        cooldown = 0;
        range = 0;
        directDamage = 0;

    }

    public void plantAction()
    {
        
    }

    public void setName(String n)
    {
        name = n;
    }

    public void setCost(int c)
    {
        cost = c;
    }

    public void setCooldown(int cd)
    {
        cooldown = cd;
    }

    public void setRange(int r)
    {
        range = r;
    }

    public void setDirectDamage(int d)
    {
        directDamage = d;
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

    public int getDirectDamage()
    {
        return directDamage;
    }


    private String name;
    private int cost;
    private int cooldown;
    private int range;
    private int directDamage;
}
