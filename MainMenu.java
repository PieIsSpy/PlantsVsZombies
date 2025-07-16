import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu() {
        try {
            bgImg = new ImageIcon(getClass().getResource("/img/menuImg.png"));
            logoImg = new ImageIcon(getClass().getResource("/img/logoImg.png"));
            //startImg = new ImageIcon(getClass().getResource("/img/startImg.png"));
        }
        catch (Exception e) {
            System.out.println("Image cannot be loaded");
        }

        /*
        startBtn = new JButton("Start");
        startBtn.setBounds((int)(this.getWidth()/2), (int)(this.getHeight()/5), (int)(startImg.getIconWidth()/1.05), (int)(startImg.getIconHeight()/1.05));
        this.add(startBtn);

        init();

         */
    }

    /*
    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0,5)));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImg != null && logoImg != null) {
            g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            g.drawImage(logoImg.getImage(), (int)(this.getWidth()/17), (int)(this.getHeight()/12), (int)(logoImg.getIconWidth() / 1.5), (int)(logoImg.getIconHeight() / 1.5),null);
            //g.drawImage(startImg.getImage(), (int)(this.getWidth()/2), (int)(this.getHeight()/5), (int)(startImg.getIconWidth()/1.05), (int)(startImg.getIconHeight()/1.05), null);
        }
    }

    /*
    public JButton getStartBtn() {
        return startBtn;
    }

     */

    private ImageIcon bgImg;
    private ImageIcon logoImg;
    /*
    private ImageIcon startImg;
    private JButton startBtn;

     */
}
