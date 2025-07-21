import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/** This class represents the Controller of the MVC design structure.
 *  It is responsible for the interaction between the Model and the View.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 *
 */
public class Controller implements ActionListener, MouseListener{
    /** This constructor initializes the Model and the View classes that will
     *  be interacting inside the Controller class.
     *
     * @param m the model class of the controller
     * @param v the view class of the controller
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        view.setMouseListener(this);

        player = new Player(200);
        model.selectLevel(1);
        //level = model.getLevelThread().getLevel();
        updateView();
    }

    public void updateView() {

        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                zombieUpdate();
                view.getLawn().repaint();
            }
        });
        timer.start();
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
        Level level = model.getLevelThread().getLevel();
        
        if(isWithinField(e.getX(), e.getY()))
        {
            //mouseX - fieldX / tileHeight
            col = (e.getX() - view.getLawn().getFieldPosX()) / view.getLawn().getTileWidth();
            row = (e.getY() - view.getLawn().getFieldPosY()) / view.getLawn().getTileHeight();

            //place a plant
            if(level.canBePlaced(row, col))
            {
                player.placePlant(level, row, col, "sunflower", 1);
                System.out.println("Plant position: " + level.getTiles()[row][col].getRow() + ", " + level.getTiles()[row][col].getCol());

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //view.getLawn().addImage(new GameImage(image, rowToPixel(row), columnToPixel(col)));
                        view.getLawn().addImage(new GameImage(level.getTiles()[row][col].getImageIcon(), columnToPixel(col), rowToPixel(row)));
                    }

                });

            }
            else
            {
                System.out.println("Tile occupied!");
            }
            
        
        }
        else
        {
            System.out.println("You are NOT in the field!");
        }
        
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

    public double rowToPixel(double row)
    {
        return row * view.getLawn().getTileHeight() + view.getLawn().getFieldPosY();
    }

    public double columnToPixel(double col)
    {
        return col * view.getLawn().getTileWidth() + view.getLawn().getFieldPosX();
    }

 
    public void zombieUpdate()
    {
        //access zombie array list in level
        ArrayList<Zombie> z = model.getLevelThread().getLevel().getEnemies();
        int i;
        double pixelX, pixelY;

        for(i = 0; i < z.size(); i++)
        {
            pixelY = rowToPixel(z.get(i).getRow());
            pixelX = columnToPixel(z.get(i).getCol());

            if(z.get(i).getGameImage() == null)
            {
                GameImage image = new GameImage(z.get(i).getImageIcon(), pixelX, pixelY);
                view.getLawn().addZombieImage(image);
                z.get(i).setGameImage(image);
            }
            else
            {
                //System.out.println("Update position!");
                System.out.println("col: " + z.get(i).getCol());
                z.get(i).getGameImage().setPixelX(pixelX);
                //System.out.println("Updated x: " + z.get(i).getGameImage().getPixelX());
                
            }

        }
    
    }

    /**the model class of the program*/
    private Model model;
    /**the view class of the program*/
    private View view;

    private Player player;

    public static void main(String[] args)
    {
        Model m = new Model();
        View v = new View();
        Controller c = new Controller(m, v);

        //it works, it only stopped (returned null) because zombie has reached the house
        m.getLevelThread().getLevel().getEnemies().add(new Zombie(0, 8, 0));
        //m.getLevelThread().getLevel().getEnemies().add(new Zombie(1, 7, 0));
        //m.getLevelThread().getLevel().getEnemies().add(new Zombie(2, 5, 0));
        //m.getLevelThread().getLevel().getEnemies().add(new Zombie(3, 4, 0));
    }

}
