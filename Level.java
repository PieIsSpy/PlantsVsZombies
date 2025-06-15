import java.util.ArrayList;

public class Level {
    public Level (int l, int r, int c, int t) {
        LEVEL_NUM = l;
        rows = r;
        columns = c;
        time_length = t;
        time_current = 0;
        enemies = new ArrayList<Zombie>();
    }

    public boolean isGameOver() {
        return enemies.isEmpty() && time_current >= time_length;
    }

    public boolean canBePlaced(int row, int col) {
        return tiles[row][col] == null;
    }

    public Plant findFront(int row) {
        int column = -1;
        //while (column )
    }

    public void placePlant() {

    }

    public void spawnZombie() {
        enemies.add(new Zombie((int)(Math.floor(Math.random() * (rows))), columns + 1));
        System.out.println("Time: " + time_current);
        System.out.printf("Spawned zombie at row %d, col %d\n", (int)enemies.getLast().getRowCoord(), (int)enemies.getLast().getColCoord());
        System.out.println();
        enemies.clear();
    }

    public void playerAction() {

    }

    public void gameCycle() {
        int interval = 0;
        int cout = 0;
        int i;
        boolean waveFlag = false;

        while (!isGameOver()) {
            // check time
            if (time_current >= 30 && time_current <= 80)
                interval = 10;
            else if (time_current >= 81 && time_current <= 140)
                interval = 5;
            else if (time_current >= 141 && time_current <= 170)
                interval = 3;

            // check once if its time for a huge wave of zombies
            if (time_current >= 170 & !waveFlag) {
                System.out.println("A huge wave of zombies are coming!");
                for (i = 0; i < 5; i++)
                    spawnZombie();

                waveFlag = true;
            }

            // check if its time to spawn one
            if (cout >= interval && time_current < time_length) {
                spawnZombie();
                cout = 0;
            }

            //behavior call
            for (i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).isAlive())
                    enemies.get(i).behaviour(tiles[(int)enemies.get(i).getRowCoord()][tiles.length]);
            }

            cout++;
            time_current++;
        }
    }

    private final int LEVEL_NUM;
    private int time_length;
    private int time_current;
    private int rows;
    private int columns;
    private Plant[][] tiles;
    private ArrayList<Zombie> enemies;
}

class LevelDriver {
    public static void main(String[] args) {
        Level level1 = new Level(1, 5, 9, 180);
        level1.gameCycle();
    }
}