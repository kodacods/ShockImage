package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian Blur filter.
 * </p>
 * 
 * <p>
 * A Gaussian Blur filter uses the 2-dimensional gaussian equation to average 
 * the values of the neighboring pixels, using a radius, and can be implemented by a convoloution.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills & Alex Haszard
 * @version 1.0
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;
    /**
     * sigma is the value created from the radius.
     */
    private float sigma;

    /**
     * <p>
     * Construct a Gaussian filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed Gaussian Filter
     */
    GaussianBlur(int radius) {
        this.radius = radius;    
        this.sigma = radius / 3.0f;
    }

    /**
     * <p>
     * Construct a Gaussian blur with the default size.
     * </p
     * >
     * <p>
     * By default, a Gaussian blur has radius 1.
     * </p>
     * 
     * @see Gaussian(int)
     */
    GaussianBlur() {
        this(1);
    }

    /**
     * <p>
     * Apply a Gaussian Blur to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Gaussian Blur is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Gaussian Blur to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int size = (2*radius+1) * (2*radius+1);
        float [] array = new float[size];
        float sum = 0;
        
        // Apply the 2d Gaussian formula
        for (int i = 0; i < 2*radius+1; i++) {
            for (int j = 0; j < 2*radius+1; j++) {
                int x = j - radius;
                int y = i - radius;
                float firstCalc = (float) (1 / (2 * Math.PI * Math.pow(sigma, 2)));
                float secondCalc = (float) (Math.exp(-((Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sigma, 2)))));
                array[(i * (2*radius+1)) + j] = firstCalc * secondCalc;
                sum += firstCalc * secondCalc;
            }
        }

        // Normalize the values in array
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] / sum;
        }

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }


}
