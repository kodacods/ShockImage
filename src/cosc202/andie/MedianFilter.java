package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Median filter.
 * </p>
 * 
 * <p>

 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a median filter with the given radius.
     * </p>
     * 
     * <p>

     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MedianFilter() {
        this(1);
    }

    public BufferedImage apply(BufferedImage input) {
       // int size = (2*radius+1) * (2*radius+1);

        Color[] pixelArray=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF); //idk if i need this

                pixelArray[0]=new Color(input.getRGB(y-1,x-1));
                pixelArray[1]=new Color(input.getRGB(y-1,x));
                pixelArray[2]=new Color(input.getRGB(y-1,x+1));
                pixelArray[3]=new Color(input.getRGB(y,x+1));
                pixelArray[4]=new Color(input.getRGB(y+1,x+1));
                pixelArray[5]=new Color(input.getRGB(y+1,x));
                pixelArray[6]=new Color(input.getRGB(y+1,x-1));
                pixelArray[7]=new Color(input.getRGB(y,x-1));
                pixelArray[8]=new Color(input.getRGB(y,x));

                for(int k=0;k<9;k++){
                    R[k]=pixel[k].getRed();
                    B[k]=pixel[k].getBlue();
                    G[k]=pixel[k].getGreen();
                }
               Arrays.sort(R);
               Arrays.sort(G);
               Arrays.sort(B);
               input.setRGB(x,y,new Color(R[4],B[4],G[4]).getRGB());

               
               // input.setRGB(x, y, argb);
            }
        }
        //copying edited input image to new output image
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, pixelArray);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }
    
}
