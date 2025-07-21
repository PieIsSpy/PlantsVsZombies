/** This class represents the Player, the controller
 *  of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Player {

    /** This constructor initializes the initial amount of
     *  suns the player have.
     *
     * @param s the initial amount of suns
     */
    public Player (int s) {
        sun = s;
    }

    /** This method returns the player's current amount of suns
     *
     * @return the player's current amount of suns
     */
    public int getSun() {
        return sun;
    }

    /** This method instantiates a plant to a Level class
     *  in a given row and column, provided that the given
     *  name of the plant is valid.
     *
     * @param l the level to instantiate the plant
     * @param r the row of the plant to be instantiated
     * @param c the col of the plant to be instantiated
     * @param n the name of the plant to be instantiated
     * @param t the time of instantiation
     */
    public void placePlant(Level l, int r, int c, String n, int t) {
        if (n.equalsIgnoreCase("sunflower"))
            l.getTiles()[r][c] = new Sunflower(r,c,t);
        else if (n.equalsIgnoreCase("peashooter"))
            l.getTiles()[r][c] = new Peashooter(r,c, t);
        else if(n.equalsIgnoreCase("wallnut"))
            l.getTiles()[r][c] = new Wallnut(r, c, t);
        else if (n.equalsIgnoreCase("potato mine"))
            l.getTiles()[r][c] = new PotatoMine(r,c,t);
        else if (n.equalsIgnoreCase("cherry bomb"))
            l.getTiles()[r][c] = new CherryBomb(r,c,t);
        else if (n.equalsIgnoreCase("snow pea"))
            l.getTiles()[r][c] = new SnowPea(r,c,t);
    }

    /** This method removes a plant in a given row
     *  and col of a Level class.
     *
     * @param l the level to be edited
     * @param r the row to be shoveled
     * @param c the col to be shoveled
     */
    public void useShovel(Level l, int r, int c) {
        l.getTiles()[r][c] = null;
    }

    /** This method collects the sun present in a level
     *  and adds its value to the player's accumulated
     *  amount of suns.
     *
     * @param s the amount of suns to be added
     */
    public void collectSun(int s) {
        sun += s;
    }

    /** This method subtracts the player's
     *  accumulated amount of suns.
     *
     * @param s the amount of suns to be subtracted
     */
    public void subtractSun(int s) {
        sun -= s;
    }

    /**the accumulated amount of suns*/
    private int sun;
}