import javax.swing.*;
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

        /*
        int row, col;
        //Level level = model.getLevelThread().getLevel();

        if(model.getLevelThread().getLevel() != null && isWithinField(e.getX(), e.getY()))
        {
            //mouseX - fieldX / tileHeight
            col = (e.getX() - view.getLawn().getFieldPosX()) / view.getLawn().getTileWidth();
            row = (e.getY() - view.getLawn().getFieldPosY()) / view.getLawn().getTileHeight();

            //place a plant
            if(model.getLevelThread().getLevel().canBePlaced(row, col))
            {

                model.getPlayer().placePlant(model.getLevelThread().getLevel(), row, col, "sunflower",0);
                System.out.println("Plant position: " + model.getLevelThread().getLevel().getTiles()[row][col].getRow() + ", " + model.getLevelThread().getLevel().getTiles()[row][col].getCol());
                view.getLawn().addImage(new GameImage(model.getLevelThread().getLevel().getTiles()[row][col].getImage(), columnToPixel(row), rowToPixel(col)));

            }
            else
            {
                System.out.println("Tile occupied!");
            }
            
            //System.out.println("Position: (" + row + ", " + col + ")");
            view.getLawn().revalidate();
            view.getLawn().repaint();
        }
        else
        {
            System.out.println("You are NOT in the field!");
        }

         */
        

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

    public boolean isWithinSeedPacket(SeedPacket s, int x, int y) {
        return x >= s.getImageCorner().x && x <= s.getImageCorner().x + s.getImage().getIconWidth() &&
                y >= s.getImageCorner().y && y <= s.getImageCorner().y + s.getImage().getIconHeight();
    }

    public int rowToPixel(int row)
    {
        return row * view.getLawn().getTileHeight() + view.getLawn().getFieldPosY();
    }

    public int columnToPixel(int col)
    {
        return col * view.getLawn().getTileWidth() + view.getLawn().getFieldPosX();
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
