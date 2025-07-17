import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class View extends JFrame {
    public View() {

        super("Plants vs Zombies");
        setLayout(new BorderLayout());
        //setLayout(null);
        setSize(WIDTH, HEIGHT);

        init();
        //mainMenu();

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init()
    {
        lawn = new LawnPanel();
        add(lawn);
        /*
        panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        add(panelSouth, BorderLayout.SOUTH);
        */
        start = new JButton("Start");
        quit = new JButton("Quit");

        /*
        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");
        forfeit = new JButton("Forfeit");
         */
    }

    /*
    public void mainMenu() {
        System.out.println("Current GUI: Main Menu");

        // Main menu background acting as center border
        MainMenu menu = new MainMenu();
        menu.setLayout(new BorderLayout());
        add(menu);

        // Left side borders
        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(new Color(255,0,0,200));
        left.setPreferredSize(new Dimension(getWidth()/2, getHeight()));
        left.setOpaque(false);

        JPanel upperLeft = new JPanel(new BorderLayout());
        upperLeft.setBackground(new Color(0,255,0,200));
        upperLeft.setPreferredSize(new Dimension(getWidth()/2, 300));
        upperLeft.setOpaque(false);

        JPanel innerLeft = new JPanel(new BorderLayout());
        innerLeft.setBackground(new Color(0,100,255,200));
        innerLeft.setPreferredSize(new Dimension(getWidth()/2,150));
        innerLeft.setOpaque(false);

        JPanel buttonContainerL = new JPanel(new BorderLayout());
        buttonContainerL.setBackground(new Color(10,10,10,180));
        buttonContainerL.setPreferredSize(new Dimension(320,innerLeft.getHeight()));
        buttonContainerL.setOpaque(false);

        ImageIcon startImg;
        try {
            startImg = new ImageIcon(getClass().getResource("/img/startImg.png"));
            start.setIcon(startImg);
        }
        catch (NullPointerException e) {
            System.out.println("startImg.png cannot be loaded");
        }

        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setFocusPainted(false);

        // add everything from left side
        buttonContainerL.add(start,BorderLayout.CENTER);
        innerLeft.add(buttonContainerL, BorderLayout.WEST);
        upperLeft.add(innerLeft, BorderLayout.SOUTH);
        left.add(upperLeft,BorderLayout.NORTH);
        menu.add(left, BorderLayout.EAST);

        // right side boarders
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(new Color(250, 0, 0, 180));
        right.setPreferredSize(new Dimension(getWidth()/2, getHeight()));
        right.setOpaque(false);

        JPanel bottomRight = new JPanel(new BorderLayout());
        bottomRight.setBackground(new Color(250,0,0,180));
        bottomRight.setPreferredSize(new Dimension(getWidth()/2, 225));
        bottomRight.setOpaque(false);

        JPanel innerRight = new JPanel(new BorderLayout());
        innerRight.setBackground(new Color(0,0,250,180));
        innerRight.setPreferredSize(new Dimension(125, getHeight()/4));
        innerRight.setOpaque(false);

        JPanel buttonContainerR = new JPanel(new BorderLayout());
        buttonContainerR.setBackground(new Color(0,250,0,180));
        buttonContainerR.setPreferredSize(new Dimension(70,100));
        buttonContainerR.setOpaque(false);

        quit.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);

        // add everything from the right side
        buttonContainerR.add(quit,BorderLayout.CENTER);
        innerRight.add(buttonContainerR, BorderLayout.NORTH);
        bottomRight.add(innerRight, BorderLayout.WEST);
        right.add(bottomRight,BorderLayout.SOUTH);
        menu.add(right,BorderLayout.WEST);

        revalidate();
        repaint();
    }
    */

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
        quit.addActionListener(listener);/*
        level1.addActionListener(listener);
        level2.addActionListener(listener);
        level3.addActionListener(listener);
        forfeit.addActionListener(listener);

         */
    }

    public void setMouseListener(MouseListener l)
    {
        lawn.addMouseListener(l);
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
    private LawnPanel lawn;
    private static final int WIDTH = 800, HEIGHT = 600;
}
