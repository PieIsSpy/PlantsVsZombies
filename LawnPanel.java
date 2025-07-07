import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LawnPanel extends JPanel {
    public LawnPanel()
    {
        lawnImg = new ImageIcon("lawnImage.png");
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(lawnImg != null)
        {
             g.drawImage(lawnImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }
        else
        {
            System.out.println("Failed to load image!");
        }
    }

    private ImageIcon lawnImg;
}
