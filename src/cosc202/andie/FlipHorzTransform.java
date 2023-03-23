package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to flip the image horizontally.
 * </p>
 * 
 * <p>
 * Flips the image by mapping the image onto a 2d array and reversing the 
 * elements in the array along the columns of the 2D array, that being the width of the image.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Meg Albarico
 * @version 1.0
 */

public class FlipHorzTransform implements ImageOperation, java.io.Serializable { 

    /**
     * <p>
     * Construct a horizontal flip.
     * </p>
     */

    public FlipHorzTransform(){ 
    }

    /**
     * <p>
     * Apply a horizontal flip to an image.
     * </p>
     * 
     * @param input The image to be rotated.
     * @return The resulting rotated image.
     * 
     */

    public BufferedImage apply(BufferedImage input) { 

        int w = input.getWidth(); 
        int h = input.getHeight(); 
        int set[][] = new int[w][h];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                set[i][j] = input.getRGB(i, j); 
            }
        }

        for(int j = 0; j < h; j++){
            for(int i = 1; i < w / 2; i++){
               int temp = set[i][j];
               set[i][j] = set[w - i][j];
               set[w - i][j] = temp; 
               
               input.setRGB(i, j, set[i][j]); 
               input.setRGB(w - i, j, set[w - i][j]);
            }
         }


        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        return output;
    }  
}
