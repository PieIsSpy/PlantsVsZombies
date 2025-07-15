import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener{
    public Controller(MainModel m, ProgramGUI v) {
        model = m;
        view = v;

        view.setMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
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

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    private MainModel model;
    private ProgramGUI view;


}
