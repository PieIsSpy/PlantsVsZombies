import java.awt.Image;

import javax.swing.*;

public class GameImage extends JPanel {
    public GameImage(Image image, int pixelX, int pixelY)
    {
        this.image = image;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public Image getImage()
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


    private Image image;
    private int pixelX, pixelY;
    
}
