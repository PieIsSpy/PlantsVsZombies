/** This class represents the third level of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class Level3 extends Level{
    /** This constructor initializes the basic attributes of its
     *  super class such as the level number, the max timer, and
     *  the number of rows and col of the lawn. It also initializes
     *  the available plants of the level.
     *
     * @param currentTime the starting time frame of the level
     */
    public Level3(int currentTime) {
        super(3,180,5,9,currentTime);

        Plant[] p = new Plant[] {
                new Sunflower(-1,-1,0),
                new Peashooter(-1,-1,0),
                new CherryBomb(-1,-1,0),
                new Wallnut(-1,-1,0),
                new PotatoMine(-1,-1,0),
                new SnowPea(-1,-1,0)
        };

        initializePlants(p);
        spawnGraves();
    }

    /** This method spawns 5 graves at the start of the game.
     *
     */
    public void spawnGraves() {
        int nRow, nCol;
        int i;

        for (i = 0; i < 5; i++) {
            do {
                nRow = (int)(Math.floor(Math.random() * getROWS()));
                nCol = 5 + (int)(Math.floor(Math.random() * (getCOLUMNS() - 5)));
            } while (!canBePlaced(nRow, nCol));

            getTiles()[nRow][nCol] = new Tombstone(nRow, nCol);
            //System.out.println("grave at row " + (nRow+1) + " col " + (nCol+1));
        }
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
                Normal Zombie: 70%
                Conehead Zombie: 20%
                Buckethead Zombie: 10%
         */
        if (currentTime < (int)Math.floor(getTIME_LENGTH() * 0.45)) {
            if (x > .3f)
                getEnemies().add(new Zombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
            else if (x > .1f)
                getEnemies().add(new ConeheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
            else
                getEnemies().add(new BucketheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
        }
        /*
            Time: 45% of Time limit and above
            Spawn rates:
                Normal Zombie: 60%
                Conehead Zombie: 40%
                Buckethead Zombie: 10%
                Pole Vaulter Zombie: 5%
         */
        else {
            if (x > .5f)
                getEnemies().add(new Zombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
            else if (x >= .3f)
                getEnemies().add(new ConeheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
            else if (x >= .15f)
                getEnemies().add(new BucketheadZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
            else
                getEnemies().add(new PolevaulterZombie((int)(Math.floor(Math.random() * getROWS())), getCOLUMNS() + 1, currentTime));
        }
    }
}