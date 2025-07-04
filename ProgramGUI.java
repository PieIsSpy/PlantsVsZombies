import javax.swing.*;
import java.awt.*;

public class ProgramGUI extends JFrame {
    public ProgramGUI() {

        super("Plants vs Zombies");
        setLayout(null);
        setSize(1052, 594);
        init();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 


    public void init()
    {
        //BACKGROUND IMAGE
        background = new JLabel(new ImageIcon("lawnImage.png"), JLabel.CENTER);
        background.setBounds(0, 0, 1052, 594);
        add(background);

    }

    private Image lawn;
    private JLabel background;
    //use grid layout for the center 
}
