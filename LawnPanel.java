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
        TILE_WIDTH = FIELD_WIDTH / 9;
        TILE_HEIGHT = FIELD_HEIGHT / 5;

        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;

        plantGameImages = new ArrayList<>();
        zombieGameImages = new ArrayList<>();
        seedPackets = new Draggable[6];

        setLayout(null);
        addComponents(forfeit);
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
            //System.out.println("Pos image: " + (int)zombieGameImages.get(i).getPixelX() + ", " + (int)zombieGameImages.get(i).getPixelY());
        }



    }

    /** This method is responsible for reading all files in a folder
     *  and converts them into an array of image icons.
     *
     * @param folderPath the folder to be read
     * @return the array of image icons read by the method
     */
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

    /** This method is responsible for reading all the image contents
     *  of a given file array and stores them into an image icon array
     *  container.
     *
     * @param files the files to be read
     * @param container the container to be stored
     */
    public void readImages(File[] files, ImageIcon[] container) {
        int i = 0;

        // for every file in the container, scan for images
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

    /** This method reads the filenames of a plant image source folder and
     *  stores them as plant names.
     *
     * @param files the files to be read
     */
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

    /** This method is responsible for initializing the draggable objects
     *  inside the seedPacket array.
     *
     * @param plants the plants to be represented as draggable objects
     */
    public void initializeSeedPackets(Plant[] plants) {
        int i;
        int x = 22, y = 75;
        ImageIcon found;
        String name;

        for (i = 0; i < plants.length; i++) {
            name = plants[i].getName();
            found = seedPacketsImg[findSeedPacket(name)];
            seedPackets[i] = new Draggable(name, found, x, y);
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

    /** This method searches for the given name inside the initialized
     *  plant names that could be used as a name of the draggable object.
     *
     * @param name the name of the plant to be searched
     * @return the index of the found plant name
     */
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

    /** This method is responsible for adding all the components needed
     *  to be rendered in the Lawn Panel.
     *
     * @param forfeit the forfeit button to be formatted
     */
    public void addComponents(JButton forfeit) {

        // sun count
        sunCount = new JLabel("0");
        sunCount.setFont(new Font("D050000L", Font.PLAIN,20));
        sunCount.setForeground(Color.WHITE);
        sunCount.setHorizontalTextPosition(JLabel.CENTER);
        sunCount.setBounds(87,20,100,30);
        add(sunCount);

        // forfeit button
        forfeit.setBounds(PANEL_WIDTH - 150, 0, 100, 60);
        forfeit.setFont(new Font("Lucida Handwriting", Font.BOLD, 15));
        forfeit.setOpaque(true);
        forfeit.setFocusPainted(false);
        forfeit.setBackground(Color.lightGray);
        forfeit.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        forfeit.setHorizontalTextPosition(JLabel.CENTER);
        add(forfeit);

        // drag and drop container
        dragArea = new JPanel(new BorderLayout());
        dragArea.setOpaque(false);
        dragArea.setBounds(0,0,PANEL_WIDTH,PANEL_HEIGHT);
        add(dragArea);
    }

    /** This method clears all entity, game element and draggable object
     *  renders of the Lawn Panel.
     *
     */
    public void clearImages() {
        dragArea.removeAll();
        plantGameImages.clear();
        zombieGameImages.clear();

        int i;
        for (i = 0; i < seedPackets.length; i++)
            seedPackets[i] = null;
    }

    /** This method adds an image into the arraylist of zombie images
     *  to be rendered
     *
     * @param image the image to be added for rendering
     */
    public void addZombieImage(GameImage image)
    {
        zombieGameImages.add(image);
        System.out.println("Added zombie image!");
    }

    /** This method adds an image into the arraylist of plant images
     *  to be rendered
     *
     * @param image the image to be added for rendering
     */
    public void addPlantImage(GameImage image)
    {
        plantGameImages.add(image);
        System.out.println("Added plant image!");
    }

    /** This method updates the sun count text of the panel
     *
     * @param sun the current amount of sun
     */
    public void updateSunCount(int sun) {
        sunCount.setText(Integer.toString(sun));
    }

    /** This method gets the array of plant image resources to be used
     *
     * @return the array of plant image resources
     */
    public ImageIcon[] getPlantImages()
    {
        return plantsImg;
    }

    /** This method gets the array of zombie image resources to be used
     *
     * @return the array of zombie image resources
     */
    public ImageIcon[] getZombieImages()
    {
        return zombiesImg;
    }

    /** This method gets the width of the lawn area.
     *
     * @return the width of the lawn area.
     */
    public int getFieldWidth()
    {
        return FIELD_WIDTH;
    }

    /** This method gets the height of the lawn area.
     *
     * @return the height of the lawn area.
     */
    public int getFieldHeight()
    {
        return FIELD_HEIGHT;
    }

    /** This method returns the x coordinate of the reference point of the lawn
     *  area.
     *
     * @return the x coordinate of the reference point of the lawn
     */
    public int getFieldPosX()
    {
        return FIELD_X;
    }

    /** This method returns the y coordinate of the reference point of the lawn
     *  area.
     *
     * @return the y coordinate of the reference point of the lawn
     */
    public int getFieldPosY()
    {
        return FIELD_Y;
    }

    /** This method returns the tile width of a lawn tile.
     *
     * @return the tile width of a lawn tile
     */
    public int getTileWidth()
    {
        return TILE_WIDTH;
    }

    /** This method returns the tile height of a lawn tile.
     *
     * @return the tile height of a lawn tile
     */
    public int getTileHeight()
    {
        return TILE_HEIGHT;
    }

    /** This method returns all draggable seed packets present in the lawn.
     *
     * @return all draggable seed packets present in the lawn
     */
    public Draggable[] getSeedPackets() {
        return seedPackets;
    }

    /// the panel width
    private final int PANEL_WIDTH;
    /// the panel height
    private final int PANEL_HEIGHT;
    /**lawn background image to be displayed */
    private ImageIcon lawnImg;
    /// the seed slot image to be displayed
    private ImageIcon seedSlotImg;
    /// the width of the lawn area
    private final int FIELD_WIDTH = 565;
    /// the height of the lawn area
    private final int FIELD_HEIGHT = 475;
    /// the x coordinate of the reference point of the lawn area
    private final int FIELD_X = 195;
    /// the y coordinate of the reference point of the lawn area
    private final int FIELD_Y = 85;
    /// the tile width of a lawn tile.
    private final int TILE_WIDTH;
    /// the tile height of a lawn tile.
    private final int TILE_HEIGHT;
    /// the container for draggable objects
    private JPanel dragArea;
    /// the label text for the player sun count
    private JLabel sunCount;
    /// the plants to be rendered
    private ArrayList<GameImage> plantGameImages;
    /// the zombies to be rendered
    private ArrayList<GameImage> zombieGameImages;
    /// the draggable seed packets to be used
    private Draggable[] seedPackets;
    /// the image resources for plants
    private ImageIcon[] plantsImg;
    ///the image resources for zombies
    private ImageIcon[] zombiesImg;
     ///the image resources for game elements
    private ImageIcon[] gameElementsImg;
    ///the image resources for seed packets
    private ImageIcon[] seedPacketsImg;
    ///the names of plants to be represented in a draggable object
    private String[] plantNames;
}