/** This class represents the runnable file for the game
 *  Plants vs Zombies.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class PlantsVsZombies {
    /** This method is responsible for letting the model and view
     *  communicate with each other through the use of a controller.
     *  This also makes the class responsible for running the game.
     *
     * @param args the command-line arguments passed to the Java program when executed.
     */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}
