/** This class represents the second level of the game.
 *
 */
public class Level2 extends Level{
    /** This constructor initializes the basic attributes of its
     *  super class such as the level number, the max timer, and
     *  the number of rows and col of the lawn. It also initializes
     *  the available plants of the level.
     */
    public Level2() {
        super(2,180,5,9);

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
     */
    @Override
    public void spawnZombies() {
        float x = (float)Math.random();
        System.out.println("Spawned zombie");
        /*
            Time: Below 45% of Time limit
            Spawn rates:
                Normal Zombie: 80%
                Conehead Zombie: 20%
         */
        if (getInternal_time() < (int)Math.floor(getTIME_LENGTH() * 0.45)) {
            if (x > .2f)
                getEnemies().add(new Zombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1));
            else
                getEnemies().add(new ConeheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1));
        }
        /*
            Time: 45% of Time limit and above
            Spawn rates:
                Normal Zombie: 60%
                Conehead Zombie: 40%
         */
        else {
            if (x > .4f)
                getEnemies().add(new Zombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1));
            else
                getEnemies().add(new ConeheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1));
        }
    }
}