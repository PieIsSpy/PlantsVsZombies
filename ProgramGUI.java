import javax.swing.*;
import java.awt.*;

public class ProgramGUI extends JFrame {
    public ProgramGUI() {

        super("Plants vs Zombies");
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        init();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 


    public void init()
    {
        LawnPanel lawn = new LawnPanel();
        add(lawn);
    }


    private static final int WIDTH = 1052, HEIGHT = 594;

    
}
