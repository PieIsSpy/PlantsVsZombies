import javax.swing.*;
import java.awt.*;

/** This class represents a draggable object to be used by the
 *  LawnPanel class.
 *
 *  @author Karl Deejay Omandac
 *  @author Rachel Angeline Alba
 *  @version 1.2
 */
public class Draggable extends JPanel {
    /** This constructor initializes the name, image icon and
     *  starting position of a draggable object
     *
     * @param n the name of the object represented
     * @param i the image of the draggable object
     * @param x the x position of the draggable object
     * @param y the y position of the draggable object
     */
    public Draggable(String n, ImageIcon i, int x, int y) {
        NAME = n;
        imageSprite = i;
        image_corner = new Point(x,y);
        ORIGINAL_POINT = new Point(x,y);
        setOpaque(false);
    }

    /** This method is responsible for rendering the image
     *  and status of a draggable object.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics filter = g;

        // if a draggable is in cooldown, make the filter semi-transparent
        if (filterOpacity)
            filter.setColor(new Color(59,59,59, 200));
        else
            filter.setColor(new Color(59,59,59, 0));

        imageSprite.paintIcon(this,g,image_corner.x,image_corner.y);

        // cover the image with the filter
        filter.drawRoundRect(image_corner.x, image_corner.y, imageSprite.getIconWidth(), imageSprite.getIconHeight(), 4,4);
        filter.fillRoundRect(image_corner.x, image_corner.y, imageSprite.getIconWidth(), imageSprite.getIconHeight(), 4,4);
    }

    /** This method sets the cover filter toggle of the image.
     *
     * @param b the cover filter status of the image
     */
    public void setFilterOpacity(boolean b) {
        filterOpacity = b;
    }

    /** This method sets a point as its previous point.
     *
     * @param p the previous point of the draggable
     */
    public void setPreviousPoint(Point p) {
        previous_corner = p;
    }

    /** This method returns the previous point of the draggable object.
     *
     * @return the previous point of the draggable object
     */
    public Point getPreviousCorner() {
        return previous_corner;
    }

    /** This method returns the current point of the draggable object.
     *
     * @return the image corner of the object
     */
    public Point getImageCorner() {
        return image_corner;
    }

    /** This method returns the original point of reference
     *  of the draggable object when it was instantiated.
     *
     * @return the original point of reference of the draggable object
     */
    public Point getOriginalCorner() {
        return ORIGINAL_POINT;
    }

    /** This method returns the image sprite of the draggable object.
     *
     * @return the image sprite of the draggable object.
     */
    public ImageIcon getImageSprite() {
        return imageSprite;
    }

    /** This method returns the name of the object represented by the
     *  draggable object
     *
     * @return the name of the object represented by the
     * draggable object
     */
    @Override
    public String getName() {
        return NAME;
    }

    /** the name of the object represented*/
    private final String NAME;
    /** the sprite image of the draggable object*/
    private ImageIcon imageSprite;
    /** the original point of reference during instantiation*/
    private final Point ORIGINAL_POINT;
    /** the current point of reference*/
    private Point image_corner;
    /** the previous point of reference*/
    private Point previous_corner;
    /** determines whether the filter is semi-opaque or transparent*/
    private boolean filterOpacity;
}