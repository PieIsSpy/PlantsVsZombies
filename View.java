import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class View extends JFrame {
    public View() {

        super("Plants vs Zombies");
        //setLayout(new BorderLayout());
        setLayout(null);
        setSize(WIDTH, HEIGHT);

        init();

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init()
    {
        // buttons
        start = new JButton("Start");
        quit = new JButton("Quit");
        forfeit = new JButton("Forfeit");

        // panels
        cardLayout = new CardLayout();
        currentPanel = new JPanel(cardLayout);
        menu = new MenuPanel(WIDTH, HEIGHT, start, quit);
        lawn = new LawnPanel(WIDTH, HEIGHT, forfeit);

        // add panels
        currentPanel.add(menu, "menu");
        currentPanel.add(lawn, "lawn");
        currentPanel.setBounds(0,0,getWidth(),getHeight());
        add(currentPanel);

        /*
        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");
         */


    }

    public void changePanel(String panel) {
        if (panel.equalsIgnoreCase("menu")) {
            System.out.println("Current GUI: Main Menu");
            cardLayout.show(currentPanel, "menu");
        }
        else if (panel.equalsIgnoreCase("lawn")) {
            System.out.println("Current GUI: Level Select");
            cardLayout.show(currentPanel, "lawn");
        }
    }

    public void clearLawn() {
        lawn.clearImages();
    }

    public void levelSelect() {
        /*
        System.out.println("Current GUI: Level Select");
        panelSouth.removeAll();

        panelSouth.add(level1);
        panelSouth.add(level2);
        panelSouth.add(level3);

        panelSouth.add(forfeit);

        add(panelSouth, BorderLayout.SOUTH);

        revalidate();
        repaint();
         */
    }

    public void setActionListener(ActionListener listener) {
        start.addActionListener(listener);
        quit.addActionListener(listener);
        forfeit.addActionListener(listener);
        /*
        level1.addActionListener(listener);
        level2.addActionListener(listener);
        level3.addActionListener(listener);
         */
    }

    public void setMouseListener(MouseListener listener)
    {
        lawn.addMouseListener(listener);
    }

    public void setMouseMotionListener(MouseMotionListener listener) {
        lawn.addMouseMotionListener(listener);
    }

    public LawnPanel getLawn()
    {
        return lawn;
    }

    private JButton start;
    private JButton quit;
    private JButton level1;
    private JButton level2;
    private JButton level3;
    private JButton forfeit;

    private CardLayout cardLayout;
    private JPanel currentPanel;
    private MenuPanel menu;
    private LawnPanel lawn;
    private static final int WIDTH = 800, HEIGHT = 600;
}
