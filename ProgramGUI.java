import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class ProgramGUI extends JFrame {
    public ProgramGUI() {

        super("Plants vs Zombies");
        setLayout(new BorderLayout());
        //setLayout(null);
        setSize(WIDTH, HEIGHT);

        init();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //mainMenu();
        //init();
    }

    public void init()
    {
        lawn = new LawnPanel();
        add(lawn);

        /* 
        JLabel measure = new JLabel("HI");
        measure.setSize(712, 460);
        measure.setBackground(Color.GREEN);
        measure.setOpaque(true);
        measure.setLocation(253,72);

        lawn.add(measure);
        */ 
        
        
        /* 
        panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        add(panelSouth, BorderLayout.SOUTH);

        start = new JButton("Start");
        quit = new JButton("Quit");

        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");
        forfeit = new JButton("Forfeit");
        */ 
    }

    /* 
    public void mainMenu() {
        System.out.println("Current GUI: Main Menu");
        //panelSouth.removeAll();
        /*
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());

        

        MainMenu menu = new MainMenu();
        add(menu, BorderLayout.CENTER);


        panelSouth.add(start);
        panelSouth.add(quit);

        add(panelSouth, BorderLayout.SOUTH);

        /*
        start = new StartButton();
        start.setBounds(WIDTH/2, HEIGHT, start.getWidth(), start.getHeight());
        //start.setLocation(WIDTH/2, HEIGHT);
        add(start);


        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        //panelSouth.setOpaque(true);
        start = new StartButton();
        //start = new JButton("start");
        panelSouth.add(start);
        add(panelSouth);

         
        
        panelSouth.revalidate();
        panelSouth.repaint();

         
        revalidate();
        repaint();
    }
    */

    public void levelSelect() {
        System.out.println("Current GUI: Level Select");
        panelSouth.removeAll();
        //panelSouth.removeAll();

        /*
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());

         */

        panelSouth.add(level1);
        panelSouth.add(level2);
        panelSouth.add(level3);

        panelSouth.add(forfeit);

        add(panelSouth, BorderLayout.SOUTH);

        /*
        panelSouth.revalidate();
        panelSouth.repaint();

         */
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
    private JPanel panelSouth;
    private JButton forfeit;
    private LawnPanel lawn;
    private static final int WIDTH = 1052, HEIGHT = 594;
}
