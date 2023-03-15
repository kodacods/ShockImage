package cosc202.andie;

import java.awt.image.*;

public class RotateTransform implements ImageOperation, java.io.Serializable { //work on lator
    public RotateTransform(){
        
    }
    public BufferedImage apply(BufferedImage input) { 
        return input;
    }   
    
}
