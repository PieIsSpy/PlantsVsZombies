import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(int width, int height, JButton start, JButton quit) {
        // get all images
        try {
            bgImg = new ImageIcon(getClass().getResource("/img/menuImg.png"));
        }
        catch (Exception e) {
            System.out.println("menuImg.png cannot be loaded");
        }

        try {
            logoImg = new ImageIcon(getClass().getResource("/img/logoImg.png"));
        }
        catch (Exception e) {
            System.out.println("logoImg.png cannot be loaded");
        }

        setLayout(new BorderLayout());
        addComponents(width, height, start, quit);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImg != null) {
            g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }

        if (logoImg != null) {
            g.drawImage(logoImg.getImage(), (int)(this.getWidth()/17), (int)(this.getHeight()/12), (int)(logoImg.getIconWidth() / 1.5), (int)(logoImg.getIconHeight() / 1.5),null);
        }
    }

    public void addComponents(int width, int height, JButton start, JButton quit) {
        // Left side borders
        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(new Color(255,0,0,200));
        left.setPreferredSize(new Dimension(width/2, height));
        left.setOpaque(false);

        JPanel upperLeft = new JPanel(new BorderLayout());
        upperLeft.setBackground(new Color(0,255,0,200));
        upperLeft.setPreferredSize(new Dimension(width/2, 300));
        upperLeft.setOpaque(false);

        JPanel innerLeft = new JPanel(new BorderLayout());
        innerLeft.setBackground(new Color(0,100,255,200));
        innerLeft.setPreferredSize(new Dimension(width/2,150));
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
        add(left, BorderLayout.EAST);

        // right side boarders
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(new Color(250, 0, 0, 180));
        right.setPreferredSize(new Dimension(width/2, height));
        right.setOpaque(false);

        JPanel bottomRight = new JPanel(new BorderLayout());
        bottomRight.setBackground(new Color(250,0,0,180));
        bottomRight.setPreferredSize(new Dimension(width/2, 225));
        bottomRight.setOpaque(false);

        JPanel innerRight = new JPanel(new BorderLayout());
        innerRight.setBackground(new Color(0,0,250,180));
        innerRight.setPreferredSize(new Dimension(125, height/4));
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
        add(right,BorderLayout.WEST);
    }

    private ImageIcon bgImg;
    private ImageIcon logoImg;
}
