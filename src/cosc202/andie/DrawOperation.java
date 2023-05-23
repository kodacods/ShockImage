package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class DrawOperation implements ImageOperation {
    private Shape shape;
    private boolean isShapeFilled;
    private Color color;

    public DrawOperation(Shape shape, boolean isShapeFilled, Color color) {
        this.shape = shape;
        this.isShapeFilled = isShapeFilled;
        this.color = color;
    }

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
