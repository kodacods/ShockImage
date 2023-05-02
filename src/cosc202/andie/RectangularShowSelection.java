package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

public class RectangularShowSelection implements ImageOperation, java.io.Serializable {
    public BufferedImage apply(BufferedImage input){
        // BufferedImage image = input;
        // Graphics2D g2d = image.createGraphics();
        // g2d.setColor(Color.BLUE);

        // g2d.drawRect(SelectionActions.originPoint.x, SelectionActions.originPoint.y, SelectionActions.selWidth, SelectionActions.selHeight);
        // g2d.dispose();
        //Now doing a modified version of brightness modification.
        //Code partially copied from initial Brightness filter with that filter's author's permission.
        int borderCornerX = 0;
        int borderCornerY = 0;

        //determine the point of the lower right corner as opposed to the already set origin point 

        if(SelectionActions.originPoint.x == SelectionActions.selFirst.x){
            borderCornerX = SelectionActions.selSecond.x;
        } else if (SelectionActions.originPoint.x == SelectionActions.selSecond.x) {
            borderCornerX = SelectionActions.selFirst.x;
        }

        if(SelectionActions.originPoint.y == SelectionActions.selFirst.y){
            borderCornerY = SelectionActions.selSecond.y;
        } else if (SelectionActions.originPoint.y == SelectionActions.selSecond.y) {
            borderCornerY = SelectionActions.selFirst.y;
        }

        for(int y = 0; y < input.getHeight(); y++){
            for(int x = 0; x < input.getWidth(); x++){
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                double c = 0;
                double bt = 0.25;

                int br = 0;
                int bg = 0;
                int bb = 0;

                if(x < borderCornerX && x > SelectionActions.originPoint.x && y < borderCornerY && y > SelectionActions.originPoint.y){
                    br = truncate( (int) Math.round((1 + c/100) * (r-127.5) + 127.5 * (1 + bt)) );
                    bg = truncate( (int) Math.round((1 + c/100) * (g-127.5) + 127.5 * (1 + bt)) );
                    bb = truncate( (int) Math.round((1 + c/100) * (b-127.5) + 127.5 * (1 + bt)) );
                } else {
                    br = truncate( (int) Math.round((1 + c/100) * (r-127.5) + 127.5 * (1 - bt)) );
                    bg = truncate( (int) Math.round((1 + c/100) * (g-127.5) + 127.5 * (1 - bt)) );
                    bb = truncate( (int) Math.round((1 + c/100) * (b-127.5) + 127.5 * (1 - bt)) );
                }

                argb = (a << 24) | (br << 16) | (bg << 8) | bb;
                input.setRGB(x, y, argb);
            }

        }
        return input;
    }

    /**
     * Takes the pixel value that has been adjusted to the new percentage brightness.
     * If the pixel value is <0 the pixel value will be set to 0
     * If the pixel value is >255 the pixel value will be set to 255
     * If the pixel value is within the range of 0-255, then the pixel value will not change.
     * 
     * @param value
     * @return value
     */
    public static int truncate (int value){
        if (value < 0) {
            return 0;
        }
        else if (value > 255) {
            return 255;
        } else {
        return value;
        }
    }
}
