package cosc202.andie;

import java.awt.image.*;
public class RotateClock90Transform implements ImageOperation, java.io.Serializable{
    
    public RotateClock90Transform(){
        
    }
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


