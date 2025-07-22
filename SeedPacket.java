import javax.swing.*;
import java.awt.*;

public class SeedPacket extends JPanel {
    public SeedPacket(String n, ImageIcon i, int x, int y) {
        NAME = n;
        IMAGE = i;
        WIDTH = IMAGE.getIconWidth();
        HEIGHT = IMAGE.getIconHeight();
        image_corner = new Point(x,y);
        ORIGINAL_POINT = new Point(x,y);
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        IMAGE.paintIcon(this,g,image_corner.x,image_corner.y);
    }

    public void setPreviousPoint(Point p) {
        previous_corner = p;
    }

    public Point getPreviousCorner() {
        return previous_corner;
    }

    public void setImageCorner(Point p) {
        image_corner = p;
        repaint();
    }

    public Point getImageCorner() {
        return image_corner;
    }

    public Point getOriginalCorner() {
        return ORIGINAL_POINT;
    }

    public ImageIcon getImage() {
        return IMAGE;
    }

    private final String NAME;
    private final ImageIcon IMAGE;
    private final Point ORIGINAL_POINT;
    private final int WIDTH;
    private final int HEIGHT;
    private Point image_corner;
    private Point previous_corner;
}