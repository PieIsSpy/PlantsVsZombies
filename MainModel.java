public class MainModel {
    public void selectLevel(int levelNum) {
        if (levelNum == 1 && levelProgress >= 0) {
            Level1 level1 = new Level1((int)System.currentTimeMillis()/1000);
            levelDriver(level1);
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
    }

    public void levelDriver(Level level) {
        while (!level.isGameOver() && level.isGameWon((int)System.currentTimeMillis()/1000)) {
            level.gameCycle((int)System.currentTimeMillis()/1000);
        }

        if (level.isGameWon((int)System.currentTimeMillis()/1000))
            levelProgress++;
    }

    private int levelProgress = 0;
}

