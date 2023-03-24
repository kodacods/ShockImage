package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to resize the image by a specified scale factor.
 * </p>
 * 
 * <p>
 * Resizes the images by a scale factor by creating a resized Image instance of the input image using 
 * then mapping it onto a new output
 * BufferedImage.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Meg Albarico
 * @version 1.0
 */

public class ResizeTransform implements ImageOperation, java.io.Serializable { 
    private double scaleFactor = 1; 
    public ResizeTransform(double scaleFactor){
        this.scaleFactor = scaleFactor;
    }
    public BufferedImage apply(BufferedImage input) { 
        /**
         * Attempt to do with a specific height and size
         * before going into custom selection like in MeanFilter.
         */
        
        int newWidth = (int)(input.getWidth() * scaleFactor); 
        int newHeight = (int)(input.getHeight() * scaleFactor);

        int scaleType = 0;
        if(scaleFactor > 1){
            scaleType = Image.SCALE_SMOOTH;
        } else {
            scaleType = Image.SCALE_AREA_AVERAGING;
        }
        Image out = input.getScaledInstance(newWidth, newHeight, scaleType);
        // okay fine whatever
        BufferedImage output = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR); //oh. yeha there we go
        Graphics2D lay = output.createGraphics();
        lay.drawImage(out, 0, 0, null);

        return output;
    }
}
