
public class Plant {

    private static int count = 0;

    /**This constructor initializes the  */
    public Plant(String typeOfPlant, int r, int c)
    {
        stats = new PlantStats(typeOfPlant);
        row = r;
        col = c;
        health = 100;
        count++;
    }

    public Plant(int r, int c)
    {
        this("sunflower", r, c);
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    public void takeDamage(int dmg)
    {
        if(isAlive())
        {
            health = health - dmg;
        }
        
    }

    @Override 
    public String toString()
    {
        return "Type of plant: " + stats.getName() + "\nCost: " + stats.getCost()
        + "\nSpeed: " + stats.getSpeed() + "\nAtack Range: " + stats.getAttackRange()
        + "\nAttack Damage: " + stats.getAttackDamage() + "\nDirect Damage: " + 
        stats.getDirectDamage() + "\n";
    }

    public void displayCurrentStatus()
    {
        System.out.println("Health: " + getHealth());
        System.out.println(stats.getName() + " at row " + getRow() + " and column " + getCol());
        System.out.println();
    }

    public int getHealth()
    {
        return health;
    }

    public static int getCount()
    {
        return count;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }


    private PlantStats stats;
    private int health;
    private int row;
    private int col;

}

