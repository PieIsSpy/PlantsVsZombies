import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
                //ROW
            //1   2    3    4
        //1
  //C   //2
        //3
        //4

            //place a plant
            if(level.canBePlaced(row, col))
            {
                player.placePlant(level, row, col, "sunflower", 1);
                System.out.println("Plant position: " + level.getTiles()[row][col].getRow() + ", " + level.getTiles()[row][col].getCol());
                ImageIcon image = new ImageIcon(getClass().getResource(level.getTiles()[row][col].getImagePath()));
                //why tf doesnt it show the image when i get the image from the entity, and it only works when i hardcode it here??????????SJBBDJND

                //it doesnt load fast enough??

                //image.getImage().flush();
                //view.getLawn().addImage(new GameImage(image, 300, 300));


                //ImageIcon image = level.getTiles()[row][col].getImage();
                //new ImageIcon(image);
                //ImageIcon image = level.getTiles()[row][col].getImage();
               // image.getImage().flush();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //view.getLawn().addImage(new GameImage(image, rowToPixel(row), columnToPixel(col)));
                        view.getLawn().addImage(new GameImage(image, columnToPixel(col), rowToPixel(row)));
                    }

                });
                
                //view.getLawn().repaint(); 
                //view.getLawn().addImage(new GameImage(level.getTiles()[row][col].getImage(), rowToPixel(row), columnToPixel(col)));
                //System.out.println(rowToPixel(row) + ", " + columnToPixel(col));
                //view.repaint();
                //it doesnt work if i do a repaint here
                //it worked when i created the imageicon here 

            }
            else
            {
                System.out.println("Tile occupied!");
            }
            
            //System.out.println("Position: (" + row + ", " + col + ")");
        
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

    /**the model class of the program*/
    private Model model;
    /**the view class of the program*/
    private View view;

    private Player player;

}
