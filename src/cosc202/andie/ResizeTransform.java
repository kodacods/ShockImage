package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

public class ResizeTransform implements ImageOperation, java.io.Serializable { //this should probably be the easiest one? <-- NO lol
    //i refuse to use graphics 2d
    //set to a constant scaling factor but provide a custom option lator
    public ResizeTransform(){
    }
    public BufferedImage apply(BufferedImage input) { 
        /**
         * Attempt to do with a specific height and size
         * before going into custom selection like in MeanFilter.
         */
        double scaleFactor = 2;
        int newWidth = (int)(input.getWidth() * scaleFactor);
        int newHeight = (int)(input.getHeight() * scaleFactor);

        Image out = input.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        // okay fine whatever
        BufferedImage output = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR); //oh. yeha there we go
        Graphics2D lay = output.createGraphics();
        lay.drawImage(out, 0, 0, null);

        return output;
    }
}
