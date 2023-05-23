package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * 
 * <p>
 * This class implements Image Operation to apply the drawn shapes to the image.
 * </p>
 * 
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Stella Yan
 * @version 1.0
 */

public class DrawOperation implements ImageOperation {
    private Shape shape;
    private boolean isShapeFilled;
    private Color color;

    /**
     * <p>
     * Create a new drawing operation.
     * </p>
     * 
     */
    public DrawOperation(Shape shape, boolean isShapeFilled, Color color) {
        this.shape = shape;
        this.isShapeFilled = isShapeFilled;
        this.color = color;
    }

    /**
     * <p>
     * Creates an apply method to apply the drawn shapes to the image based on
     * conditions set in @ImagePanel.
     * </p>
     * 
     * @param input the image to apply the operation to.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        Graphics2D graphics = output.createGraphics();
        graphics.drawImage(input, 0, 0, null);
        graphics.setColor(color);

        if (isShapeFilled && shape instanceof Line2D) {
            graphics.draw(shape);
        } // Draw a line if isShapeFilled is true and the shape is a line (because lines
          // can't be filled

        else if (isShapeFilled) {
            graphics.fill(shape); // Fill the shape if isShapeFilled is true
        } else {
            graphics.draw(shape); // Draw the outline of the shape if isShapeFilled is false
        }

        graphics.dispose();
        return output;
    }
}
