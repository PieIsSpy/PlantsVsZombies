/** This class represents an Entity of a game.
 *  This can be used by either plants or zombie
 *  to inherit the attributes of health, speed, damage,
 *  and row and column coordinates.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
abstract class Entity {
    /** This constructor initializes the starting health,
     *  damage output, and row and col coordinates
     *  of an entity. 
     *
     *  @param h the health point of an entity
     *  @param s the speed of an entity
     *  @param d the damage output of an entity
     *  @param r the row coordinate of an entity
     *  @param c the col coordinate of an entity
     * @param t the time of creation
     */
    public Entity (int h, int s, int d, float r, float c, int t) {
        health = h;
        speed = s;
        damage = d;
        row = r;
        col = c;
        internal_time = t;
        gameImage = null;
    }

    /**
     * This constructor initializes the starting health, 
     * speed, and damage output of an entity. 
     * @param h the health point of an entity
     * @param s the speed of an entity
     * @param d the damage output of an entity
     * @param t the time of creation
     */
    public Entity(int h, int s, int d, int t)
    {
        health = h;
        speed = s;
        damage = d;
        internal_time = t;
    }

    /** This method subtracts the health of an entity
     *  by d amount of damage. If the current health
     *  of an entity underflow below 0, it automatically
     *  sets to 0.
     *
     *  @param d the damage input to an entity
     */
    public void takeDamage(int d) {
        int cur = getHealth();
        cur -= d;

        if (cur < 0)
            setHealth(0);
        else
            setHealth(cur);
    }

    /** This method checks if the entity is still alive.
     *
     *  @return true if the health is above 0, else false
     */
    public boolean isAlive() {
        return getHealth() > 0;
    }

    /** This method updates the health point of an entity.
     *
     *  @param h the current health point of an entity
     */
    public void setHealth(int h) {
        health = h;
    }

    /** This method updates the speed attribute of an entity.
     *
     * @param s the speed of the entity
     */
    public void setSpeed(int s) {
        speed = s;
    }

    /** This method updates the damage output of an entity
     *
     *  @param d the current damage output of an entity
     */
    public void setDamage(int d) {
        damage = d;
    }

    /** This method updates the row coordinate of an entity
     *
     *  @param r the row coordinate of an entity
     */
    public void setRow(float r) {
        row = r;
    }

    /** This method updates the col coordinate of an entity
     *
     *  @param c the col coordinate of an entity
     */
    public void setCol(float c) {
        col = c;
    }

    /** This method updates the internal time of an entity
     *
     * @param t the current time of an entity
     */
    public void setInternal_time(int t) {
        internal_time = t;
    }

    /** This method returns the current health of an
     *  Entity.
     *
     *  @return the current health of an entity
     */
    public int getHealth() {
        return health;
    }

    /** This method returns the speed per grid
     *  of an entity.
     *
     *  @return the seconds per grid of an entity
     */
    public int getSpeed() {
        return speed;
    }

    /** This method returns the damage output of
     *  an Entity.
     *
     *  @return the damage output of an entity
     */
    public int getDamage() {
        return damage;
    }

    /** This method returns the row coordinate of
     *  an entity.
     *
     *  @return the row coordinate of an entity
     */
    public float getRow() {
        return row;
    }

    /** This method returns the column coordinate of
     *  an entity.
     *
     *  @return the column coordinate of an entity
     */
    public float getCol() {
        return col;
    }

    /** This method returns the current internal time
     *  of the entity
     *
     * @return the current internal time of the entity
     */
    public int getInternal_time() {
        return internal_time;
    }

    public GameImage getGameImage()
    {
        return gameImage;
    }

    public void setGameImage(GameImage g)
    {
        gameImage = g;
    }


    /** How much damage it can sustain */
    private int health;
    /** How fast an entity attacks or walks */
    private int speed;
    /** How much damage it deals to the entity being attacked */
    private int damage;
    /** The row position of the entity */
    private float row;
    /** The column positon of the zombie */
    private float col;
    /** The internal time of the enemy*/
    private int internal_time;
    
    private GameImage gameImage;
}
