

package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;

public class DrawingAction implements ImageOperation {
    private int x;
    private int y;
    private int w;
    private int h;


    public DrawingAction(int x, int y, int w, int h, Colour colour, ) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }




    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2d = image.createGraphics();
         g2d.setColor(Color.GREEN);
        BufferedImage drawImage = input.getSubimage(x, y, w, h);
        g2d.fillRect(x, y, w, h); // (x, y, width, height)
        g2d.dispose();
    }

    // Graphics2D g2d = image.createGraphics();
    // g2d.setColor(Color.GREEN);
    // g2d.fillRect(100, 100, 300, 200); // (x, y, width, height)
    // g2d.dispose();

}
