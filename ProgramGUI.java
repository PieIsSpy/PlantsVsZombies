import javax.swing.*;
import java.awt.*;

public class ProgramGUI extends JFrame {
    public ProgramGUI() {

        super("Plants vs Zombies");
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        //init();
        mainMenu();
        setVisible(true);
        setResizable(false);
        startImg = new ImageIcon(getClass().getResource("/startImg.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init()
    {
        LawnPanel lawn = new LawnPanel();
        add(lawn);
    }

    public void mainMenu() {
        MainMenu menu = new MainMenu();
        add(menu);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BorderLayout());
        start = new JButton("Start");
        start.setIcon(startImg);
        //System.out.println(startImg.getIconHeight());

        /*
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
         */
        panelSouth.add(start);
        add(panelSouth, BorderLayout.SOUTH);
        validate();
    }

    private JButton start;
    private ImageIcon startImg;
    private static final int WIDTH = 1052, HEIGHT = 594;
}
