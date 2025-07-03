import javax.swing.*;
import java.awt.*;

public class ProgramGUI extends JFrame {
    public ProgramGUI() {
        super("Plants vs Zombies");
        setLayout(new BorderLayout());
        setSize(900, 650);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
