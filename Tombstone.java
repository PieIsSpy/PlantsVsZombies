public class Tombstone extends Entity {
    public Tombstone (int r, int c) {
        super(270, 0, 0, r, c, 0);
    }

    public void spawn(Level level, int currentTime) {
        float x = (float)Math.random();

        if (x > .5f)
            level.getEnemies().add(new Zombie((int)getRow(), (int)getCol(), currentTime));
        else if (x >= .3f)
            level.getEnemies().add(new ConeheadZombie((int)getRow(), (int)getCol(), currentTime));
        else if (x >= .15f)
            level.getEnemies().add(new BucketheadZombie((int)getRow(), (int)getCol(), currentTime));
        else
            level.getEnemies().add(new PolevaulterZombie((int)getRow(), (int)getCol(), currentTime));
    }
}