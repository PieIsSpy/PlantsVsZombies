import java.awt.Graphics;
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
            lawnImg = new ImageIcon(getClass().getResource("/img/lawnImg.png"));
            setLayout(null);
        }
        catch (Exception e) {
            System.out.println("Image cannot be loaded");
        }
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

    /**lawn background image to be displayed */
    private ImageIcon lawnImg;

}
