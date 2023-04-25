package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

//implements ImageOperation, java.io.Serializable <- let's say this doesn't apply. does selection count as an operation? a prelude to
//an operation maybe?

public class RectangularShowSelection implements ImageOperation, java.io.Serializable {
    Point p1 = SelectionActions.selFirst;
    Point p2 = SelectionActions.selSecond;
    int cornerOriginX = 0;
    int cornerOriginY = 0;
    int lengthX = 0;
    int lengthY = 0;
    public BufferedImage apply(BufferedImage input){
        BufferedImage image = input;
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLUE);
        if(p1.equals(p2)){
            return input;
        } 
        if(p1.x < p2.x){
            cornerOriginX = p1.x;
            lengthX = p2.x - p1.x;
        } else {
            cornerOriginX = p2.x;
            lengthX = p1.x - p2.x;
        }

        if(p1.y < p2.y){
            cornerOriginY = p1.y;
            lengthY = p2.y - p1.y;
        } else {
            cornerOriginY = p2.y;
            lengthY = p1.y - p2.y;
        }

        g2d.drawRect(cornerOriginX, cornerOriginY, lengthX, lengthY);
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
