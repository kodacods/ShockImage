package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to turn the image 90 degrees clockwise.
 * </p>
 * 
 * <p>
 * Flips the image by mapping the image onto a 2d array and, after setting up a 2d array with 
 * height as the columns and width as the rows (the initial 2d array is the reverse), map the values from 
 * the initial 2d array onto the new array, descending in terms of height and advancing in terms of width.
 * 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Meg Albarico
 * @version 1.0
 */

public class RotateClock90Transform implements ImageOperation, java.io.Serializable{
    
    /**
     * <p>
     * Construct a 90 degree clockwise rotation.
     * </p>
     */

    public RotateClock90Transform(){
    }

    /**
     * <p>
     * Apply a 90 degree clockwise rotation to an image.
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
        
        int[][] setOut = new int[h][w]; //rotate clockwise
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                setOut[i][j] = set[j][h - 1 - i];
            } 
        }
        
        BufferedImage turned = new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB);

        for(int i = 0; i < h; i++){
            for(int j = 1; j < w; j++){
                turned.setRGB(i, j, setOut[i][j]); 
            } 
        }
        

        BufferedImage output = new BufferedImage(turned.getColorModel(), turned.copyData(null), turned.isAlphaPremultiplied(), null);
        return output;
    }   
    
}


