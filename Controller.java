import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    public Controller(MainModel m, ProgramGUI v) {
        model = m;
        view = v;
    }

    public void updateView() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private MainModel model;
    private ProgramGUI view;
}
