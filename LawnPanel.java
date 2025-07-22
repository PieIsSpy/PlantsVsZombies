import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
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
    public LawnPanel(int width, int height, JButton forfeit)
    {
        // array list initialization
        images = new ArrayList<>();

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

        // read image files
        entitiesImg = readFiles("/img/lawn/entities");
        System.out.println("entitiesImg size: " + entitiesImg.length);
        System.out.println();

        gameElementsImg = readFiles("/img/lawn/gameElements");
        System.out.println("gameElementsImg size: " + gameElementsImg.length);
        System.out.println();

        seedPacketsImg = readFiles("/img/lawn/seedPackets");
        System.out.println("seedPacketsImg size: " + seedPacketsImg.length);
        System.out.println();

        // lawn area
        TILE_HEIGHT = FIELD_HEIGHT / 5;
        TILE_WIDTH = FIELD_WIDTH / 9;

        images = new ArrayList<>();
        zombieImages = new ArrayList<>();

        layeredPane = new JLayeredPane();
        setLayout(null);
        addComponents(width, height, forfeit);

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
        int i;
        super.paintComponent(g);

        for(i = 0; i < images.size(); i++)
        {
             g.drawImage(images.get(i).getImageIcon().getImage(), (int)images.get(i).getPixelX(), (int)images.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
        }
        
        for(i = 0; i < zombieImages.size(); i++)
        {
            g.drawImage(zombieImages.get(i).getImageIcon().getImage(), (int)zombieImages.get(i).getPixelX(), (int)zombieImages.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
            //System.out.println("Updated image x: " + (int) zombieImages.get(i).getPixelX());
        }
        if (lawnImg != null)
            g.drawImage(lawnImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

        if (seedSlotImg != null)
            g.drawImage(seedSlotImg.getImage(), 10,10, (int)(seedSlotImg.getIconWidth()*0.8),(int)(seedSlotImg.getIconHeight()*0.8), null);

    }

    public ImageIcon[] readFiles(String folderPath) {
        int i = 0;

        try {
            String url = getClass().getResource(folderPath).getPath();
            System.out.println(url);
            File path = new File(url);
            File[] files = path.listFiles();

            // read and store images
            if (files !=null) {
                if (folderPath.equalsIgnoreCase("/img/lawn/seedPackets")) {
                    plantNames = new String[files.length];
                    readPlantNames(files);
                }

                System.out.println("Files read: " + files.length);
                ImageIcon[] container = new ImageIcon[files.length];
                readImages(files, container);

                System.out.println("Final check: ");
                while (i < container.length && container[i] != null) {
                    System.out.println(container[i].toString());
                    i++;
                }

                System.out.println("Returning " + i + " elements");

                return container;
            }
        } catch (Exception e) {
            System.out.println("readFiles()");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void readImages(File[] files, ImageIcon[] container) {
        int i = 0;

        for (File f : files) {
            try {
                System.out.println(f.getName());
                container[i] = new ImageIcon(ImageIO.read(files[i]));
                i++;
            } catch (Exception e) {
                System.out.println("readImages()");
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Images read: " + i);
    }

    public void readPlantNames(File[] files) {
        int i = 0;
        String name;

        for (File f : files) {
            try {
                name = f.getName();
                plantNames[i] = name.substring(0,name.lastIndexOf("."));
                i++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void initializeSeedPackets(Plant[] plants) {
        int i;
        int x = 22, y = 75;

        seedPackets = new SeedPacket[plants.length];
        System.out.println(plants.length);
        for (i = 0; i < plants.length; i++) {
            seedPackets[i] = new SeedPacket(plants[i].getName(), seedPacketsImg[findSeedPacket(plants[i].getName())], x, y); //makes a seedpacket 
            System.out.println(seedPackets[i].getName());
            try {
                ImageIcon found = seedPacketsImg[findSeedPacket(plants[i].getName())];
                seedPackets[i] = new SeedPacket(plants[i].getName(), found, x, y); 
                layeredPane.add(seedPackets[i]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            y += 65;
        }

        System.out.println("Seed packets: " + seedPackets.length);
    }

    //returns the index of the found plant name and passes it to initializeSeedPackets
    public int findSeedPacket(String name) {
        int i;
        int found = -1;

        for (i = 0; i < plantNames.length & found == -1; i++)
            if (name.equalsIgnoreCase(plantNames[i])) {
                System.out.println(i + ") Found " + plantNames[i]);
                found = i;
            }

        return found;
    }

    public void addComponents(int width, int height, JButton forfeit) {
        // JLayeredPane to make way for drag and drop

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
        int i;
        images.clear();

        /*
        for (i = 0; i < seedPackets.length; i++)
            seedPackets[i] = null;

         */
        seedPackets = null;
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
    private ImageIcon seedSlotImg;

    private final int FIELD_WIDTH = 534;
    private final int FIELD_HEIGHT = 460;
    private final int FIELD_X = 191;
    private final int FIELD_Y = 75;

    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;

    JLayeredPane layeredPane;

    private ArrayList<GameImage> images;
    private ArrayList<GameImage> zombieImages;


    private SeedPacket[] seedPackets;
    private ImageIcon[] entitiesImg;
    private ImageIcon[] gameElementsImg;
    private ImageIcon[] seedPacketsImg;
    private String[] plantNames;
}
