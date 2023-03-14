package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 * 
 * <p>
 * A Sharpen filter enhances the differences between neighboring pixels, 
 * it works as a sort of "reverse blur".
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Alex Haszard
 * @version 1.0
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {
    

    /**
     * <p>
     * Construct a Sharpen filter.
     * </p>
     * 
     */
    SharpenFilter() {   
    }

    /**
     * <p>
     * Apply a Sharpen filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Sharpen filter is implemented via convolution.
     * </p>
     * 
     * @param input The image to apply the Sharpen filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply (BufferedImage input) {
        // The values for the kernel as a 9-element array
        float [] array = 
        {0      , -1/2.0f, 0      ,
         -1/2.0f, 3      , -1/2.0f,
         0      , -1/2.0f, 0      };
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        // And we're done
        return output;
    }

}
