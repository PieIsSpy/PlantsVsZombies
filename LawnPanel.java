import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * This class represents the background lawn image in a 
 * Plants vs Zombies game. 
 * 
 */
public class LawnPanel extends JPanel {
    /**
     * This constructor initializes the file image of a lawn
     * background and sets its layout to null. 
     * 
     */
    public LawnPanel()
    {
        //to be updated in the future since there will be different maps
        //this constructor will probably need to accept a string pathway to allow multiple lawn images
        try {
            lawnImg = new ImageIcon(getClass().getResource("/img/lawnImage.png"));
            setLayout(null);
        }
        catch (Exception e) {
            System.out.println("Image cannot be loaded");
        }

        field = new JPanel(new GridLayout());

    }

    public void init()
    {
        //make a hhheno bdgetd
    }


    /**
     * This method overrides the paintComponent method from the JPanel class, 
     * allowing the lawnPanel to draw/display the image. 
     * 
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(lawnImg != null)
        {
             g.drawImage(lawnImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }
       
    }


    public int getFieldWidth()
    {
        return FIELD_WIDTH;
    }

    public int getFieldHeight()
    {
        return FIELD_HEIGHT;
    }

    public int getFieldPosX()
    {
        return FIELD_X;
    }

    public int getFieldPosY()
    {
        return FIELD_Y;
    }

    public int getTileWidth()
    {
        return TILE_WIDTH;
    }

    public int getTileHeight()
    {
        return TILE_HEIGHT;
    }


    /**lawn background image to be displayed */
    private ImageIcon lawnImg;

    JPanel field;

    private final int FIELD_WIDTH = 712;
    private final int FIELD_HEIGHT = 460;
    private final int FIELD_X = 253;
    private final int FIELD_Y = 72;

    private final int TILE_WIDTH = 70;
    private final int TILE_HEIGHT = 85;


}
