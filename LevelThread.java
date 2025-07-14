public class LevelThread extends Thread {
    public LevelThread(Level level) {
        this.level = level;
        running = true;
        levelStart = (int)System.currentTimeMillis()/1000;
        levelTimer = 0;
    }

    public void run() {
        int lastPrint = -1;
        System.out.println("Level driver running");

        do {
            levelTimer = (int)System.currentTimeMillis()/1000 - levelStart;

            if (System.currentTimeMillis() % 1000 == 0 && levelTimer != lastPrint) {
                System.out.println("Level 1 running: " + (levelTimer));
                lastPrint = levelTimer;
            }

            level.gameCycle(levelTimer);
        } while (running && !level.isGameOver() && !level.isGameWon(levelTimer));

        running = false;
        System.out.println("Level Done");
    }

    public void setRunning(boolean b) {
        running = b;
    }

    public int checkGameStatus() {
        if (level.isGameWon(levelTimer)) {
            //System.out.println(2);
            return 2;
        }
        else if (level.isGameOver()) {
            //System.out.println(1);
            return 1;
        } else {
            //System.out.println(0);
            return 0;
        }
    }

    private Level level;
    private boolean running;
    private int levelStart;
    private int levelTimer;
}