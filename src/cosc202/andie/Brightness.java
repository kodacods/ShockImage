package cosc202.andie;

import java.awt.image.*;

public class Brightness implements ImageOperation, java.io.Serializable {

    private int percentage=0;

    
    Brightness() {
    }

    /**
     * <p>
     * Create a new brightness operation.
     * 
     * @param percentage to be adjusted
     * </p>
     */
    Brightness(int percentage){
        this.percentage = percentage;
    }

    /**
     * <p>
     * Takes the buffered image andfinds the value of each pixel and adjusts each brightness by the percentage factor
     * 
     * @param input of the buffered image
     * @return input of the altered brightness image
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

                double c = 0;
                double bt = percentage;

                //Decreasing the brightness of rgb by 25%
                int br = truncate( (int) Math.round((1 + c/100) * (r-127.5) + 127.5 * (1 + bt/100)) );
                int bg = truncate( (int) Math.round((1 + c/100) * (g-127.5) + 127.5 * (1 + bt/100)) );
                int bb = truncate( (int) Math.round((1 + c/100) * (b-127.5) + 127.5 * (1 + bt/100)) );

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
