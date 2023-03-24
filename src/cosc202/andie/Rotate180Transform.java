package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to turn the image 180 degrees.
 * </p>
 * 
 * <p>
 * Flips the image by mapping the image onto a 2d array and reverses the 
 * elements of the array along both axes.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Meg Albarico
 * @version 1.0
 */

public class Rotate180Transform implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * Construct a 180 degree rotation.
     * </p>
     */
    
    public Rotate180Transform(){
    }

    /**
     * <p>
     * Apply a 180 degree rotation to an image.
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

        //i think it's practically performing both horz and vert flip
        
        for(int i = 0; i < w; i++){ 
            for(int j = 1; j < h / 2; j++){ 
                int temp = set[i][j];
                set[i][j] = set[i][h - j];
                set[i][h - j] = temp; 
                input.setRGB(i, j, set[i][j]); 
                input.setRGB(i, h - j, set[i][h - j]);
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


