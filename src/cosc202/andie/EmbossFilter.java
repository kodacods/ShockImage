package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Emboss filter.
 * </p>
 * 
 * <p>
 * A Emboss filter is an edge detection filter which enhances the differences
 * of an image near its edges.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Alex Haszard & Steven Mills
 * @version 1.0
 */
public class EmbossFilter implements ImageOperation, java.io.Serializable {

    private int direction;

    /**
     * Construcs an emboss filter with a direction
     * 
     * @param d Value from 0 - 8, defining the direction of the filter (starting
     *          from the top left, and traversing each row thereafter)
     */
    public EmbossFilter(int d) {
        this.direction = d;
    }

    /**
     * <p>
     * Construct an Emboss filter with default direction
     * </p>
     * 
     */
    public EmbossFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Emboss filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Emboss filter is implemented via convolution.
     * </p>
     * 
     * @param input The image to apply the Emboss filter to.
     * @return The resulting (Embossed) image.
     */
    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as an array of 9-element arrays
        float[][] arrays = {
                {
                        1, 0, 0,
                        0, 0, 0,
                        0, 0, -1
                },
                {
                        0, 1, 0,
                        0, 0, 0,
                        0, -1, 0
                },
                {
                        0, 0, 1,
                        0, 0, 0,
                        -1, 0, 0
                },
                {
                        0, 0, 0,
                        1, 0, -1,
                        0, 0, 0
                },
                {
                        0, 0, 0,
                        0, 0, 0,
                        0, 0, 0
                },
                {
                        0, 0, 0,
                        -1, 0, 1,
                        0, 0, 0
                },
                {
                        0, 0, -1,
                        0, 0, 0,
                        1, 0, 0
                },
                {
                        0, -1, 0,
                        0, 0, 0,
                        0, 1, 0
                },
                {
                        -1, 0, 0,
                        0, 0, 0,
                        0, 0, 1
                },
        };

        // Apply the convolution using the kernel of the current direction
        BufferedImage output = FilterConvolution.applyConvolution(input, arrays[direction], 1, 127);

        // And we're done
        return output;
    }

}
