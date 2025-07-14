import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu() {
        bgImg = new ImageIcon(getClass().getResource("/img/MainMenuImage.png"));
        logoImg = new ImageIcon(getClass().getResource("/img/GameLogo.png"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImg != null && logoImg != null) {
            g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            g.drawImage(logoImg.getImage(), (int)(this.getWidth()/2.7), 0, (int)(logoImg.getIconWidth() / 1.5), (int)(logoImg.getIconHeight() / 1.5),null);
        }
    }

    private ImageIcon bgImg;
    private ImageIcon logoImg;
}
