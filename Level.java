import java.util.ArrayList;

public class Level {
    public Level (int l, int r, int c, int t) {
        LEVEL_NUM = l;
        rows = r;
        columns = c;
        time_length = t;
        time_current = 0;
        enemies = new ArrayList<Zombie>();
        tiles = new Plant[r][c];
    }

    public boolean isGameOver() {
        int i = 0;
        boolean condition = false;

        while (i < enemies.size() && !condition) {
            if (enemies.get(i).isAtHouse())
                condition = true;

            i++;
        }

        return condition;
    }

    public boolean isGameWon() {
        return enemies.isEmpty() && time_current >= time_length;
    }

    public boolean canBePlaced(int row, int col) {
        return tiles[row][col] == null;
    }

    public void placePlant(int r, int c) {
        if (canBePlaced(r, c))
            tiles[r][c] = new Sunflower();
    }

    public void spawnZombie() {
        enemies.add(new Zombie((int)(Math.floor(Math.random() * (rows))), columns + 1));
        //System.out.println("Time: " + time_current);
        System.out.printf("Spawned zombie at row %d, col %d\n", (int)enemies.getLast().getRowCoord(), (int)enemies.getLast().getColCoord());
        System.out.println();
        //enemies.clear();
    }

    public void playerAction() {

    }

    public void gameCycle() {
        int interval = 0;
        int cout = 0;
        int i;
        boolean waveFlag = false;

        while (!isGameWon() && !isGameOver()) {
            System.out.println("time: " + time_current);
            // check time
            if (time_current >= 30 && time_current <= 80) {
                if (time_current == 30)
                    System.out.println("The zombies are coming...");
                interval = 10;
            }
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
            if (interval != 0 & cout >= interval && time_current < time_length) {
                spawnZombie();
                cout = 0;
            }

            //behavior call
            for (i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).isAlive())
                    enemies.get(i).behaviour(tiles[(int)enemies.get(i).getRowCoord()]);
            }

            if (!isGameWon() && !isGameOver()) {
                cout++;
                time_current++;
            }
            else if (isGameOver())
                System.out.println("THE ZOMBIES ATE YOUR BRAIN!");
            else if (isGameWon())
                System.out.println("You won!");
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