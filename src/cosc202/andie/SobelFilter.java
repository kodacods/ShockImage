package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sobel filter.
 * </p>
 * 
 * <p>
 * A Sobel filter is an edge detection filter which enhances the differences
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
public class SobelFilter implements ImageOperation, java.io.Serializable {

    private boolean direction;

    /**
     * Construcs an Sobel filter with a direction
     * 
     * @param d boolean value, false for horizontal and true for vertial filter
     */
    public SobelFilter(boolean d) {
        this.direction = d;
    }

    /**
     * <p>
     * Construct an Sobel filter with default direction
     * </p>
     * 
     */
    public SobelFilter() {
        this(false);
    }

    /**
     * <p>
     * Apply a Sobel filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Sobel filter is implemented via convolution.
     * </p>
     * 
     * @param input The image to apply the Sobel filter to.
     * @return The resulting (Sobeled) image.
     */
    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as an array of 9-element arrays
        float[][] arrays = {
                {
                        -1 / 2.0f, 0, 1 / 2.0f,
                        -1, 0, 1,
                        -1 / 2.0f, 0, 1 / 2.0f
                },
                {
                        -1 / 2.0f, -1, -1 / 2.0f,
                        0, 0, 0,
                        1 / 2.0f, 1, 1 / 2.0f
                }
        };

        // Apply the convolution using the kernel of the current direction
        BufferedImage output = FilterConvolution.applyConvolution(input, arrays[direction ? 1 : 0], 1, 127);

        // And we're done
        return output;
    }

}
