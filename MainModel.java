public class MainModel {
    public MainModel() {
        inProgress = false;
    }

    public void selectLevel(int levelNum) {
        System.out.println("Called selectLevel()");

        // if there are no currently running games
        if (t == null && !isInProgress()) {
            // set the level
            if (levelNum == 1 && levelProgress >= 0)
                level = new Level1((int)System.currentTimeMillis()/1000);
            else if (levelNum == 2 && levelProgress >= 1)
                level = new Level2((int)System.currentTimeMillis()/1000);
            else if (levelNum == 3 && levelProgress >= 2)
                level = new Level3((int)System.currentTimeMillis()/1000);

            // make a new thread for the level
            t = new LevelThread(level);
            t.start();
            inProgress = true;

            // check if the game is over
            while (t.isAlive()) {
                if (((LevelThread) t).checkGameStatus() > 0) {
                    if (((LevelThread) t).checkGameStatus() == 2) {
                        System.out.println("Level won");
                        levelProgress++;
                    } else
                        System.out.println("Level lost");

                    endLevel();
                }
            }
        }
        else
            System.out.println("A level is currently being played");
    }

    public void endLevel() {
        if (t != null) {
            System.out.println("Level has ended");
            ((LevelThread)t).setRunning(false);
            t.interrupt();

            inProgress = false;
            t = null;
            level = null;
        }
        else
            System.out.println("There is no level currently running");
    }

    public boolean isInProgress() {
        return inProgress;
    }

    private boolean inProgress;
    private int levelProgress = 0;
    private Thread t;
    private Level level;
}

