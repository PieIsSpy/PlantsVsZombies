import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
            lawnImg = new ImageIcon(getClass().getResource("/img/lawnImg.png"));
            setLayout(null);
        }
        catch (Exception e) {
            System.out.println("Image cannot be loaded");
        }

        TILE_HEIGHT = FIELD_HEIGHT / 5;
        TILE_WIDTH = FIELD_WIDTH / 9;

        images = new ArrayList<>();
        zombieImages = new ArrayList<>();

        init();

    }

    
    public void init()
    {

        /* 
        ImageIcon image = new ImageIcon(getClass().getResource("/img/sunflower.gif"));
        JLabel flower = new JLabel(image);
        flower.setSize(TILE_WIDTH, TILE_HEIGHT);
        flower.setLocation(191, 75);
        
        add(flower);
        */ 
       
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
        g.drawImage(lawnImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

        for(int i = 0; i < images.size(); i++)
        {
             g.drawImage(images.get(i).getImageIcon().getImage(), (int)images.get(i).getPixelX(), (int)images.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
        }
        
        for(int i = 0; i < zombieImages.size(); i++)
        {
            g.drawImage(zombieImages.get(i).getImageIcon().getImage(), (int)zombieImages.get(i).getPixelX(), (int)zombieImages.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
            System.out.println("Updated image x: " + (int) zombieImages.get(i).getPixelX());
        }
    }
    
    public void addImage(GameImage image)
    {
        images.add(image);
        System.out.println("Added image!" + image.getPixelX());

        //repaint();
    }

    public void addZombieImage(GameImage image)
    {
        zombieImages.add(image);
        System.out.println("Added zombie image!");

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

    private final int FIELD_WIDTH = 534;
    private final int FIELD_HEIGHT = 460;
    private final int FIELD_X = 191;
    private final int FIELD_Y = 75;

    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;

    private ArrayList<GameImage> images;
    private ArrayList<GameImage> zombieImages;


}
