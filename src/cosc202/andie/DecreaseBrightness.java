package cosc202.andie;

import java.awt.image.*;

public class DecreaseBrightness implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Create a new decrease brightness operation.
     * 
     * </p>
     */
    Brightness() {
    }

    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);


                //Decreasing the brightness of rgb by 25%
                int cr = (int) Math.round((1+0/100) * (r-127.5)+ (127.5 * 0.75));
                int cg = (int) Math.round((1+0/100) * (g-127.5)+ (127.5 * 0.75));
                int cb = (int) Math.round((1+0/100) * (b-127.5)+ (127.5 * 0.75));

                argb = (a << 24) | (cr << 16) | (cg << 8) | cb;
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
