package cosc202.andie;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.MouseInputListener;


/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseInputListener { //m
    
    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    public static Shape preview;
    public enum ShapeType { CIRCLE, RECTANGLE, SQUARE }

    public static ShapeType currentShapeType;
    public static Shape currentShape;


    public static Point first, second;
    public static Point origin;
    public static  int selWidth, selHeight;
        private boolean circleDrawn = false;


    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;
    private ArrayList<Shape> shapes; // List to store drawn shapes


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
        shapes = new ArrayList<>(); // Initialize the shapes list

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
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
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
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
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
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            for (Shape shape : shapes) {
                g2.setColor(Color.RED);
                g2.draw(shape);
            }

                // Draw the currentShape
    if (currentShape != null) {
        g2.setColor(Color.RED);
        g2.draw(currentShape);
    }
        
            // Draw rectangle here
            if (currentShape == null && origin != null) {
                g2.setColor(Color.BLUE);
                g2.drawRect(origin.x, origin.y, selWidth, selHeight);
            }
           
            g2.dispose();


        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
            (circleDrawn) = false;
            // return; // Ignore mouse press if a circle has already been drawn
        // }
        Point point = e.getPoint();

        first = new Point((int)(point.getX() / scale), (int)(point.getY() / scale));

        switch (currentShapeType) {
            case CIRCLE:
                currentShape = new Ellipse2D.Double(first.x, first.y, 0, 0);
                break;
            case RECTANGLE:
                currentShape = new Rectangle2D.Double(first.x, first.y, 0, 0);
                break;
            case SQUARE:
                currentShape = new Rectangle2D.Double(first.x, first.y, 0, 0);
                break;
        }
     
    }

@Override
public void mouseReleased(MouseEvent e) {
    if (circleDrawn) {
            return; // Ignore mouse release if a circle has already been drawn
        }
    Point point = e.getPoint();

    second = new Point((int)(point.getX() / scale), (int)(point.getY() / scale));
    RecTangleTime();

    switch (currentShapeType) {
        case CIRCLE:
        
            double radiusX = Math.abs(second.x - first.x) / scale;
            double radiusY = Math.abs(second.y - first.y) / scale;
            double radius = Math.max(radiusX, radiusY);
            double centerX = first.x + (second.x - first.x) / 2.0;
            double centerY = first.y + (second.y - first.y) / 2.0;
            double x = centerX - radius;
            double y = centerY - radius;
            currentShape = new Ellipse2D.Double(x, y, radius * 2, radius * 2);
             shapes.add(currentShape);
                circleDrawn = true; // Set the flag to true indicating a circle has been drawn
                
            break;
        case RECTANGLE:
            currentShape = new Rectangle2D.Double(first.x, first.y, second.x/scale, second.y/scale);
            shapes.add(currentShape);
            break;
        case SQUARE:
            currentShape = new Rectangle2D.Double(first.x, first.y, second.x/scale, second.y/scale);
            shapes.add(currentShape);
            break;
    }

    // Clear the currentShape reference
    currentShape = null;

    repaint();
    //RecTangleTime();
}


    @Override
    public void mouseEntered(MouseEvent e) {
    }

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
                if (circleDrawn) {
            return; // Ignore mouse press if a circle has already been drawn
        }
        second = e.getPoint();
        switch (currentShapeType) {
            case CIRCLE:
                double radiusX = Math.abs(second.x - first.x) / scale;
                double radiusY = Math.abs(second.y - first.y) / scale;
                double radius = Math.max(radiusX, radiusY);
                double centerX = first.x + (second.x - first.x) / 2.0;
                double centerY = first.y + (second.y - first.y) / 2.0;
                double x = centerX - radius;
                double y = centerY - radius;
                currentShape = new Ellipse2D.Double(x, y, radius * 2, radius * 2);
                break;
            case RECTANGLE:
                ((Rectangle2D.Double) currentShape).setFrameFromDiagonal(first.x, first.y, second.x, second.y);
                break;
            case SQUARE:
                int size = Math.max(Math.abs(second.x - first.x), Math.abs(second.y - first.y));
                ((Rectangle2D.Double) currentShape).setFrame(first.x, first.y, size, size);
                break;
        }
        
        repaint();
    }
    

    

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void RecTangleTime() {
            origin = new Point(Math.min(first.x, second.x), Math.min(first.y, second.y));
            selWidth = Math.abs(first.x - second.x);
            selHeight = Math.abs(first.y - second.y);
            System.out.println("here");
            //getImage().apply(new RectangularShowSelection());
            repaint();
            getParent().revalidate();

    }

    public void setCurrentShapeType(ShapeType shapeType) {
    currentShapeType = shapeType;
        currentShape = null;

    // System.out.println("Shape type: " + shapeType);
}



   
}
