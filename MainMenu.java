import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu() {
        bgImg = new ImageIcon(getClass().getResource("/MainMenuImage.png"));
        logoImg = new ImageIcon(getClass().getResource("/GameLogo.png"));
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImg != null && logoImg != null) {
            g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            g.drawImage(logoImg.getImage(), (int)(this.getWidth()/3.5), 0, null);
        }
    }

    private ImageIcon bgImg;
    private ImageIcon logoImg;
}
