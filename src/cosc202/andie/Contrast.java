package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to adjust the contrast of the image
 * </p>
 * 
 * <p>
 * The images produced by this operation are still technically colour images,
 * in that they have red, green, and blue values, but each pixel has equal
 * values for red, green, and blue giving a shade of grey.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Beka Rolleston
 * @version 1.0
 */

public class Contrast {

    /**
     * <p>
     * Create a new increaseContrast operation.
     * </p>
     */
    Contrast() {
    }

    public BufferedImage apply(BufferedImage input, double perc) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = inRange((argb & 0xFF000000) >> 24);
                int r = inRange((argb & 0x00FF0000) >> 16);
                int g = inRange((argb & 0x0000FF00) >> 8);
                int b = inRange((argb & 0x000000FF));


                
                //int grey = (int) Math.round(0.3*r + 0.6*g + 0.1*b);

                //Increasing the contrast of rgb by 25%
                int cr = (int) Math.round(1.25 * (r-127.5)+ 127.5 * 2);
                int cg = (int) Math.round(1.25 * (r-127.5)+ 127.5 * 2);
                int cb = = (int) Math.round(1.25 * (r-127.5)+ 127.5 * 2);

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }

    public static int inRange (int value){
        if (value < 0) value = 0;
        if (value > 255) value = 255;
        return value;
    }
}
