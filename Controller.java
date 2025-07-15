import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller {
    public Controller(MainModel m, ProgramGUI v) {
        model = m;
        view = v;
    }

    public void updateView() {

    }

    /* 
    @Override
    public void actionPerformed(ActionEvent e) {
        // menu
        if (e.getActionCommand().equals("Start")) {
            System.out.println("Pressed start");
            view.levelSelect();
        }
        else if (e.getActionCommand().equals("Quit")) {
            System.out.println("Pressed quit");
            view.dispose();
        }

        // level selector
        else if(e.getActionCommand().equals("Level 1")) {
            if (!model.isInProgress()) {
                System.out.println("Pressed Level 1");
                model.selectLevel(1);
            }
            else
                System.out.println("A level is already running!");
        }
        else if (e.getActionCommand().equals("Level 2")) {
            if (!model.isInProgress()) {
                System.out.println("Pressed Level 2");
                model.selectLevel(2);
            }
            else
                System.out.println("A level is already running!");
        }
        else if (e.getActionCommand().equals("Level 3")) {
            if (!model.isInProgress()) {
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
    }
*/ 



    private MainModel model;
    private ProgramGUI view;


}
