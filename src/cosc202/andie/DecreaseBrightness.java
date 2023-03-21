package cosc202.andie;

import java.awt.image.*;

public class DecreaseBrightness implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Create a new decrease brightness operation.
     * 
     * </p>
     */
    DecreaseBrightness() {
    }

    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = truncate((argb & 0xFF000000) >> 24);
                int r = truncate((argb & 0x00FF0000) >> 16);
                int g = truncate((argb & 0x0000FF00) >> 8);
                int b = truncate((argb & 0x000000FF));

                double c = 0;
                double bt = -25;

                //Decreasing the brightness of rgb by 25%
                int br = (int) Math.round((1 + c/100) * (r-127.5) + 127.5 * (1 + bt/100));
                int bg = (int) Math.round((1 + c/100) * (g-127.5) + 127.5 * (1 + bt/100));
                int bb = (int) Math.round((1 + c/100) * (b-127.5) + 127.5 * (1 + bt/100));

                argb = (a << 24) | (br << 16) | (bg << 8) | bb;
                input.setRGB(x, y, argb);
            }
        }
        return input;
    }

    /**
     * Takes the pixel value as an argument.
     * If the pixel value is <0 or >255 then the amended value will be returned.
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
