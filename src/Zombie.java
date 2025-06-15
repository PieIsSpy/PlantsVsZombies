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
     *  of a basic zombie and places it to a given row.
     *  This also increments the static variable
     *  "count" by 1.
     *
     *  @param r the row grid position of the Zombie
     */
    public Zombie(int r) {
        speed = 4;
        damage = 10;
        health = 70;

        row_position = r;
        col_position = 9;

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
        return col_position <= 0;
    }

    /** This method moves the Zombie to the left of the
     *  lawn at speed variable seconds per grid.
     */
    public void walk() {
        col_position -= (float) (1.0 / speed);
    }

    /** This method makes the zombie eat the plant that is
     *  in front of it, dealing damage.
     *
     *  @param p the target plant object to be damaged
     */
    public void eat(Plant p) {
        p.takeDamage(damage);

        // temporary until Lawn.java have death mechanics already
        if (!p.isAlive())
            p = null;
    }

    /** This method compiles basic action methods
     *  of a zombie and turns it into a behaviour.
     *
     *  @param p the plant to be checked
     */
    public void behaviour(Plant p) {
        // while zombie isn't in the house and still alive (this will be called repeatedly by Lawn.java
        if (!this.isAtHouse() && this.isAlive()) {
            // if zombie is still not within attack range or there isn't any plants in front
            if (col_position - p.getColumn() > 0.5 || !p.isAlive()) {
                System.out.printf("pos: row %d col %d\n", (int)row_position, (int)col_position);
                walk();
            }
            // else if a plant is in front of zombie
            else if (p.isAlive()){
                System.out.printf("plant hp: %d\n", p.getHealth());
                eat(p);
            }
        }
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

    /** This method returns the row coordinate of
     *  a Zombie. 
     *
     *  @return the row coordinate of a zombie
     */
    public float getRowCoord() {
        return row_position;
    }

    /** This method returns the column coordinate of
     *  a Zombie.
     *
     *  @return the column coordinate of a zombie
     */
    public float getColCoord() {
        return col_position;
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
    /** The row position of the zombie */
    private float row_position;
    /** The column positon of the zombie */
    private float col_position;
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

/*
class zombieDriver {
    public static void main(String[] arg) {
        Zombie z = new Zombie(1);
        Plant[] p = new Plant[2];
        p[0] = new Plant(1,1);
        p[1] = new Plant(1, 2);
        Plant front = p[1];

        z.behaviour(front);
        front = p[0];
        z.behaviour(front);
    }
}

 */

/*
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
 */