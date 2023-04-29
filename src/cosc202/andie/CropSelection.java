package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CropSelection implements ImageOperation, java.io.Serializable {
    public BufferedImage apply(BufferedImage input){

        BufferedImage output = input.getSubimage(SelectionActions.originPoint.x, SelectionActions.originPoint.y, SelectionActions.selLength, SelectionActions.selWidth);
        input = output;
        return output;

        //Outcome: It crops, but repeating the process makes it... not work. It also kind of explodes on the third crop.

        //I think it has to do with the input taking from the original image pre-crop.
    }
}
