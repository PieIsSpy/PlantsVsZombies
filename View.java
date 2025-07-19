import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class View extends JFrame {
    public View() {

        super("Plants vs Zombies");
        setLayout(new BorderLayout());
        //setLayout(null);
        setSize(WIDTH, HEIGHT);

        //init();
        //mainMenu();
        levelGUI();

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init()
    {
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

    public void levelGUI() {
        // lawn gui acting as center border
        lawn = new LawnPanel(WIDTH, HEIGHT);
        add(lawn, BorderLayout.CENTER);
        /*
        lawn.setLayout(new BorderLayout());

        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(getWidth()/5, getHeight()));
        left.setBackground(new Color(255,255,100,150));
        left.setOpaque(false);

        JLabel sunCount = new JLabel();
        //sunCount.setText();

        ImageIcon source = new ImageIcon(getClass().getResource("/img/seedSlotImg.png"));
        Image scaled = source.getImage().getScaledInstance((int)(source.getIconWidth()*0.8),(int)(source.getIconHeight()*0.8), Image.SCALE_SMOOTH);
        ImageIcon seedSlot = new ImageIcon(scaled);
        JLabel container = new JLabel();
        container.setIcon(seedSlot);
        container.setHorizontalAlignment(JLabel.CENTER);

        // add components to jframe
        left.add(container, BorderLayout.CENTER);
        lawn.add(left, BorderLayout.WEST);
        add(lawn, BorderLayout.CENTER);

         */

        // repaint and revalidate
        validate();
        repaint();
    }

    public void mainMenu() {
        MenuPanel menu = new MenuPanel(WIDTH,HEIGHT,start,quit);
        add(menu, BorderLayout.CENTER);

        validate();
        repaint();
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
        quit.addActionListener(listener);/*
        level1.addActionListener(listener);
        level2.addActionListener(listener);
        level3.addActionListener(listener);
        forfeit.addActionListener(listener);

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
    private LawnPanel lawn;
    private static final int WIDTH = 800, HEIGHT = 600;
}
