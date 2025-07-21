import javax.swing.*;
import java.awt.*;

public class SeedPacket extends JPanel {
    public SeedPacket(String name, ImageIcon image, int x, int y) {
        this.name = name;
        image_corner = new Point(x,y);
        setPreferredSize(new Dimension(10,10));
        setBounds(x,y,10,10);
        setBackground(Color.red);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this,g,image_corner.x,image_corner.y);
    }

    public void setPreviousPoint(Point p) {
        previous_corner = p;
    }

    public String getName() {
        return name;
    }

    public Point getImage_corner() {
        return image_corner;
    }

    public Point getPreviousPoint() {
        return previous_corner;
    }

    private String name;
    private ImageIcon image;
    private Point image_corner;
    private Point previous_corner;
}