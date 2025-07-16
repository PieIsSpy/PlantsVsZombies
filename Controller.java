import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener, MouseListener{
    public Controller(MainModel m, ProgramGUI v) {
        model = m;
        view = v;

        view.setMouseListener(this);
    }



    public void updateView() {

    }

    
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


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        /**
         * 1.) convert field coordinates to row and column
         * 2.) need to measure first where the field is 
         * 
         * 
         */

         System.out.println("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
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

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        int row, col;
        
        if(isWithinField(e.getX(), e.getY()))
        {
            //mouseX - fieldX / tileHeight
            row = (e.getX() - view.getLawn().getFieldPosX()) / view.getLawn().getTileWidth();
            col = (e.getY() - view.getLawn().getFieldPosY()) / view.getLawn().getTileHeight();

            //place a plant
    
        
            
            
            System.out.println("Position: (" + row + ", " + col + ")");
        }
        else
        {
            System.out.println("You are NOT in the field!");
        }
        

        //convert mouse coordinates to row and column values in field
        /* 
        if(e.getX() < view.getLawn().getFieldPosX() || e.getX() > view.getLawn().getFieldPosX())
        {
            System.out.println("");
        }
        */
    }

    //not sure if this is supposed to be in controller or gui
    public boolean isWithinField(int x, int y)
    {
        boolean isXValid = false, isYValid = false;

        if(x >= view.getLawn().getFieldPosX() && x <= (view.getLawn().getFieldPosX() + view.getLawn().getFieldWidth()))
        {
            isXValid = true;
        }
        if(y >= view.getLawn().getFieldPosY() && y <= (view.getLawn().getFieldPosY() + view.getLawn().getFieldHeight()))
        {
            isYValid = true;
        }

        return isXValid && isYValid;
    }

    public int rowToPixel(int row)
    {
        return row * view.getLawn().getTileHeight() + view.getLawn().getFieldPosY();
    }

    public int columnToPixel(int col)
    {
        return col * view.getLawn().getTileWidth() + view.getLawn().getFieldPosX();
    }

    private MainModel model;
    private ProgramGUI view;



}
