import javax.swing.*;
import java.awt.*;

/** This class displays the Result of the running level
 *  thread.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.0
 */
public class ResultPanel extends JPanel {
    /** This constructor gathers all necessary buttons and the width and height
     *  of the panel in order to properly format the Result Panel.
     *
     * @param width the width of the panel
     * @param height the height of the panel
     * @param retry the retry button of the panel
     * @param backLost the back button of the panel when the game is lost
     * @param next the next button of the panel
     * @param backWon the back button of the panel when the game is won
     */
    public ResultPanel (int width, int height, JButton retry, JButton backLost, JButton next, JButton backWon){
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;

        setLayout(null);
        init(retry, backLost, next, backWon);
    }

    /** This method paints the background and message dialogue of the result panel.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        if (messageShown == 0) { // if the level is lost
            g.setColor(Color.black);
            g.drawRect(0,0,PANEL_WIDTH,PANEL_HEIGHT);
            g.fillRect(0,0,PANEL_WIDTH,PANEL_HEIGHT);

            try {
                ImageIcon message = new ImageIcon(getClass().getResource("/img/result/levelLost.png"));
                g.drawImage(message.getImage(), 200, 50, (int) (message.getIconWidth() / 1.5), (int) (message.getIconHeight() / 1.5), null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if (messageShown > 0) { // if the level is won
            try {
                ImageIcon background = new ImageIcon(getClass().getResource("/img/lawn/lawnImg.png"));
                g.drawImage(background.getImage(), 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // find the appropriate image for each level clear
            try {
                ImageIcon message;

                if (messageShown == 1)
                    message = new ImageIcon(getClass().getResource("/img/result/levelWin1.png"));
                else if (messageShown == 2)
                    message = new ImageIcon(getClass().getResource("/img/result/levelWin2.png"));
                else
                    message = new ImageIcon(getClass().getResource("/img/result/levelWin3.png"));

                g.drawImage(message.getImage(), 150, 50, (int) (message.getIconWidth()*1.5), (int) (message.getIconHeight()*1.5), null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        repaint();
    }

    /** This method initializes the winning and losing panels of the result panel and
     *  adds them into a card layout.
     *
     * @param retry the retry button to be formatted
     * @param backLost the back button of a losing game to be formatted
     * @param next the next button to be formatted
     * @param backWon the back button of a winning game to be formatted
     */
    public void init(JButton retry, JButton backLost, JButton next, JButton backWon) {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        levelLost = constructButtonHolder(retry, backLost);
        levelWon = constructButtonHolder(next, backWon);

        mainPanel.add(levelLost, "level lost");
        mainPanel.add(levelWon, "level won");
        mainPanel.setBounds(0,0,PANEL_WIDTH,PANEL_HEIGHT);
        mainPanel.setOpaque(false);
        add(mainPanel);
    }

    /** This method formats the given buttons into their respective places.
     *
     * @param action the action button of the panel
     * @param back the back button of the panel
     * @return the JPanel constructed by the method
     */
    public JPanel constructButtonHolder(JButton action, JButton back) {
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);

        JPanel container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(PANEL_WIDTH, 100));
        container.setBackground(Color.red);
        container.setOpaque(false);

        // action button
        action.setBackground(Color.gray);
        action.setForeground(Color.GREEN);
        action.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,10));
        action.setPreferredSize(new Dimension(150,50));
        action.setFont(new Font("Sylfaen",Font.BOLD,30));
        action.setHorizontalTextPosition(JLabel.CENTER);
        action.setVerticalTextPosition(JLabel.CENTER);
        action.setFocusPainted(false);

        // back button
        back.setBackground(Color.gray);
        back.setForeground(Color.GREEN);
        back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,10));
        back.setPreferredSize(new Dimension(150,50));
        back.setFont(new Font("Sylfaen",Font.BOLD,30));
        back.setHorizontalTextPosition(JLabel.CENTER);
        back.setVerticalTextPosition(JLabel.CENTER);
        back.setFocusPainted(false);

        // add everything
        container.add(action);
        container.add(back);
        center.add(container, BorderLayout.SOUTH);

        return center;
    }

    /** This method determines which result message will be displayed by the panel.
     *  If the given number is 0, then the panel will show a losing message.
     *  If the given number is more than 0, then the panel will show a wining game.
     *  If the given number is less than 0, then no message will be shown.
     *
     * @param n the result message to be shown.
     */
    public void showMessage(int n) {
        messageShown = n;

        if (messageShown == 0)
            cardLayout.show(mainPanel, "level lost");
        else
            cardLayout.show(mainPanel, "level won");
    }

    /** the card layout of the panel*/
    private CardLayout cardLayout;
    /** the main container of the panels*/
    private JPanel mainPanel;
    /** the winning message panel*/
    private JPanel levelWon;
    /** the losing message panel*/
    private JPanel levelLost;
    /** determines the message shown by the panel*/
    private int messageShown;
    /** the panel width*/
    private final int PANEL_WIDTH;
    /** the panel height*/
    private final int PANEL_HEIGHT;
}