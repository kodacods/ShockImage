package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class ResizeTransform implements ImageOperation, java.io.Serializable { //work on lator
    public BufferedImage apply(BufferedImage input) { 
        /**
         * Attempt to do with a specific height and size
         * before going into custom selection like in MeanFilter.
         */
        Image modify = (Image)input;
        BufferedImage output = input.getScaledInstance(2*input.getWidth(), 2*input.getHeight(), 16);
    }
}
