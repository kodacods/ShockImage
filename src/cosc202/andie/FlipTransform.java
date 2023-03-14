package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class FlipTransform implements ImageOperation, java.io.Serializable { //by far the easiest one probably (i hope...)
    public FlipTransform(){

    }
    public BufferedImage apply(BufferedImage input) { 
        int w = input.getWidth();
        int h = input.getHeight(); //okay i think i got it now. this'll be slow...
        int set[][] = new int[w][h];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){//hmm. its not like a conventional 2d array - 
                set[i][j] = input.getRGB(i, j); //probably going to take hell of a time
            }
            System.out.println(set[i]);
        }
        for(int i = 0; i < w; i++){ //swap pixels. there are definitely better ways of doing this.
            System.out.println(set[i]);
            for(int j = 0; j < h; j++){ //the usual.
                int temp = set[i][j];
                set[i][j] = set[i][h - j];
                set[i][h - j] = temp; //now that's done...
                input.setRGB(i, j, set[i][j]);
            } //if it works... now to convert it back again...
            System.out.println(set[i]);
        }
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        return output;

    }  
}
