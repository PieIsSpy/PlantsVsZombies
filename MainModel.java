public class MainModel extends Thread {
    public MainModel() {
        inProgress = false;
    }

    public void selectLevel(int levelNum) {
        System.out.println("Called selectLevel()");
        if (levelNum == 1 && levelProgress >= 0) {
            Level1 level1 = new Level1((int)System.currentTimeMillis()/1000);
            //new Thread ()

            level1 = null;
        }
        else if (levelNum == 2 && levelProgress >= 1) {
            Level2 level2 = new Level2((int)System.currentTimeMillis()/1000);
            levelDriver(level2);
            level2 = null;
        }
        else if (levelNum == 3 && levelProgress >= 2) {
            Level3 level3 = new Level3((int)System.currentTimeMillis()/1000);
            levelDriver(level3);
            level3 = null;
        }
        else
            System.out.println("Level not yet unlocked");
    }

    public void levelDriver(Level level) {
        int levelStart, levelTimer, lastPrint = -1;
        System.out.println("Level running");
        inProgress = true;

        levelStart = (int)System.currentTimeMillis()/1000;
        do {
            levelTimer = (int)System.currentTimeMillis()/1000 - levelStart;

            if (System.currentTimeMillis() % 1000 == 0 && levelTimer != lastPrint) {
                System.out.println("Level 1 running: " + (levelTimer - levelProgress));
                lastPrint = levelTimer;
            }

            level.gameCycle(levelTimer);
        } while (!level.isGameOver() && !level.isGameWon(levelTimer));
        System.out.println("done");

        inProgress = false;

        if (level.isGameWon((int)System.currentTimeMillis()/1000))
            levelProgress++;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    private boolean inProgress;
    private int levelProgress = 0;
}

