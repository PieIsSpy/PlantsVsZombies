import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    public View() {

        super("Plants vs Zombies");
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        mainMenu();
    }

    public void init()
    {
        panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        add(panelSouth, BorderLayout.SOUTH);

        start = new JButton("Start");
        quit = new JButton("Quit");

        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");
        forfeit = new JButton("Forfeit");

        menu = new MainMenu();
    }

    public void mainMenu() {
        System.out.println("Current GUI: Main Menu");
        panelSouth.removeAll();

        add(menu, BorderLayout.CENTER);
        menu.setLayout(new FlowLayout());
        panelSouth.add(start);
        panelSouth.add(quit);
        add(panelSouth, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    public void levelSelect() {
        System.out.println("Current GUI: Level Select");

        panelSouth.removeAll();

        panelSouth.add(level1);
        panelSouth.add(level2);
        panelSouth.add(level3);

        panelSouth.add(forfeit);

        add(panelSouth, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    public void setActionListener(ActionListener listener) {

        start.addActionListener(listener);
        quit.addActionListener(listener);
        level1.addActionListener(listener);
        level2.addActionListener(listener);
        level3.addActionListener(listener);
        forfeit.addActionListener(listener);
    }

    private JButton start;
    private JButton quit;
    private JButton level1;
    private JButton level2;
    private JButton level3;
    private JButton forfeit;
    private JPanel panelSouth;
    private MainMenu menu;
    private static final int WIDTH = 800, HEIGHT = 600;
}
