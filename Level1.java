/** This class represents the first level of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.1
 */
public class Level1 extends Level{
    /** This constructor initializes the basic attributes of its
     *  super class such as the level number, the max timer, and
     *  the number of rows and col of the lawn. It also initializes
     *  the available plants of the level.
     *
     * @param currentTime the starting time frame of the level
     */
    public Level1(int currentTime) {
        super(1,180,5,9,currentTime);

        Plant[] p = new Plant[]{
                new Sunflower(-1, -1, 0),
                new Peashooter(-1, -1, 0)
        };

        initializePlants(p);
    }
}
