package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CropSelection implements ImageOperation, java.io.Serializable {
    public BufferedImage apply(BufferedImage input){

        BufferedImage out = input.getSubimage(SelectionActions.originPoint.x, SelectionActions.originPoint.y, SelectionActions.selLength, SelectionActions.selWidth);
        BufferedImage output = new BufferedImage(SelectionActions.selLength, SelectionActions.selWidth, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D lay = output.createGraphics();
        lay.drawImage(out, 0, 0, null);  // x = 0 and y = 0 refers to where On The New Blank Image... which won't work...

        return output;
    }
}
