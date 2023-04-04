package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to adjust the contrast by percentage in increments of -25, 0, 25.
 * </p>
 * 
 * <p>
 * Independantly changes rgb in pixels of the image to adjust the contrast of the overall image
 * Using the equation v = (1+ c/100)*(v−127.5) + (127.5*(1+ bt/100)), where v, is the current pixel value, c is the contrast percentage, bt is the brightness percentage = 0;
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Beka Rolleston
 * @version 1.0
 */

public class Contrast implements ImageOperation, java.io.Serializable {

    private int percentage=0;
    
    Contrast() {
    }

    /**
     * <p>
     * Create a new Contrast operation.
     * 
     * </p>
     * @param percentage
     */
    public Contrast(int percentage){
        this.percentage = percentage;
    }

    /**
     * <p>
     * Takes the buffered image and finds the value of each pixel and adjusts each contrast by the percentage factor
     * 
     * @param input of the buffered image
     * @return input of the altered contrast image
     }
     */
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                double c = percentage;
                double bt = 0;
                //Decreasing the contrast of rgb by 25%
                int cr = truncate( (int) Math.round((1 + c/100) * (r-127.5) + 127.5 * (1 + bt/100)) );
                int cg = truncate( (int) Math.round((1 + c/100) * (g-127.5) + 127.5 * (1 + bt/100)) );
                int cb = truncate( (int) Math.round((1 + c/100) * (b-127.5) + 127.5 * (1 + bt/100)) );

                argb = (a << 24) | (cr << 16) | (cg << 8) | cb;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
    
    /**
     * Takes the pixel value that has been adjusted to the new percentage contrast.
     * If the pixel value is <0 the pixel value will be set to 0
     * If the pixel value is >255 the pixel value will be set to 255
     * If the pixel value is within the range of 0-255, then the pixel value will not change.
     * 
     * @param value
     * @return value
     */
    public static int truncate (int value){
        if (value < 0) {
            return 0;
        }
        else if (value > 255) {
            return 255;
        } else {
        return value;
        }
    }
}
