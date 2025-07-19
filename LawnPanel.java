import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

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
    public LawnPanel(int width, int height)
    {
        //to be updated in the future since there will be different maps
        //this constructor will probably need to accept a string pathway to allow multiple lawn images
        try {
            lawnImg = new ImageIcon(getClass().getResource("/img/lawnImg.png"));
        }
        catch (Exception e) {
            System.out.println("Image cannot be loaded");
        }

        TILE_HEIGHT = FIELD_HEIGHT / 5;
        TILE_WIDTH = FIELD_WIDTH / 9;

        images = new ArrayList<>();

        setLayout(new BorderLayout());
        addComponents(width, height);
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
             g.drawImage(images.get(i).getImage(), images.get(i).getPixelX(), images.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
        }
    }

    public void addComponents(int width, int height) {
        // set border for seed slots
        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(width/5, height));
        left.setBackground(new Color(255,255,100,150));
        left.setOpaque(false);

        // get seed slots image
        ImageIcon source = new ImageIcon(getClass().getResource("/img/seedSlotImg.png"));
        Image scaled = source.getImage().getScaledInstance((int)(source.getIconWidth()*0.8),(int)(source.getIconHeight()*0.8), Image.SCALE_SMOOTH);
        ImageIcon seedSlot = new ImageIcon(scaled);
        JLabel container = new JLabel();
        container.setIcon(seedSlot);
        container.setHorizontalAlignment(JLabel.CENTER);

        // sun count text
        /*
        JPanel test = new JPanel(new BorderLayout());
        test.setBackground(new Color(100,100,100,180));
        test.setPreferredSize(new Dimension(width/5, 200));
        container.add(test, BorderLayout.NORTH);

         */
        //JLabel sunCount = new JLabel("0");
        //container.add(sunCount, BorderLayout.NORTH);

        // add components
        left.add(container, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);
    }

    public void addImage(GameImage image)
    {
        images.add(image);
        System.out.println("Added image!");
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

    private final int FIELD_WIDTH = 534;
    private final int FIELD_HEIGHT = 460;
    private final int FIELD_X = 191;
    private final int FIELD_Y = 75;

    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;

    private ArrayList<GameImage> images;
    private ImageIcon[] seedPackets;
}
