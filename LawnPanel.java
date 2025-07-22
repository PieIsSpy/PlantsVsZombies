import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class represents the Gameplay Gui of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class LawnPanel extends JPanel {
    /**
     * This constructor initializes the file image of a lawn
     * background and sets its layout to null. 
     *
     */
    public LawnPanel(int width, int height, JButton forfeit)
    {

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
        plantsImg = readFiles("/img/lawn/plants"); //already stores the images
        System.out.println("entitiesImg size: " + plantsImg.length);
        System.out.println();

        zombiesImg = readFiles("/img/lawn/zombies"); //already stores the images
        System.out.println("zombiesImgsize: " + zombiesImg.length);
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

        plantGameImages = new ArrayList<>();
        zombieGameImages = new ArrayList<>();
        seedPackets = new SeedPacket[6];

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

        if (lawnImg != null)
            g.drawImage(lawnImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

        if (seedSlotImg != null)
            g.drawImage(seedSlotImg.getImage(), 10,10, (int)(seedSlotImg.getIconWidth()*0.8),(int)(seedSlotImg.getIconHeight()*0.8), null);

        for(i = 0; i < plantGameImages.size(); i++)
        {
             g.drawImage(plantGameImages.get(i).getImageIcon().getImage(), (int)plantGameImages.get(i).getPixelX(), (int)plantGameImages.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
             //System.out.println("Paint!");
        }

        for(i = 0; i < zombieGameImages.size(); i++)
        {
            g.drawImage(zombieGameImages.get(i).getImageIcon().getImage(), (int)zombieGameImages.get(i).getPixelX(), (int)zombieGameImages.get(i).getPixelY(), TILE_WIDTH, TILE_HEIGHT, null);
            System.out.println("Pos image: " + (int)zombieGameImages.get(i).getPixelX() + ", " + (int)zombieGameImages.get(i).getPixelY());
        }



    }
    //if folder name is entities it will read the files in that folder
    public ImageIcon[] readFiles(String folderPath) {
        int i = 0;

        try {
            String url = getClass().getResource(folderPath).getPath(); //string pathname
            System.out.println(url);
            File path = new File(url); //creates a new file instance pointing to the location of that directory
            File[] files = path.listFiles(); //stores the files in the given path

            // read and store images
            if (files !=null) {
                if (folderPath.equalsIgnoreCase("/img/lawn/seedPackets")) {
                    plantNames = new String[files.length]; //instatiates the plant names based on number of files
                    readPlantNames(files);
                }

                System.out.println("Files read: " + files.length);
                ImageIcon[] container = new ImageIcon[files.length]; //stores the images in the given path
                readImages(files, container);

                System.out.println("Final check: ");
                while (i < container.length && container[i] != null) {
                    System.out.println(container[i].toString());
                    i++;
                }

                System.out.println("Returning " + i + " elements");

                return container; //returns the images in the given directory path
            }
        } catch (Exception e) {
            System.out.println("readFiles()");
            System.out.println(e.getMessage());
        }

        return null;
    }

    //stores the contents(images) in files to store in the container
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
        ImageIcon found;
        String name;

        for (i = 0; i < plants.length; i++) {
            name = plants[i].getName();
            found = seedPacketsImg[findSeedPacket(name)];
            seedPackets[i] = new SeedPacket(name, found, x, y);
            seedPackets[i].setBounds(0,0,getWidth(),getHeight());
            dragArea.add(seedPackets[i]);
            y += 65;
        }

        System.out.println(seedPackets.length);
        for (i = 0; i < seedPackets.length; i++)
            if (seedPackets[i] != null) {
                System.out.println(seedPackets[i].getName());
                System.out.println(seedPackets[i].getImageSprite());
                System.out.println(seedPackets[i].getImageCorner().x);
                System.out.println(seedPackets[i].getImageCorner().y);
            }
    }

    //returns the index of the found plant name and passes it to initializeSeedPackets
    public int findSeedPacket(String name) {
        int i;
        int found = -1;

        for (i = 0; i < plantNames.length && found == -1; i++)
            if (name.equalsIgnoreCase(plantNames[i])) {
                System.out.println(i + ") Found " + plantNames[i]);
                found = i;
            }

        return found;
    }

    public void addComponents(int width, int height, JButton forfeit) {
        // JLayeredPane to make way for drag and drop
        //layeredPane.setBounds(0,0, width, height);
        //add(layeredPane);

        // sun count
        sunCount = new JLabel("0");
        sunCount.setFont(new Font("D050000L", Font.PLAIN,20));
        sunCount.setForeground(Color.WHITE);
        sunCount.setHorizontalTextPosition(JLabel.CENTER);
        sunCount.setBounds(87,20,100,30);
        //layeredPane.add(sunCount);
        add(sunCount);

        // forfeit button
        forfeit.setBounds(width - 150, 0, 100, 60);
        forfeit.setFont(new Font("Lucida Handwriting", Font.BOLD, 15));
        forfeit.setOpaque(true);
        forfeit.setBackground(Color.lightGray);
        forfeit.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        forfeit.setHorizontalTextPosition(JLabel.CENTER);
        add(forfeit);
        //layeredPane.add(forfeit);

        // drag and drop container
        dragArea = new JPanel(new BorderLayout());
        dragArea.setOpaque(false);
        dragArea.setBounds(0,0,width,height);
        add(dragArea);
    }

    public void clearImages() {
        dragArea.removeAll();
        plantGameImages.clear();
        zombieGameImages.clear();

        int i;
        for (i = 0; i < seedPackets.length; i++)
            seedPackets[i] = null;

        //seedPackets = null;
    }

    public void addZombieImage(GameImage image)
    {
        zombieGameImages.add(image);
        System.out.println("Added zombie image!");
    }

    public void addPlantImage(GameImage image)
    {
        plantGameImages.add(image);
        System.out.println("Added plant image!");
    }

    public void updateSunCount(int sun) {
        sunCount.setText(Integer.toString(sun));
    }

    public ImageIcon[] getPlantImages()
    {
        return plantsImg;
    }

    public ImageIcon[] getZombieImages()
    {
        return zombiesImg;
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

    public SeedPacket[] getSeedPackets() {
        return seedPackets;
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

    private JPanel dragArea;
    private JLabel sunCount;

    private ArrayList<GameImage> plantGameImages;
    private ArrayList<GameImage> zombieGameImages;

    private SeedPacket[] seedPackets;
    private ImageIcon[] plantsImg;
    private ImageIcon[] zombiesImg;
    private ImageIcon[] gameElementsImg;
    private ImageIcon[] seedPacketsImg;
    private String[] plantNames;
}