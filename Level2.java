/** This class represents the second level of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Level2 extends Level{
    /** This constructor initializes the basic attributes of its
     *  super class such as the level number, the max timer, and
     *  the number of rows and col of the lawn. It also initializes
     *  the available plants of the level.
     *
     * @param currentTime the time of creation
     */
    public Level2(int currentTime) {
        super(2,180,5,9,currentTime);

        Plant[] p = new Plant[] {
                new Sunflower(-1,-1,0),
                new Peashooter(-1,-1,0),
                new CherryBomb(-1,-1,0),
                new Wallnut(-1,-1,0)
        };

        initializePlants(p);
    }

    /**
     * This method spawns a new zombie at a random row
     * and in the rightmost column. It is then added
     * to the enemies array list.
     *
     * @param currentTime the current time frame of the level
     */
    @Override
    public void spawnZombies(int currentTime) {
        float x = (float)Math.random();

        /*
            Time: Below 45% of Time limit
            Spawn rates:
                Normal Zombie: 80%
                Conehead Zombie: 20%
         */
        if (currentTime < (int)Math.floor(getTIME_LENGTH() * 0.45)) {
            if (x > .2f)
                getEnemies().add(new Zombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
            else
                getEnemies().add(new ConeheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
        }
        /*
            Time: 45% of Time limit and above
            Spawn rates:
                Normal Zombie: 60%
                Conehead Zombie: 40%
         */
        else {
            if (x > .4f)
                getEnemies().add(new Zombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
            else
                getEnemies().add(new ConeheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
        }
    }
}