import java.awt.*;
import java.io.File;
import java.net.URL;
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
    public LawnPanel(int width, int height, JButton forfeit)
    {
        int i;
        images = new ArrayList<>();
        //seedPackets = new SeedPacket[6];
        //seedPacketImgs = new ImageIcon[6];

        // get lawn bg
        try {
            lawnImg = new ImageIcon(getClass().getResource("/img/lawn/lawnImg.png"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // get seed slot img
        try {
            seedSlotImg = new ImageIcon(getClass().getResource("img/lawn/seedSlotImg.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // get entity images
        try {
            String url = getClass().getResource("/img/lawn/entities").getPath();
            System.out.println(url);
            File path = new File(url);
            entitiesFiles = path.listFiles();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // get game element images
        try {
            String url = getClass().getResource("/img/lawn/gameElements").getPath();
            System.out.println(url);
            File path = new File(url);
            gameElementsFiles = path.listFiles();

            if (gameElementsFiles != null)
                listFiles(gameElementsFiles);
            else
                System.out.println("no");
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // get seed packet images
        try {
            String url = getClass().getResource("/img/lawn/seedPackets").getPath();
            System.out.println(url);
            File path = new File(url);
            seedPacketsFiles = path.listFiles();

            if (seedPacketsFiles != null)
                listFiles(seedPacketsFiles);
            else
                System.out.println("no");
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TILE_HEIGHT = FIELD_HEIGHT / 5;
        TILE_WIDTH = FIELD_WIDTH / 9;

        setLayout(null);
        addComponents(width, height, forfeit);
    }

    /**
     * This method overrides the paintComponent method from the JPanel class, 
     * allowing the lawnPanel to draw/display the image. 
     * 
     */
    @Override
    public void paintComponent(Graphics g)
    {
        int i;
        super.paintComponent(g);

        if (lawnImg != null)
            g.drawImage(lawnImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

        if (seedSlotImg != null)
            g.drawImage(seedSlotImg.getImage(), 10,10, (int)(seedSlotImg.getIconWidth()*0.8),(int)(seedSlotImg.getIconHeight()*0.8), null);

        for (i = 0; i < images.size(); i++)
            if (images.get(i) != null)
                g.drawImage(images.get(i).getImage(), images.get(i).getPixelX(), images.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
    }

    public void listFiles(File[] files) {
        int i;

        for (File f : files)
            System.out.println(f.getName());
    }

    public void initializeSeedPackets(Plant[] plants) {
        int i;
    }

    public void addComponents(int width, int height, JButton forfeit) {
        // JLayeredPane to make way for drag and drop
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0, width, height);
        add(layeredPane);

        // sun count
        JLabel sunCount = new JLabel("0");
        sunCount.setFont(new Font("D050000L", Font.PLAIN,20));
        sunCount.setForeground(Color.WHITE);
        sunCount.setHorizontalTextPosition(JLabel.CENTER);
        sunCount.setBounds(87,20,100,30);
        layeredPane.add(sunCount);

        // drag and drop

        // forfeit button
        forfeit.setBounds(width - 150, 0, 100, 60);
        forfeit.setFont(new Font("Lucida Handwriting", Font.BOLD, 15));
        forfeit.setOpaque(true);
        forfeit.setBackground(Color.lightGray);
        forfeit.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        forfeit.setHorizontalTextPosition(JLabel.CENTER);
        layeredPane.add(forfeit);
    }

    public void clearImages() {
        images.clear();
        //seedPackets = null;
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
    private ImageIcon seedSlotImg;

    private final int FIELD_WIDTH = 534;
    private final int FIELD_HEIGHT = 460;
    private final int FIELD_X = 191;
    private final int FIELD_Y = 75;

    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;

    private ArrayList<GameImage> images;
    File[] entitiesFiles;
    File[] gameElementsFiles;
    File[] seedPacketsFiles;
}
