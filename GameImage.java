import java.awt.Image;

import javax.swing.ImageIcon;

public class GameImage {
    public GameImage(ImageIcon image, int pixelX, int pixelY)
    {
        this.image = image;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public void setPixelX(int x)
    {
        pixelX = x;
    }

    public void setPixelY(int y)
    {
        pixelY = y;
    }

    public ImageIcon getImageIcon()
    {
        return image;
    }

     public int getPixelX()
    {
        return pixelX;
    }

    public int getPixelY()
    {
        return pixelY;
    }

    private ImageIcon image;
    private int pixelX, pixelY;
    
}
