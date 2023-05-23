package cosc202.andie;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming
 * in and out.
 * </p>
 * 
 * 
 * <p>
 * This class also implements {@link MouseInputListener} to allow for drawing
 * and retrives/updates the desired x and y co-ordinates.
 * </p>
 * 
 * 
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills & Stella Yan
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseInputListener { // m

    private boolean isDrawingShape = false;
    private boolean isDragging = false;
    private EditableImage image;

    public static Shape preview;
    private boolean isRedCircleSelected = false;
    private boolean shouldDrawBlueRectangle = true;

    private Color currentColor = Color.RED; // Default color is set to red

    public enum ShapeType {
        CIRCLE, RECTANGLE, LINE
    }

    private boolean isShapeFilled = false;

    public static boolean selection = true;
    // put this in the DrawingAction class menu?

    public static ShapeType currentShapeType;
    public static Shape currentShape;

    public static Point first, second;
    public static Point origin;
    public static int selWidth, selHeight;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is
     * zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally
     * as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     * 
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * 
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 10) {
            zoomPercent = 10;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);

            if (shouldDrawBlueRectangle && origin != null) {
                Stroke dashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,
                        new float[] { 5.0f, 5.0f }, 0.0f);

                // Set the stroke of the graphics object to the dashed stroke
                g2.setStroke(dashedStroke);
                g2.setColor(Color.BLACK);
                g2.drawRect(origin.x, origin.y, selWidth, selHeight);
            } else if (preview != null && isDragging && isDrawingShape && isShapeFilled) {
                g2.setColor(currentColor);
                g2.fill(preview);

            }

            else if (preview != null && isDragging && isDrawingShape) {
                g2.setColor(currentColor);
                g2.draw(preview);

            }
            g2.dispose();
        }
    }

    /**
     * <p>
     * Mouse event handler for mouse clicks.
     * </p>
     * 
     * @param MouseEvent e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * <p>
     * Mouse event handler for mouse pressed.
     * </p>
     * 
     * <p>
     * sets the first point of the shape to be drawn
     * </p>
     * 
     * @param MouseEvent e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (isRedCircleSelected == false) {
            shouldDrawBlueRectangle = true;
        }

        Point point = e.getPoint();

        first = new Point((int) (point.getX() / scale), (int) (point.getY() / scale));

        if (currentShapeType != null) {

            switch (currentShapeType) {
                case CIRCLE:
                    currentShape = new Ellipse2D.Double(first.x, first.y, 0, 0);
                    ImagePanel.selection = false;
                    isRedCircleSelected = true;
                    shouldDrawBlueRectangle = false;
                    isDrawingShape = true;
                    break;

                case RECTANGLE:
                    currentShape = new Rectangle2D.Double(first.x, first.y, 0, 0);
                    isRedCircleSelected = true;
                    shouldDrawBlueRectangle = false;
                    ImagePanel.selection = false;
                    isDrawingShape = true;
                    break;

                case LINE:
                    currentShape = new Line2D.Double(first.x, first.y, first.x, first.y);
                    isRedCircleSelected = true;
                    shouldDrawBlueRectangle = false;
                    ImagePanel.selection = false;
                    isDrawingShape = true;
                    break;

                default:
                    break;

            }
        }
    }

    /**
     * <p>
     * Mouse event handler for mouse released.
     * </p>
     * 
     * <p>
     * sets the second point of the shape to be drawn
     * </p>
     * 
     * @param MouseEvent e
     */
    @Override

    public void mouseReleased(MouseEvent e) {

        Point point = e.getPoint();

        second = new Point((int) (point.getX() / scale), (int) (point.getY() / scale));

        if (currentShapeType != null) {
            switch (currentShapeType) {
                case CIRCLE:
                    double radiusX = Math.abs(second.x - first.x);
                    double radiusY = Math.abs(second.y - first.y);
                    double radius = Math.max(radiusX, radiusY);
                    double centerX = first.x + (second.x - first.x) / 2.0;
                    double centerY = first.y + (second.y - first.y) / 2.0;
                    double x = Math.max(0, centerX - radius);
                    double y = Math.max(0, centerY - radius);
                    double maxX = image.getCurrentImage().getWidth() - 1;
                    double maxY = image.getCurrentImage().getHeight() - 1;
                    double clippedWidth = Math.min(maxX - x, radius * 2);
                    double clippedHeight = Math.min(maxY - y, radius * 2);
                    currentShape = new Ellipse2D.Double(x, y, clippedWidth, clippedHeight);

                    image.apply(new DrawOperation(currentShape, isShapeFilled, currentColor));
                    ImagePanel.selection = false;
                    isRedCircleSelected = false;
                    shouldDrawBlueRectangle = false;
                    isDrawingShape = false;

                    break;

                case RECTANGLE:
                    int width = Math.abs(first.x - second.x);
                    int height = Math.abs(first.y - second.y);
                    int xx = Math.min(first.x, second.x);
                    int yx = Math.min(first.y, second.y);
                    int maxXx = image.getCurrentImage().getWidth() - 1;
                    int maxYx = image.getCurrentImage().getHeight() - 1;
                    int clippedWidthx = Math.min(maxXx - xx, width);
                    int clippedHeightx = Math.min(maxYx - yx, height);
                    currentShape = new Rectangle2D.Double(xx, yx, clippedWidthx, clippedHeightx);

                    image.apply(new DrawOperation(currentShape, isShapeFilled, currentColor));
                    ImagePanel.selection = false;
                    isRedCircleSelected = false;
                    shouldDrawBlueRectangle = false;
                    isDrawingShape = false;

                    break;

                case LINE:
                    currentShape = new Line2D.Double(first.x, first.y, second.x, second.y);

                    image.apply(new DrawOperation(currentShape, isShapeFilled, currentColor));
                    ImagePanel.selection = false;
                    isRedCircleSelected = false;
                    shouldDrawBlueRectangle = false;
                    isDrawingShape = false;

                    break;
                default:
                    break;

            }
            repaint();

        }

        currentShape = null;
        currentShapeType = null;
        ImagePanel.selection = false;
    }

    /**
     * <p>
     * Mouse event handler for mouse entered.
     * </p>
     * 
     * 
     * @param MouseEvent e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * <p>
     * Mouse event handler for mouse exited.
     * </p>
     * 
     * 
     * @param MouseEvent e
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * <p>
     * Handle mouse movement events.
     * </p>
     * 
     * <p>
     * This method is used to update the current shape as the mouse is dragged.
     * </p>
     * 
     * @param e The mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (image.getCurrentImage() == null)
            return;
        isDragging = true;
        second = e.getPoint();
        int scaledFirstX = (int) (first.x);
        int scaledFirstY = (int) (first.y);
        int scaledSecondX = (int) (second.x / scale);
        int scaledSecondY = (int) (second.y / scale);
        // Limit the rectangle within the picture dimensions
        int maxX = image.getCurrentImage().getWidth() - 1;
        int maxY = image.getCurrentImage().getHeight() - 1;
        scaledFirstX = Math.max(0, Math.min(scaledFirstX, maxX));
        scaledFirstY = Math.max(0, Math.min(scaledFirstY, maxY));
        scaledSecondX = Math.max(0, Math.min(scaledSecondX, maxX));
        scaledSecondY = Math.max(0, Math.min(scaledSecondY, maxY));
        if (shouldDrawBlueRectangle) {
            origin = new Point(Math.min(scaledFirstX, scaledSecondX), Math.min(scaledFirstY, scaledSecondY));
            selWidth = Math.abs(scaledFirstX - scaledSecondX);
            selHeight = Math.abs(scaledFirstY - scaledSecondY);
            repaint();
        }

        if (currentShapeType != null) {
            switch (currentShapeType) {
                case CIRCLE:
                    double radiusX = Math.abs(scaledSecondX - scaledFirstX);
                    double radiusY = Math.abs(scaledSecondY - scaledFirstY);
                    double radius = Math.max(radiusX, radiusY);
                    double centerX = scaledFirstX + (scaledSecondX - scaledFirstX) / 2.0;
                    double centerY = scaledFirstY + (scaledSecondY - scaledFirstY) / 2.0;
                    double x = Math.max(0, centerX - radius);
                    double y = Math.max(0, centerY - radius);
                    double clippedWidth = Math.min(maxX - x, radius * 2);
                    double clippedHeight = Math.min(maxY - y, radius * 2);
                    preview = new Ellipse2D.Double(x, y, clippedWidth, clippedHeight);
                    repaint();
                    break;

                case RECTANGLE:

                    int width = Math.abs(scaledSecondX - scaledFirstX);
                    int height = Math.abs(scaledSecondY - scaledFirstY);
                    int xx = Math.min(scaledFirstX, scaledSecondX);
                    int yx = Math.min(scaledFirstY, scaledSecondY);
                    preview = new Rectangle2D.Double(xx, yx, width, height);

                    repaint();
                    break;

                case LINE:
                    preview = new Line2D.Double(scaledFirstX, scaledFirstY, scaledSecondX, scaledSecondY);

                default:
                    break;

            }
        }

        repaint();

    }

    /**
     * <p>
     * Mouse event handler for mouse moved.
     * </p>
     * 
     * 
     * @param MouseEvent e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * <p>
     * Method to draw the rectangle.
     * </p>
     * 
     * 
     * 
     */
    public void RecTangleTime() {

        if (isRedCircleSelected == false && shouldDrawBlueRectangle == true) {
            origin = new Point(Math.min(first.x, second.x), Math.min(first.y, second.y));
            selWidth = Math.abs(first.x - second.x);
            selHeight = Math.abs(first.y - second.y);

            repaint();
            getParent().revalidate();

        } else
            return;
    }

    /**
     * <p>
     * this method sets the current shape type.
     * <p/>
     * 
     * 
     * @param ShapeType shapeType
     * 
     */
    public void setCurrentShapeType(ShapeType shapeType) {
        currentShapeType = shapeType;
        currentShape = null;

    }

    /**
     * <p>
     * this method sets the shape type.
     * <p/>
     * 
     * 
     * @param ShapeType shapeType
     * 
     */
    public void setShapeType(ShapeType shapeType) {
        currentShapeType = shapeType;
    }

    /**
     * <p>
     * this method sets the current colour.
     * <p/>
     * 
     * 
     * @param Color color
     * 
     */
    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    /**
     * <p>
     * this method sets if setShapeFilled is true.
     * <p/>
     * 
     * 
     * @param boolean filled
     * 
     */
    public void setShapeFilled(boolean filled) {
        isShapeFilled = filled;
        repaint(); // Redraw the panel to reflect the changes
    }

}