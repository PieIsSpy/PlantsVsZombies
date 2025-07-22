import javax.swing.*;
import java.awt.*;

public class StartButton extends JButton {
    public StartButton() {
        startImg = new ImageIcon(getClass().getResource("/img/lawn/seedPackets/startImg.png"));
        /*
        width = startImg.getIconWidth();
        height = startImg.getIconHeight();

         */
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (startImg !=null) {
            g.drawImage(startImg.getImage(), 0,0, startImg.getIconWidth(), startImg.getIconHeight(), null);
        }
    }

    /*
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

     */

    private ImageIcon startImg;
    private int width;
    private int height;
}
