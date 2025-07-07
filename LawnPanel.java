import java.awt.Graphics;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LawnPanel extends JPanel {
    public LawnPanel()
    {
        File file = new File("lawnImage.png");
        System.out.println("can execute: " + file.canExecute());
        System.out.println("location: " + file.getAbsolutePath());
        System.out.println("does file exist: " + file.exists());

        lawnImg = new ImageIcon("lawnImage.png");
        System.out.println("image width: " + lawnImg.getImage().getWidth(null));
        System.out.println("image height: " + lawnImg.getImage().getHeight(null));

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

    public static void main(String[] args)
    {
        LawnPanel lawn = new LawnPanel();

    }
}
