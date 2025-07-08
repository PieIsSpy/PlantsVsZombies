public class Level3 extends Level{
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

    public void spawnGraves() {
        int nRow, nCol;
        int i;

        for (i = 0; i < 5; i++) {
            do {
                nRow = (int)(Math.floor(Math.random() * getROWS()));
                nCol = 4 + (int)(Math.floor(Math.random() * (getCOLUMNS() - 4)));
            } while (!canBePlaced(nRow, nCol));

            getTiles()[nRow][nCol] = new Tombstone(nRow, nCol);
            //System.out.println("grave at row " + (nRow+1) + " col " + (nCol+1));
        }
    }

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