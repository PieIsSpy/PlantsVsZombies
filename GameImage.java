import javax.swing.*;

public class GameImage {
    public GameImage(ImageIcon image, double pixelX, double pixelY)
    {
        this.image = image;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public void setImageIcon(ImageIcon image)
    {
        this.image = image;
    }

    public void setPixelX(double x)
    {
        pixelX = x;
    }

    public void setPixelY(double y)
    {
        pixelY = y;
    }

    public ImageIcon getImageIcon()
    {
        return image;
    }

     public double getPixelX()
    {
        return pixelX;
    }

    public double getPixelY()
    {
        return pixelY;
    }

    public void setSlowed(boolean b) {
        slowed = b;
    }

    public boolean isSlowed() {
        return slowed;
    }

    private ImageIcon image;
    private double pixelX, pixelY;
    private boolean slowed;
}
