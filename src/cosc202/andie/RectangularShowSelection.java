package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

//implements ImageOperation, java.io.Serializable <- let's say this doesn't apply. does selection count as an operation? a prelude to
//an operation maybe?

public class RectangularShowSelection implements ImageOperation, java.io.Serializable {
    public BufferedImage apply(BufferedImage input){
        BufferedImage image = input;
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLUE);

        g2d.drawRect(SelectionActions.originPoint.x, SelectionActions.originPoint.y, SelectionActions.selLength, SelectionActions.selWidth);
        g2d.dispose();
        return image;
    }

    //Okay, so the basic premise is to use MouseListener to select a specific section
    //of an image.

    //Obviously this shouldn't literally brighten the selection area and darken the
    //unselected permanently. It will be a state that's 'held in place' until cancelled
    //So select applies the filter; doing anything with it returns the brightness
    //configuration back to normal.

    //There should probably be some switch that turns true whenever selection is applied 
    // and there will be a cancel function in place of the 'start select' function
    //oh this is going to be hard
    //:3

    //Stores dimensions of the selected region... hmm.

    //For brightening the selected area / dimming the rest, rewritten brightness filter conditional within / outside a specified boundary 

    //Objectives:

    //Apply MouseListener to panel [HELP!!!]
    //Do mouse actions [Easy enough]
    //Apply darken filter outside of selection [Unsure how to do...]

    //New plan: Do in SelectionActions.

}
