import javax.swing.*;
import java.awt.*;

public class SeedPacket extends JPanel {
    public SeedPacket(String n, ImageIcon i, int x, int y) {
        name = n;
        image = i;
        System.out.println(image.getIconWidth());
        System.out.println(image.getIconHeight());
        image_corner = new Point(x,y);
        setPreferredSize(new Dimension(image.getIconWidth(),image.getIconHeight()));
        setBounds(x,y,1000,1000);
        //setBackground(Color.red);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this,g,0,0);
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