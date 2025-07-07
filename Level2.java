public class Level2 extends Level{
    public Level2(int r, int c, int currentTime) {
        super(2,180,r,c,currentTime);

        Plant[] p = new Plant[] {
                new Sunflower(-1,-1,0),
                new Peashooter(-1,-1,0),
                new CherryBomb(-1,-1,0),
                new Wallnut(-1,-1,0)
        };

        initializePlants(p);
    }

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