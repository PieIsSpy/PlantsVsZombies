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
                seedPacketUpdate();

                try {
                    view.getLawn().updateSunCount(model.getLevelThread().getPlayer().getSun());
                } catch (Exception ignore) {
                    // if player is null, it means there is no level being played
                }

                view.getLawn().repaint();

                if (model.getLevelResult() > -1) {
                    view.changePanel("result");
                    view.getResult().showMessage(model.getLevelResult());
                }
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
        // start a level
        if (e.getActionCommand().equals("Start") || e.getActionCommand().equals("Retry") || e.getActionCommand().equals("Next")) {
            System.out.println("Pressed start");
            model.setLevelResult(-1);
            model.selectLevel(model.getLevelProgress());
            view.getLawn().initializeSeedPackets(model.getLevelThread().getLevel().getAvaliable_plants());
            view.changePanel("lawn");
            updateView();
            System.out.println("Level " + model.getLevelThread().getLevel().getLEVEL_NUM());
        }

        // quit the game
        else if (e.getActionCommand().equals("Quit")) {
            System.out.println("Pressed quit");
            model.getLevelThread().interrupt();
            view.dispose();
            System.exit(0);
        }

        // pretermination of level
        else if (e.getActionCommand().equals("Forfeit")) {
            model.setLevelResult(-1);
            System.out.println("Pressed Forfeit");
            model.endLevel();
            view.clearLawn();
            view.changePanel("menu");
        }

        // back to menu
        else if (e.getActionCommand().equals("Back")) {
            model.setLevelResult(-1);
            view.changePanel("menu");
        }
    }

    /** This method is responsible for checking if any seed packets were clicked.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        /*
         * 1.) convert field coordinates to row and column
         * 2.) need to measure first where the field is
         */
        int i;
        drag = null; // stop tracking the previous seed packet
        System.out.println("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
        for (i = 0; i < view.getLawn().getSeedPackets().length; i++) { // for every seed packet
            // if the seed packet exists and the mouse is inside the area of its image
            if (view.getLawn().getSeedPackets()[i] != null && isWithinSeedPacket(view.getLawn().getSeedPackets()[i], e.getX(), e.getY())) {
                System.out.println("mouse is within " + view.getLawn().getSeedPackets()[i].getName() + ": " + isWithinSeedPacket(view.getLawn().getSeedPackets()[i], e.getX(), e.getY()));
                drag = view.getLawn().getSeedPackets()[i];
                drag.setPreviousPoint(e.getPoint());
            }
        }
    }

    /** This method is responsible for tracking the movement of the cursor.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            // check if there is anything to drag and the plant is ready to be planted
            if (drag != null && model.getLevelThread().isPlantReady(drag.getName())) {
                Point currentPoint = e.getPoint();

                // move the image (distance between vectors A and B = (xb - xa, yb - ya)
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

    /** This method is responsible for checking if mousekey1 has been released
     *  after pressing it.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int row, col;
        // if the draggable seed packet exists and its plant equivalent is ready
        if (drag != null && model.getLevelThread().isPlantReady(drag.getName())) {
            // snap the draggable back to its original position
            Point r = new Point(drag.getOriginalCorner().x, drag.getOriginalCorner().y);
            drag.getImageCorner().setLocation(r.x, r.y);

            // check if the last location of the mouse is in a tile
            if (isWithinField(e.getX(), e.getY())) {
                col = (e.getX() - view.getLawn().getFieldPosX()) / view.getLawn().getTileHeight();
                row = (e.getY() - view.getLawn().getFieldPosY()) / view.getLawn().getTileHeight();

                // if the tile is empty and the player has enough suns
                if (model.getLevelThread().getLevel().canBePlaced(row, col) && model.getLevelThread().hasEnoughSuns(drag.getName())) {
                   model.playerPlant(drag.getName(), row, col);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.getLawn().addPlantImage(new GameImage(choosePlantImage((Plant) model.getLevelThread().getLevel().getTiles()[row][col]), columnToPixel(col), rowToPixel(row)));
                        }
                    });
                }
            }

            view.repaint();
        }
    }

    /** This method checks if the mouse cursor's position is inside the lawn area.
     *
     * @param x the x position of the cursor
     * @param y the y position of the cursor
     * @return true if the cursor is inside the lawn area, false otherwise
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

    /** This method checks if the mouse cursor is inside the image area of a seed packet.
     *
     * @param s the seed packet to be checked
     * @param x the x position of the cursor
     * @param y the y position of the cursor
     * @return true if the cursor is inside the lawn area, false otherwise
     */
    public boolean isWithinSeedPacket(Draggable s, int x, int y) {
        return x >= s.getImageCorner().x && x <= s.getImageCorner().x + s.getImageSprite().getIconWidth() &&
                y >= s.getImageCorner().y && y <= s.getImageCorner().y + s.getImageSprite().getIconHeight();
    }

    /** This method converts the row coordinate of a tile grid into
     *  the width dimension of the LawnPanel class.
     *
     * @param row the row coordinate to be converted
     * @return the equivalent width dimension of the LawnPanel class.
     */
    public double rowToPixel(double row)
    {
        return row * view.getLawn().getTileHeight() + view.getLawn().getFieldPosY();
    }

    /** This method converts col row coordinate of a tile grid into
     *  the height dimension of the LawnPanel class.
     *
     *  @param col the col coordinate to be converted
     *  @return the equivalent height dimension of the LawnPanel class.
     */
    public double columnToPixel(double col)
    {
        return col * view.getLawn().getTileWidth() + view.getLawn().getFieldPosX();
    }

    /** This method updates the sprite position of a zombie sprite file.
     *
     */
    public void zombieUpdate()
    {
        //access zombie array list in level
        try {
            ArrayList<Zombie> z = model.getLevelThread().getLevel().getEnemies();
            int i;
            double pixelX, pixelY;
            GameImage image;

            for (i = 0; i < z.size(); i++) {
                //x and y coordinates conversion
                pixelY = rowToPixel(z.get(i).getRow()); //this makes the image move grid by grid instead of continuously/smoothly
                pixelX = columnToPixel(z.get(i).getCol());

                if (z.get(i).getGameImage() == null) {

                    image = new GameImage(chooseZombieImage(z.get(i)), pixelX, pixelY);
                    view.getLawn().addZombieImage(image);
                    z.get(i).setGameImage(image);
                    //System.out.println("Made game image!");

                } else {
                    //System.out.println("Update position!");

                    //System.out.printf("col: %.2f -> pixelX: %.2f\n", z.get(i).getCol(), pixelX);
                    if (z.get(i).getIsEating()) {
                        z.get(i).getGameImage().setImageIcon(chooseZombieImage(z.get(i)));
                    }

                    z.get(i).getGameImage().setPixelX(pixelX);
                    //System.out.println("Updated x: " + z.get(i).getGameImage().getPixelX());

                }

                //updates the image
                z.get(i).getGameImage().setImageIcon(chooseZombieImage(z.get(i)));
            }
        } catch (Exception e) {
            // ignore
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
        if(z.getIsEating())
        {
            image = zombieImages[1];
            //System.out.println("Added eating zombie!");
        }
        else
        {
            image = zombieImages[0];
            //System.out.println("Added walking zombie!");
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

    public void plantUpdate() {

    }

    /** This method updates the sprite image of a seed packet draggable object.
     *
     */
    public void seedPacketUpdate() {
        int i;

        if (model.getLevelThread().getLevel() != null && model.getLevelResult() == -1) {
            for (i = 0; i < view.getLawn().getSeedPackets().length && view.getLawn().getSeedPackets()[i] != null; i++) {
                String name = view.getLawn().getSeedPackets()[i].getName();

                if (!model.getLevelThread().isPlantReady(name) || !model.getLevelThread().hasEnoughSuns(name))
                    view.getLawn().getSeedPackets()[i].setFilterOpacity(true);
                else
                    view.getLawn().getSeedPackets()[i].setFilterOpacity(false);
            }
        }
    }

    public void sunUpdate()
    {
        //access the array list of suns in level
        //if it has landed add the image using its given position
        //convert it to its x and y coordinates

        
    }

    public void triggerLevelEndMessage(int n) {
        view.getResult().showMessage(n);
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
        //for suns
        //once clicked it should increment the sun counter
    }

    /**the model class of the program*/
    private Model model;
    /**the view class of the program*/
    private View view;
    /** the panel to be dragged*/
    private Draggable drag;
}
