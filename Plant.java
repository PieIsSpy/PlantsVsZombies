/** The class Plant represents a plant entity that can be placed by a player.
 *  This class also serves as the parent class for different types of plants.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Plant extends Entity {
    /** This constructor initializes the attributes of a plant
     *  with default values. It also initializes the row and column
     *  position of a plant given by the parameter.
     *
     *  @param r the row coordinate of a plant
     *  @param c the column coordinate of a plant
     */
    Plant(float r, float c) //attack plants (e.g., peashooter)
    {
        super(0, 0, 0, r, c);
        name = "Default";
        cost = 0;
        cooldown = 0;
        range = 0;
        directDamage = 0;

    }

    /** This method does nothing by default. Due to how different
     *  plants have different behaviours, this method have to be
     *  overridden for it to be used.
     *
     */
    public void plantAction()
    {
        
    }

    /** This method sets the name of the plant.
     *
     *  @param n the name of the plant
     */
    public void setName(String n)
    {
        name = n;
    }

    /** This method sets the sun cost of the plant.
     *
     *  @param c the sun cost of the plant
     */
    public void setCost(int c)
    {
        cost = c;
    }

    /** This method sets the cooldown of the plant.
     *
     *  @param cd the cooldown of the plant
     */
    public void setCooldown(int cd)
    {
        cooldown = cd;
    }

    /** This method sets the attack range of the plant
     *
     *  @param r the attack range of the plant
     */
    public void setRange(int r)
    {
        range = r;
    }

    /**
     * This method sets the range of distance
     * between plant and enemy object that will 
     * allow the plant deal increased damage. 
     * 
     * @param dr direct damage range between 
     * plant and zombie object 
     * 
     */
    public void setDirectDamageRange(int dr)
    {
        directDamageRange = dr;
    }

    /** This method sets the damage output of the plant when an enemy
     *  is at its nearest position.
     *
     *  @param d the damage output of the plant to an entity at its nearest position
     */
    public void setDirectDamage(int d)
    {
        directDamage = d;
    }

    /** This method returns the name of the plant.
     *
     *  @return the name of the plant
     */
    public String getName()
    {
        return name;
    }

    /** This method returns the sun cost of the plant.
     *
     *  @return the sun cost of the plant
     */
    public int getCost()
    {
        return cost;
    }

    /** This method returns the cooldown time of the plant.
     *
     *  @return the cooldown of the plant
     */
    public int getCooldown()
    {
        return cooldown;
    }

    /** This method returns the attack range of the plant.
     *
     *  @return the attack range of the plant
     */
    public int getRange()
    {
        return range;
    }

    /** This method returns the damage output of the plant
     *  at its nearest distance.
     *
     *  @return the damage output of the plant at its nearest distance
     */
    public int getDirectDamage()
    {
        return directDamage;
    }

    /** the name of the plant */
    private String name;
    /** the sun cost of the plant */
    private int cost;
    /** the cooldown timer of the plant */
    private int cooldown;
    /** the attack range of the plant */
    private int range;
    /** the damage output of the plant at its nearest distance */
    private int directDamage;
}
