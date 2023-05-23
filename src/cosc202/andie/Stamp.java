package cosc202.andie;

import java.awt.Graphics;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to stamp one image onto another image
 * </p>
 * 
 * <p>
 * Gets an image, x, y coordinates and places the image at those x and 
 * y coordinates over the current image.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Alex Haszard
 * @version 1.0
 */

public class Stamp implements ImageOperation { 

    BufferedImage stamp;
    int x, y;

    /**
     * Constructor function
     * this takes in 3 parameters and sets them as data fields for the 
     * class
     * @param img BufferedImage that will be stamped onto the input image
     * @param x x-coordinate to stamp the image to
     * @param y y-coordinate to stamp the image to
     */
    public Stamp(BufferedImage img, int x, int y){
        this.stamp = img;
        this.x = x;
        this.y = y;
    }

    /**
     * Apply method
     * This methos takes the input image, and draws the stamp on top and
     * return the output
     * @param input a BufferedImage
     * @return the stamped BufferedImage
     */
    public BufferedImage apply(BufferedImage input) { 
        BufferedImage output = input;
        Graphics g = output.getGraphics();
        g.drawImage(stamp, this.x, this.y, null);
        g.dispose();
        return output;
    }
}
