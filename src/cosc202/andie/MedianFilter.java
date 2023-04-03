package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Median filter.
 * </p>
 * 
 * <p>
 * A Median filter blurs an image by replacing each pixel by the median of the
 * pixels in a surrounding neighbourhood, and can be implemented by a convoloution.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter(int)
     */
    public MedianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Median filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int size = (2*radius+1) * (2*radius+1);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        for (int y = radius; y < input.getHeight() - radius; ++y) {
            for (int x = radius; x < input.getWidth() - radius; ++x) {
                int[] r = new int[size];
                int[] g = new int[size];
                int[] b = new int[size];
                int[] a = new int[size];
                int count = 0;
                for (int i = -radius; i <= radius; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        int argb = input.getRGB(x + i, y + j);
                        a[count] = (argb & 0xFF000000) >> 24;
                        r[count] = (argb & 0x00FF0000) >> 16;
                        g[count] = (argb & 0x0000FF00) >> 8;
                        b[count] = (argb & 0x000000FF);
                        count++;
                    }
                }

                Arrays.sort(a);
                Arrays.sort(r);
                Arrays.sort(g);
                Arrays.sort(b);

                int argbFiltered = (a[(a.length-1) / 2] << 24) | (r[(r.length-1) / 2] << 16) | (g[(g.length-1) / 2] << 8) | b[(b.length-1) / 2];
                output.setRGB(x, y, argbFiltered);
            }
        }

        return output;
    }


}

