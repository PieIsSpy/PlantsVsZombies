import java.util.*;


/** This class serves as a demonstration to play 1 level of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 2.0
 *
 */
class LevelDriver {

    // the main method of the class
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long beforeInput;
        long afterInput;
        long totalEllapse = 0;
        int correctedTime = 0;
        boolean startFlag = false, endFlag = false;

        Level model = new Level(1, 180, 5, 9, (int)startTime/1000);
        Lawn view = new Lawn(model.getROWS(), model.getCOLUMNS());
        Player control = new Player(200);

        System.out.println("Level " + model.getLEVEL_NUM());
        while (!model.isGameOver() && !model.isGameWon(correctedTime)) {
            if (correctedTime >= 30 && !startFlag) {
                System.out.println("The zombies... are coming...");
                startFlag = true;
            }

            if (correctedTime >= 170 && !endFlag) {
                System.out.println("A huge wave of zombies are coming!");
                endFlag = true;
            }

            model.gameCycle(correctedTime);
            System.out.println("Time: " + correctedTime + "/" + model.getTIME_LENGTH());
            view.displayLawn(model.getTiles(), model.getEnemies());
            beforeInput = System.currentTimeMillis();
            view.playerAction(control, model, correctedTime);
            afterInput = System.currentTimeMillis();
            totalEllapse += (int)((afterInput - beforeInput) / 10);
            correctedTime = (int)((System.currentTimeMillis() - startTime - totalEllapse)/1000);
        }
    }
}