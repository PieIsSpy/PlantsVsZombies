import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This class represents the Controller of the MVC design structure.
 *  It is responsible for the interaction between the Model and the View.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 *
 */
public class Controller implements ActionListener {
    /** This constructor initializes the Model and the View classes that will
     *  be interacting inside the Controller class.
     *
     * @param m the model class of the controller
     * @param v the view class of the controller
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        view.setActionListener(this);
    }

    public void updateView() {

    }

    /** This method is responsible for communicating the events done in the View
     *  class to the Model class.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // menu
        if (e.getActionCommand().equals("Start")) {
            System.out.println("Pressed start");
            //view.levelSelect();
        }
        else if (e.getActionCommand().equals("Quit")) {
            System.out.println("Pressed quit");
            model.getLevelThread().interrupt();
            view.dispose();
            System.exit(0);
        }

        /*
        // level selector
        else if(e.getActionCommand().equals("Level 1")) {
            if (model.getLevelThread().getLevel() == null) {
                System.out.println("Pressed Level 1");
                model.selectLevel(1);
            }
            else
                System.out.println("A level is already running!");
        }
        else if (e.getActionCommand().equals("Level 2")) {
            if (model.getLevelThread().getLevel() == null) {
                System.out.println("Pressed Level 2");
                model.selectLevel(2);
            }
            else
                System.out.println("A level is already running!");
        }
        else if (e.getActionCommand().equals("Level 3")) {
            if (model.getLevelThread().getLevel() == null) {
                System.out.println("Pressed Level 3");
                model.selectLevel(3);
            }
            else
                System.out.println("A level is already running!");
        }

        // pretermination
        else if (e.getActionCommand().equals("Forfeit")) {
            System.out.println("Pressed Forfeit");
            model.endLevel();
        }

         */
    }

    /**the model class of the program*/
    private Model model;
    /**the view class of the program*/
    private View view;
}
