/** The class Zombie represents a basic zombie object
 *  that can be used by other child classes that represent
 *  variant zombies.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Zombie {
    /** This constructor initializes the default values
     *  of a basic zombie. This also increments the static variable
     *  "count" by 1.
     *
     *  @param x_pos the row grid position of the Zombie
     */
    public Zombie(int x_pos) {
        speed = 4;
        damage = 10;
        health = 70;

        position = new float[2];
        position[0] = x_pos;
        position[1] = 9;

        count++;
    }

    /** This method checks if the Zombie is still alive.
     *
     *  @return true if the health is above 0, else false
     */
    public boolean isAlive() {
        return health > 0;
    }

    /** This method checks if the Zombie has already
     *  reached the player's house.
     *
     *  @return true if position[1] is less than or
     *  equal to 0, false otherwise
     */
    public boolean isAtHouse() {
        return position[1] <= 0;
    }

    /** This method moves the Zombie to the left of the
     *  lawn at speed variable seconds per grid.
     */
    public void walk() {
        position[1] -= (float)(1.0 / speed);
    }

    public void eat(Plant p) {

    }

    /** This method subtracts the health of the Zombie
     *  by d amount of damage. If the current health
     *  of a zombie underflows below 0, it automatically
     *  sets to 0.
     *
     *  @param d the damage input to a Zombie
     */
    public void takeDamage(int d) {
        health -= d;

        if (health < 0)
            health = 0;
    }

    /** This method returns the speed per grid
     *  of a Zombie.
     *
     *  @return the seconds per grid of a zombie
     */
    public int getSpeed() {
        return speed;
    }

    /** This method returns the current health of a
     *  Zombie.
     * 
     *  @return the current health of a zombie
     */
    public int getHealth() {
        return health;
    }

    /** This method returns the damage output of
     *  a Zombie.
     *
     *  @return the damage output of a Zombie
     */
    public int getDamage() {
        return damage;
    }

    /** This method returns the coordinates of
     *  a Zombie. position[0] represents the
     *  row coordinate while position[1] represents
     *  the col coordinate.
     *
     *  @return the current coordinates of a zombie
     */
    public float[] getPosition() {
        return position;
    }

    /** This method returns the current Zombie counts.
     *
     *  @return the number of Zombie objects created
     */
    public static int getCount() {
        return count;
    }

    /** This method decrements the count of Zombie objects by one.
     */
    public static void die() {
        count--;
    }

    /** How fast the zombie moves */
    private int speed;
    /** How much damage it deals to the plant it is attacking */
    private int damage;
    /** How much damage it can sustain */
    private int health;
    /** Where is the position of the zombie */
    private float[] position;
    /** How many zombies are created */
    private static int count = 0;

    /*
    reserved, may be used later
    private final int DEFAULT_SPEED = 4;
    private final int DEFAULT_DAMAGE = 10;
    private final int DEFAULT_HEALTH = 70;
    private final float DEFAULT_COL = 9;
     */
}

class driverZombie {
    public static void main(String[] args) {
        Zombie[] spawns = new Zombie[5];
        int i;
        float[] coordinates;

        for (i = 0; i < 5; i++)
            spawns[i] = new Zombie((int)Math.floor(Math.random() * 9) + 1);

        System.out.printf("There are %d Zombies spawned in your lawn!\n", Zombie.getCount());

        System.out.println();

        System.out.println("Zombie random spawns");
        for (i = 0; i < 5; i++) {
            coordinates = spawns[i].getPosition();
            System.out.printf("Zombie %d spawn: (row %d, col %d)\n", i + 1, (int)coordinates[0], (int)coordinates[1]);
        }

        System.out.println();

        System.out.println("Zombie stats");
        for (i = 0; i < 5; i++) {
            System.out.printf("Zombie %d:\n", i+1);
            System.out.printf("Speed: %d\n", spawns[i].getHealth());
            System.out.printf("Damage: %d\n", spawns[i].getDamage());
            System.out.printf("Health: %d\n", spawns[i].getHealth());
            System.out.println();
        }

        System.out.println();
        System.out.println();

        System.out.println("Kill one zombie");
        while (spawns[0].isAlive()) {
            System.out.printf("Zombie 1 health: %d/70\n", spawns[0].getHealth());
            spawns[0].takeDamage(15);
        }

        if (!spawns[0].isAlive())
        {
            System.out.println("Zombie 1 died!");
            spawns[0] = null;
            Zombie.die();
        }

        System.out.println();
        System.out.println("Zombie going to your house");
        i = 0;
        while (!spawns[1].isAtHouse())
        {
            spawns[1].walk();
            coordinates = spawns[1].getPosition();
            System.out.printf("seconds: %d\n", i);
            System.out.printf("Zombie %d pos: (row %d, col %d)\n", 2, (int)coordinates[0], (int)coordinates[1]);
            i++;
        }

        if (spawns[1].isAtHouse())
            System.out.println("THE ZOMBIES ATE YOUR BRAIN!");
    }
}