package cosc202.andie;

import java.awt.image.*;

public class ResizeTransform implements ImageOperation, java.io.Serializable { //this should probably be the easiest one?
    public ResizeTransform(){
    }
    public BufferedImage apply(BufferedImage input) { 
        /**
         * Attempt to do with a specific height and size
         * before going into custom selection like in MeanFilter.
         */
        //problem is it only works on image instances. not buffered?
        //Process:
        //Make copy of BufferedImage as an Image
        //Do that scaling thing on *that* Image
        //then have it as a BufferedImage output.
        //But HOW AAAAUUUUUUUGGGGGH

        

        // Image result = input.getScaledInstance(2*input.getWidth(), 2*input.getHeight(), Image.SCALE_AREA_AVERAGING);
        // BufferedImage output = (BufferedImage)result;
        return input;
    }
}
