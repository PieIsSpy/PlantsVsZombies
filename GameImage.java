import java.awt.Image;

import javax.swing.ImageIcon;

public class GameImage {
    public GameImage(ImageIcon image, int pixelX, int pixelY)
    {
        this.image = image;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public ImageIcon getIcon()
    {
        return image;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public int getPixelX()
    {
        return pixelX;
    }

    public int getPixelY()
    {
        return pixelY;
    }

    public void setPixelX(int x)
    {
        pixelX = x;
    }

    public void setPixelY(int y)
    {
        pixelY = y;
    }

    public void setImagePath(String path)
    {
        imagePath = path;
    }


    private ImageIcon image;
    private int pixelX, pixelY;
    private String imagePath;
    
}
