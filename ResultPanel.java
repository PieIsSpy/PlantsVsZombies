import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    public ResultPanel (int width, int height, JButton retry, JButton backLost, JButton next, JButton backWon){
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;

        setLayout(null);
        init(retry, backLost, next, backWon);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (messageShown == 0) {
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
        else if (messageShown > 0) {
            try {
                ImageIcon background = new ImageIcon(getClass().getResource("/img/lawn/lawnImg.png"));
                g.drawImage(background.getImage(), 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

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

    public void showMessage(int n) {
        messageShown = n;

        if (messageShown == 0)
            cardLayout.show(mainPanel, "level lost");
        else
            cardLayout.show(mainPanel, "level won");
    }

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel levelWon;
    private JPanel levelLost;
    private int messageShown;
    /// the panel width
    private final int PANEL_WIDTH;
    /// the panel height
    private final int PANEL_HEIGHT;
}