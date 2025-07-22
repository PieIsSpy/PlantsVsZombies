import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SeedPacket extends JPanel {
    public SeedPacket(String n, ImageIcon i, int x, int y) {
        NAME = n;
        imageSprite = i;
        image_corner = new Point(x,y);
        ORIGINAL_POINT = new Point(x,y);
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics filter = (Graphics) g;

        if (filterOpacity)
            filter.setColor(new Color(59,59,59, 200));
        else
            filter.setColor(new Color(59,59,59, 0));

        imageSprite.paintIcon(this,g,image_corner.x,image_corner.y);

        filter.drawRoundRect(image_corner.x, image_corner.y, imageSprite.getIconWidth(), imageSprite.getIconHeight(), 4,4);
        filter.fillRoundRect(image_corner.x, image_corner.y, imageSprite.getIconWidth(), imageSprite.getIconHeight(), 4,4);
    }

    public void setFilterOpacity(boolean b) {
        filterOpacity = b;
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

    public ImageIcon getImageSprite() {
        return imageSprite;
    }

    @Override
    public String getName() {
        return NAME;
    }

    private final String NAME;
    private ImageIcon imageSprite;
    private final Point ORIGINAL_POINT;
    private Point image_corner;
    private Point previous_corner;
    private boolean filterOpacity;
}