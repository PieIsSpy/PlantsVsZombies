package MCO1;

public class PlantStats {

    public PlantStats(String n)
    {
        if(n.equalsIgnoreCase("sunflower"))
        {
            name = n;
            cost = 50;
            speed = 10; //how fast it produces sun
            attackRange = 0;
            attackDamage = 0;
            directDamage = 0;

        }
    }

    public String getName()
    {
        return name;
    }

    public int getCost()
    {
        return cost;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getDirectDamage()
    {
        return directDamage;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public int getAttackRange()
    {
        return attackRange;
    }


    private String name;
    private int speed;
    private int attackRange;
    private int attackDamage;
    private int directDamage;
    private int cost;
}
