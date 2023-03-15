package cosc202.andie;

import java.awt.image.*;

public class FlipHorzTransform implements ImageOperation, java.io.Serializable { 
    public FlipHorzTransform(){ 

    }
    public BufferedImage apply(BufferedImage input) { 

        int w = input.getWidth(); //simple swapping goes outta bounds
        int h = input.getHeight(); 
        int set[][] = new int[w][h];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                set[i][j] = input.getRGB(i, j); 
            }
        }

        /** for(int i = 0; i < h; i++){ //i wonder if this works
            for(int j = 1; j < w / 2; j++){ 
                int temp = set[i][j];
                set[i][j] = set[i][h - j];
                set[i][h - j] = temp; 
                input.setRGB(i, j, set[i][j]); 
                input.setRGB(i, h - j, set[i][h - j]);
            }
        } */ //it doesn't.
        //Swapping horizontally...

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
