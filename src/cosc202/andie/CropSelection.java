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
        //I feel as if this code is way too simple. But what can I do to possibly fix it??? 
        //It should work just like resize. But crop. But it doesn't...
        //I've tried the way my Resize does things. Same outcome... and besides that other one is basically just conversion from
        //an Image to BufferedImage. This doesn't need correction, as it is already a BufferedImage, but... huh...???????

        /*Errors as of note:
         * Caused on 1st crop: None
         * Caused on 2nd crop: Returns 'must have an image opened'. Otherwise may or may not crop properly. 
         * No idea why error message is triggered.
         * Caused on 3rd crop: Exception in thread "AWT-EventQueue-0" java.awt.image.RasterFormatException: (x + width) is outside of Raster
         * Hmm.
         * 
         * Undo: Does not return to previous sizes unlike Resize until returning to the first crop.
         */
    }
}
