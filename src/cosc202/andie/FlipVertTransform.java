package cosc202.andie;

import java.awt.image.*;

public class FlipVertTransform implements ImageOperation, java.io.Serializable { //by far the easiest one probably (i hope...)
    public FlipVertTransform(){ //uhhh just use graphics2d IM NOT DOING THAT !!!1!!!!

    }
    public BufferedImage apply(BufferedImage input) { 
        System.out.println("Working");
        int w = input.getWidth();
        int h = input.getHeight(); 
        int set[][] = new int[w][h];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                set[i][j] = input.getRGB(i, j); 
            }
        }
        System.out.println("Created Array");
        for(int i = 0; i < w; i++){ 
            for(int j = 1; j < h / 2; j++){ 
                int temp = set[i][j];
                set[i][j] = set[i][h - j];
                set[i][h - j] = temp; 
                input.setRGB(i, j, set[i][j]); 
                input.setRGB(i, h - j, set[i][h - j]);
            }
        }
        System.out.println("Flipped");
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        return output;
// casting issue more like skill issue <-
    }  
}
