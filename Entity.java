/** This class represents an Entity of a game.
 *  This can be used by either plants or zombie
 *  to inherit the attributes of health, speed, damage,
 *  and row and column coordinates.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Entity {
    /** This constructor initializes the starting health,
     *  damage output, and row and col coordinates
     *  of an entity
     *
     *  @param h the health point of an entity
     *  @param s the speed of an entity
     *  @param d the damage output of an entity
     *  @param r the row coordinate of an entity
     *  @param c the col coordinate of an entity
     */
    public Entity (int h, int s, int d, float r, float c) {
        health = h;
        speed = s;
        damage = d;
        row = r;
        col = c;
    }

    /** This method updates the health point of an entity.
     *
     *  @param h the current health point of an entity
     */
    public void setHealth(int h) {
        health = h;
    }

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

    /** how much damage it can sustain */
    private int health;
    /** How fast an entity attacks or walks */
    private int speed;
    /** How much damage it deals to the entity being attacked */
    private int damage;
    /** The row position of the entity */
    private float row;
    /** The column positon of the zombie */
    private float col;
}
