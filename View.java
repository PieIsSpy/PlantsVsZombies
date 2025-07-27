import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** The class View represents the main GUI handler of the game.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.3
 */
public class View extends JFrame {
    /** This constructor initializes the basic attributes of the main
     *  JFrame handler of the GUI.
     *
     */
    public View() {
        super("Plants vs Zombies");
        setLayout(null);
        setSize(WIDTH, HEIGHT);

        init();

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** This method initializes the buttons and panels
     *  of the GUI and places them into the JFrame.
     *
     */
    public void init()
    {
        // buttons
        start = new JButton("Start");
        quit = new JButton("Quit");
        forfeit = new JButton("Forfeit");
        retry = new JButton("Retry");
        backLost = new JButton("Back");
        backWon = new JButton("Back");
        next = new JButton("Next");

        // panels
        cardLayout = new CardLayout();
        currentPanel = new JPanel(cardLayout);
        menu = new MenuPanel(WIDTH, HEIGHT, start, quit);
        lawn = new LawnPanel(WIDTH, HEIGHT, forfeit);
        result = new ResultPanel(WIDTH, HEIGHT, retry, backLost, next, backWon);

        // add panels
        currentPanel.add(menu, "menu");
        currentPanel.add(lawn, "lawn");
        currentPanel.add(result, "result");

        currentPanel.setBounds(0,0,getWidth(),getHeight());
        add(currentPanel);
    }

    /** This method changes the current panel of the view.
     *
     * @param panel the name of the panel to go to
     */
    public void changePanel(String panel) {
        if (panel.equalsIgnoreCase("menu")) {
            System.out.println();
            System.out.println("Current GUI: Main Menu");
            cardLayout.show(currentPanel, "menu");
        }
        else if (panel.equalsIgnoreCase("lawn")) {
            System.out.println();
            System.out.println("Current GUI: Level Select");
            cardLayout.show(currentPanel, "lawn");
        }
        else if (panel.equalsIgnoreCase("result")) {
            cardLayout.show(currentPanel, "result");
        }
    }

    /** This method clears all rendered images
     *  of the Lawn Panel.
     *
     */
    public void clearLawn() {
        lawn.clearImages();
    }

    /** This method connects all the view's buttons to the controller
     *  via the listener.
     *
     * @param listener the listener observing the button actions
     */
    public void setActionListener(ActionListener listener) {
        start.addActionListener(listener);
        quit.addActionListener(listener);
        forfeit.addActionListener(listener);
        backWon.addActionListener(listener);
        backLost.addActionListener(listener);
        retry.addActionListener(listener);
        next.addActionListener(listener);
    }

    /** This method listens for mouse actions inside the Lawn Panel
     *  and connects them to the controller via listener.
     *
     * @param listener the listener observing for mouse actions
     */
    public void setMouseListener(MouseListener listener)
    {
        lawn.addMouseListener(listener);
    }

    /** This method listens for mouse movements inside the Lawn Panel
     *  and connects them to the controller via listener.
     *
     * @param listener the listener observing for mouse movements
     */
    public void setMouseMotionListener(MouseMotionListener listener) {
        lawn.addMouseMotionListener(listener);
    }

    /** This method returns the Lawn Panel of the View.
     *
     * @return the Lawn Panel used to display lawn gameplay
     */
    public LawnPanel getLawn()
    {
        return lawn;
    }

    /** This method returns the Result panel used to display the
     *  game result after a level is done.
     *
     * @return the Result Panel of the view
     */
    public ResultPanel getResult() {
        return result;
    }

    /** the start button used by the View */
    private JButton start;
    /** the quit button used by the View */
    private JButton quit;
    /** the forfeit button used by the View */
    private JButton forfeit;
    /** the back button used by the level lost GUI of the view */
    private JButton backLost;
    /** the back button used by the level won GUI of the view */
    private JButton backWon;
    /** the retry button used by the View */
    private JButton retry;
    /** the next button used by the View */
    private JButton next;
    /** the list of JPanels used by the View */
    private CardLayout cardLayout;
    /** the current JPanel displayed in the View */
    private JPanel currentPanel;
    /** the Main Menu panel of the View */
    private MenuPanel menu;
    /** the Lawn panel of the View */
    private LawnPanel lawn;
    /** the Game Over panel of the View */
    private ResultPanel result;
    /** the dimensions of the main JFrame */
    private static final int WIDTH = 800, HEIGHT = 600;
}
