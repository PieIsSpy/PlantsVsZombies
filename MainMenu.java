import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu() {
        try {
            bgImg = new ImageIcon(getClass().getResource("/img/menuImg.png"));
        }
        catch (Exception e) {
            System.out.println("menuImg.png cannot be loaded");
        }

        try {
            logoImg = new ImageIcon(getClass().getResource("/img/logoImg.png"));
        }
        catch (Exception e) {
            System.out.println("logoImg.png cannot be loaded");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImg != null) {
            g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }

        if (logoImg != null) {
            g.drawImage(logoImg.getImage(), (int)(this.getWidth()/17), (int)(this.getHeight()/12), (int)(logoImg.getIconWidth() / 1.5), (int)(logoImg.getIconHeight() / 1.5),null);
        }
    }

    private ImageIcon bgImg;
    private ImageIcon logoImg;
}
