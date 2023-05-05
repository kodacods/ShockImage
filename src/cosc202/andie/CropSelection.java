package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CropSelection implements ImageOperation, java.io.Serializable {
    public CropSelection(){

    }
    public BufferedImage apply(BufferedImage input){
        System.out.println("For testing purposes: ");
        System.out.println("Width of the input image: " + input.getWidth());
        System.out.println("Height of the input image: " + input.getHeight());

        
        System.out.println("Should select width: " + SelectionActions.selWidth);
        System.out.println("Should select height: " + SelectionActions.selHeight);

        

        BufferedImage inpCrop = input.getSubimage(SelectionActions.originPoint.x, SelectionActions.originPoint.y, SelectionActions.selWidth, SelectionActions.selHeight);
        //'shares the same data array' 
        //[pain]
        input = inpCrop;
        
        System.out.println("Width of the changed input image: " + input.getWidth());
        System.out.println("Height of the changed input image: " + input.getHeight());
        BufferedImage output = new BufferedImage(SelectionActions.selWidth, SelectionActions.selHeight, BufferedImage.TYPE_4BYTE_ABGR);


        
        Graphics2D lay = output.createGraphics();
        lay.drawImage(inpCrop, 0, 0, null);
        
        lay.dispose();
    
        System.out.println("Width of the output image: " + output.getWidth());
        System.out.println("Height of the output image: " + output.getHeight());
        return output;

        //Outcome: It crops, but repeating the process makes it... not work. It also kind of explodes on the third crop.

        //I think it has to do with the input taking from the original image pre-crop.
        //I feel as if this code is way too simple. But what can I do to possibly fix it??? 
        //It should work just like resize. But crop. But it doesn't...
        //I've tried the way my Resize does things. Same outcome... and besides that other one is basically just conversion from
        //an Image to BufferedImage. This doesn't need correction, as it is already a BufferedImage, but... huh...???????

        /*Errors as of note:
         * Caused on 1st crop: None
         * Caused on 2nd crop: Returns 'must have an image opened first'. Otherwise may or may not crop properly. 
         * No idea why error message is triggered.
         * Caused on 3rd crop: Exception in thread "AWT-EventQueue-0" java.awt.image.RasterFormatException: (x + width) is outside of Raster
         * Hmm.
         * 
         * Undo: Does not return to previous sizes unlike Resize until returning to the first crop.
         * 
         * It's odd. It should be working. The getSubImage function is basically THE function for cropping.
         * 
         * But... a possible solution is to go pixel by pixel paint...
         */


        /* Testing results:
        * Initial crop has the initial image's length and width. 
        However, the second one has the print prompts go *twice,* sans the 'output height and width' messages. That's odd. That shouldn't happen...
        And for that matter, the input's length and width don't change, either, as expected.
        */

        /* EEK!!!!!

        I'VE TRIED EVERYTHINGGGGGGGG!!!!!!!!!!

        EVEN MAKING IT A SEPARATE ACTION SANS OPEN MENU! IT DOESN'T! WORK!!!!!

        ...

        I bet it only doesn't work on this.......

        I've even tested the other mechanic that works similarly (resize) if it's because its in a method outside of actionPerformed - 
        and yet, it worked perfectly fine.

        WHAT'S GOING ON???
        */
    }
}
