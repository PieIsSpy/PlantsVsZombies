import javax.swing.*;
/**
 * This class represents an image to be used in the game. It allows
 * its image and its coordinates to be easily updated any time. 
 * 
 */
public class GameImage {
    /**
     * This constructor initializes the image, and coordinates 
     * of the GameImage object to be used in the game
     * 
     * @param image image to be displayed
     * @param pixelX x coordinate 
     * @param pixelY y coordinate
     */
    public GameImage(ImageIcon image, double pixelX, double pixelY)
    {
        this.image = image;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    /**
     * This method updates the image to be displayed in the
     * game. 
     * 
     * @param image image to be set
     */
    public void setImageIcon(ImageIcon image)
    {
        this.image = image;
    }

    /**
     * This method updates the x coordinate of the GameImage
     * object. 
     * 
     * @param x x coordinate to be set
     */
    public void setPixelX(double x)
    {
        pixelX = x;
    }

    /**
     * This method updates the y coordinate of the GameImage
     * object. 
     * 
     * @param y y coordinate to be set
     */
    public void setPixelY(double y)
    {
        pixelY = y;
    }

    /**
     * This method gets the current image to be displayed
     * in the game. 
     * 
     * @return current image
     */
    public ImageIcon getImageIcon()
    {
        return image;
    }

    /**
     * This method gets the current x coordinate of the 
     * GameImage object. 
     * 
     * @return current x coordinate
     */
     public double getPixelX()
    {
        return pixelX;
    }

    /**
     * This method gets the current y coordinate of the 
     * GameImage object. 
     * 
     * @return current y coordinate
     */
    public double getPixelY()
    {
        return pixelY;
    }

    /**
     * This method updates the slowed status of the 
     * GameImage object. 
     * 
     * @param b the boolean status to be set 
     */
    public void setSlowed(boolean b) {
        slowed = b;
    }

    /**
     * This method gets the slowed status of the 
     * GameImage object. 
     * 
     * @return slowed status of GameImage object
     */
    public boolean isSlowed() {
        return slowed;
    }

    private ImageIcon image;
    private double pixelX, pixelY;
    private boolean slowed;
}
