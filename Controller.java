import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

/** This class represents the Controller of the MVC design structure.
 *  It is responsible for the interaction between the Model and the View.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 *
 */
public class Controller implements ActionListener, MouseListener, MouseMotionListener {
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
        view.setMouseListener(this);
        view.setMouseMotionListener(this);

        System.out.println("Main Thread: " + Thread.currentThread().getName());
        System.out.println("Level Thread: " + m.getLevelThread().getName());

        //player = new Player(200);
        //model.selectLevel(1);
        //level = model.getLevelThread().getLevel();
        //updateView();
    }

    /**
     * This method updates the GUI at a certain frame per second. 
     * 
     * 
     */
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
            model.selectLevel(model.getLevelProgress());
            view.getLawn().initializeSeedPackets(model.getLevelThread().getLevel().getAvaliable_plants());
            view.changePanel("lawn");
            updateView();
            System.out.println("Level " + model.getLevelThread().getLevel().getLEVEL_NUM());
        }
        else if (e.getActionCommand().equals("Quit")) {
            System.out.println("Pressed quit");
            model.getLevelThread().interrupt();
            view.dispose();
            System.exit(0);
        }

        // pretermination
        else if (e.getActionCommand().equals("Forfeit")) {
            System.out.println("Pressed Forfeit");
            model.endLevel();
            view.clearLawn();
            view.changePanel("menu");
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        /*
         * 1.) convert field coordinates to row and column
         * 2.) need to measure first where the field is
         */
        int i;
        drag = null;
        System.out.println("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
        for (i = 0; i < view.getLawn().getSeedPackets().length; i++) {
            if (isWithinSeedPacket(view.getLawn().getSeedPackets()[i], e.getX(), e.getY())) {
                System.out.println("mouse is within " + view.getLawn().getSeedPackets()[i].getName() + ": " + isWithinSeedPacket(view.getLawn().getSeedPackets()[i], e.getX(), e.getY()));
                drag = view.getLawn().getSeedPackets()[i];
                drag.setPreviousPoint(e.getPoint());
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            if (drag != null) {
                Point currentPoint = e.getPoint();
                drag.getImageCorner().translate(
                        (int) (currentPoint.getX() - drag.getPreviousCorner().getX()),
                        (int) (currentPoint.getY() - drag.getPreviousCorner().getY())
                );

                drag.setPreviousPoint(currentPoint);
                view.repaint();
            }
        } catch (Exception ex) {
            System.out.println("No components being dragged");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Point r = new Point(drag.getOriginalCorner().x, drag.getOriginalCorner().y);
            drag.getImageCorner().setLocation(r.x, r.y);
            view.repaint();
        } catch (Exception ex) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int row, col;

        if(model.getLevelThread().getLevel() != null && isWithinField(e.getX(), e.getY()))
        {
            //mouseX - fieldX / tileHeight
            col = (e.getX() - view.getLawn().getFieldPosX()) / view.getLawn().getTileWidth();
            row = (e.getY() - view.getLawn().getFieldPosY()) / view.getLawn().getTileHeight();

            //place a plant
            if(model.getLevelThread().getLevel().canBePlaced(row, col))
            {
                model.getPlayer().placePlant(model.getLevelThread().getLevel(), row, col, "sunflower", 1);
                System.out.println("Plant position: " + model.getLevelThread().getLevel().getTiles()[row][col].getRow() + ", " + model.getLevelThread().getLevel().getTiles()[row][col].getCol());

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        view.getLawn().addPlantImage(new GameImage(choosePlantImage((Plant) model.getLevelThread().getLevel().getTiles()[row][col]), columnToPixel(col), rowToPixel(row)));
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

    /**
     * This method determines if the given x and y coordinates are 
     * within the playing field of the game. This field is the only valid 
     * location of where the plants are to be placed by the user. 
     * 
     * @param x x coordinate 
     * @param y y coordinate 
     * @return
     */
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

    /**
     * This method 
     * 
     * 
     * @param s
     * @param x
     * @param y
     * @return
     */
    public boolean isWithinSeedPacket(SeedPacket s, int x, int y) {
        return x >= s.getImageCorner().x && x <= s.getImageCorner().x + s.getImage().getIconWidth() &&
                y >= s.getImageCorner().y && y <= s.getImageCorner().y + s.getImage().getIconHeight();
    }

    /**
     * This method converts the row position of an object to its 
     * corresponding y coordinate in the GUI. 
     * 
     * 
     * @param row row position 
     * @return result of converting a column position to a y coordinate
     */
    public double rowToPixel(double row)
    {
        return row * view.getLawn().getTileHeight() + view.getLawn().getFieldPosY();
    }

    /**
     * This method converts the column position of an object to 
     * its corresponding x coordinate in the GUI. 
     * 
     * 
     * @param col column position 
     * @return result of converting a column position to an x coordinate
     */
    public double columnToPixel(double col)
    {
        return col * view.getLawn().getTileWidth() + view.getLawn().getFieldPosX();
    }

    /**
     * This method updates the images of a Zombie object shown in the GUI as the 
     * game progresses.
     * 
     * 
     */
    public void zombieUpdate()
    {
        //access zombie array list in level
        ArrayList<Zombie> z = model.getLevelThread().getLevel().getEnemies();
        int i;
        double pixelX, pixelY;
        GameImage image;

        for(i = 0; i < z.size(); i++)
        {
            //x and y coordinates conversion
            pixelY = rowToPixel(z.get(i).getRow()); 
            pixelX = columnToPixel(z.get(i).getCol());

            if(z.get(i).getGameImage() == null)
            {
                image = new GameImage(chooseZombieImage(z.get(i)), pixelX, pixelY);
                view.getLawn().addZombieImage(image);
                z.get(i).setGameImage(image);
            }
            else
            {
                //updates the positon of image
                z.get(i).getGameImage().setPixelX(pixelX);
            }

            //updates the image 
            z.get(i).getGameImage().setImageIcon(chooseZombieImage(z.get(i)));

        }
    }

    /**
     * This method returns the corresponding image of the given
     * Zombie object. It will return the image depending on the state of the 
     * zombie, whether it is walking or eating. It also considers other zombie 
     * variants. 
     *  
     * @param z Zombie object 
     * @return image icon assigned to the Zombie object
     */
    public ImageIcon chooseZombieImage(Zombie z)
    {
        ImageIcon image = null;
        ImageIcon[] zombieImages = view.getLawn().getZombieImages();
        if(z instanceof Zombie)
        {
            if(z.getIsEating())
            {
                image = zombieImages[1];
                System.out.println("Added eating zombie!");
            }
            else
            {
                image = zombieImages[0];
                System.out.println("Added walking zombie!");
            }

        }

        return image;
    }

    /**
     * This method returns the corresponding image of the given Plant object, 
     * depending on what type of plant it is. 
     * 
     * @param p the Plant object that will be used to find its corresponding image
     * @return Image Icon assigned to the Plant object
     */
    public ImageIcon choosePlantImage(Plant p)
    {
        ImageIcon image = null;
        ImageIcon[] plantImages = view.getLawn().getPlantImages();
        if(p instanceof Sunflower)
        {
            image = plantImages[0];
        }

        return image;
    }

    public void updateSunCount() {

    }

    /**the model class of the program*/
    private Model model;
    /**the view class of the program*/
    private View view;
    private SeedPacket drag;
    //private Level level;
    //private Player player;

}
